package library;

import enums.BOOK_AVAILABILITY_STATUS_ENUM;
import enums.TICKET_STATUS_ENUM;
import exceptions.GenericCustomException;
import models.SearchBookRequestDto;
import models.Ticket;
import notifications.NotificationServiceInterface;
import notifications.NotificationStrategyManager;
import users.Customer;

import java.util.*;

public class Library implements LibraryInterfaceForAdmin, LibraryInterfaceForCustomer {
    private static Library library = null;
    private Set<Book> allBooks;
    private Set<Customer> allCustomers;
    private Set<Ticket> allOpenedTickets;
    private Set<Ticket> allClosedTickets;
    private NotificationStrategyManager notificationStrategyManager;
    private Library(){
        allBooks = new HashSet<>();
        allOpenedTickets = new HashSet<>();
        allClosedTickets = new HashSet<>();
        allCustomers = new HashSet<>();
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
        allBooks.add(book);
        return "Book added to library";
    }

    @Override
    public String removeBookFromLibrary(Book book) {
        allBooks.remove(book);
        return "Book removed from library";
    }


    @Override
    public Boolean registerCustomerToLibrary(Customer customer) {
        allCustomers.add(customer);
        return true;
    }

    @Override
    public Customer removeCustomerFromLibrary(Customer customer) {
        allCustomers.remove(customer);
        return customer;
    }

    @Override
    public List<Book> searchBooksFromLibrary(SearchBookRequestDto searchBookRequestDto) {
        System.out.println("Searching books");
        List<Book> allSearchedBooks = new ArrayList<>();
        allBooks.stream().forEach(book->{
            if (book.getBookName().equalsIgnoreCase(searchBookRequestDto.getBookName()) &&
                    book.getBookAvailabilityStatus().equals(BOOK_AVAILABILITY_STATUS_ENUM.AVAILABLE))
                allSearchedBooks.add(book);
            else if (book.getBookAvailabilityStatus().equals(BOOK_AVAILABILITY_STATUS_ENUM.AVAILABLE) &&
                    book.getAuthor().equalsIgnoreCase(searchBookRequestDto.getAuthor()))
                allSearchedBooks.add(book);
        });
        return allSearchedBooks;
    }

    @Override
    public Ticket issueBookToMe(Customer customer, List<Book> bookList) throws RuntimeException{
        Boolean isThisValidCustomer = this.validateThisCustomer(customer);
        if(!isThisValidCustomer){
            throw new RuntimeException("You are not valid customer, please sign up first");
        }
        if(bookList==null || bookList.size()==0){
            throw new RuntimeException("Please select valid books");
        }
        bookList.stream().forEach(book -> {
            synchronized (new Object()){
                if(book.getBookAvailabilityStatus().equals(BOOK_AVAILABILITY_STATUS_ENUM.AVAILABLE)){
                    book.setBookAvailabilityStatus(BOOK_AVAILABILITY_STATUS_ENUM.ISSUED);
                    System.out.println("this book issued successfully");
                }else{
                    throw new RuntimeException("This book is already issued");
                }
            }
        });

        Ticket ticket = new Ticket(customer, bookList);
        allOpenedTickets.add(ticket);
        return ticket;
    }

    private Boolean validateThisCustomer(Customer customer) {
        return allCustomers.contains(customer);
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

    public Set<Ticket> getAllOpenedTickets() {
        return allOpenedTickets;
    }

    public Set<Ticket> getAllClosedTickets() {
        return allClosedTickets;
    }
}
