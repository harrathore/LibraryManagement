package library;

import enums.BOOK_AVAILABILITY_STATUS_ENUM;
import enums.TICKET_STATUS_ENUM;
import models.SearchBookDto;
import notifications.NotificationServiceInterface;
import notifications.NotificationStrategyManager;
import users.Customer;

import java.util.*;

public class Library implements AdminInterface, CustomerInterface {
    private static Library library = null;
    private Set<Book> allbooks;
    private Set<Ticket> allOpenedTickets;
    private Set<Ticket> allClosedTickets;
    private NotificationStrategyManager notificationStrategyManager;
    private Library(){
        allbooks = new HashSet<>();
        allOpenedTickets = new HashSet<>();
        allClosedTickets = new HashSet<>();
        notificationStrategyManager = NotificationStrategyManager.getStrategyManager();
    }

    public static Library getLibrary(){
        if(library==null){
            synchronized (new Object()){
                if(library==null){
                    library = new Library();
                }
            }
        }
        return library;
    }

    @Override
    public String addBookInLibrary(Book book) {
        allbooks.add(book);
        return "Book added to library";
    }

    @Override
    public String removeBookFromLibrary(Book book) {
        allbooks.remove(book);
        return "Book removed from library";
    }

    @Override
    public String notifyAllDue() {
        this.notifyAllDueSubmision();
        return "Notified";
    }

    @Override
    public List<Book> searchBooksFromLibrary(SearchBookDto searchBookDto) {
        System.out.println("Searching books");
        List<Book> allSearchedBooks = new ArrayList<>();
        allbooks.stream().forEach(book->{
            if (book.getBookName().equalsIgnoreCase(searchBookDto.getBookName()) &&
                    book.getBookAvailabilityStatus().equals(BOOK_AVAILABILITY_STATUS_ENUM.AVAILABLE))
                allSearchedBooks.add(book);
            else if (book.getBookAvailabilityStatus().equals(BOOK_AVAILABILITY_STATUS_ENUM.AVAILABLE) &&
                    book.getAuthor().equalsIgnoreCase(searchBookDto.getAuthor()))
                allSearchedBooks.add(book);
        });
        return allSearchedBooks;
    }

    @Override

    public synchronized Ticket issueBookToMe(Customer customer, List<Book> bookList) {
        //Customer need to be authenticated here then only it will be allowed
        System.out.println("Issueing books");
        if(!Objects.nonNull(bookList)){
           return null;
        }
        bookList.stream().forEach(book -> {
            book.setBookAvailabilityStatus(BOOK_AVAILABILITY_STATUS_ENUM.ISSUED);
        });

        Ticket ticket = new Ticket(customer, bookList);
        allOpenedTickets.add(ticket);
        return ticket;
    }

    @Override
    public Ticket returnBooks(Ticket ticket) {
        allOpenedTickets.remove(ticket);
        List<Book> allBooksToBeReturned = ticket.getBooksIssued();
        ticket.setBillAmount(allBooksToBeReturned.size()*10);
        Long currentDate = 9L;
        if(currentDate > ticket.getIssuedOn() + 7L){
            long l = currentDate - 7L;
            ticket.setFineAmount(20 * (int)l);
        }
        allBooksToBeReturned.stream().forEach(book -> {
            book.setBookAvailabilityStatus(BOOK_AVAILABILITY_STATUS_ENUM.AVAILABLE);
        });
        ticket.setTicketStatus(TICKET_STATUS_ENUM.CLOSED);
        ticket.setClosedOn(currentDate);
        allClosedTickets.add(ticket);
        return ticket;
    }

    /**
     * A cron job for all due submission
     */
    private void notifyAllDueSubmision(){
        Long currentDate = 11L;
        allOpenedTickets.forEach(openTicket->{
            if(openTicket.getIssuedOn()+7L < currentDate){
                NotificationServiceInterface notificationServiceInterface =
                        notificationStrategyManager.decideNotificationService(openTicket.getCustomer().getNotificationPreference());
                notificationServiceInterface.notifyCustomer(openTicket.getCustomer());
            }
        });
    }
}
