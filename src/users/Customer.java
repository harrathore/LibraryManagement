package users;

import enums.NOTIFICATION_PREFERENCE_ENUM;
import exceptions.GenericCustomException;
import library.Book;
import library.Library;
import library.LibraryInterfaceForCustomer;
import models.Ticket;
import models.SearchBookRequestDto;
import notifications.EmailObserver;
import notifications.MobileObserver;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Customer extends User implements EmailObserver, MobileObserver {
    private LibraryInterfaceForCustomer libraryInterfaceForCustomer;
    private NOTIFICATION_PREFERENCE_ENUM notificationPreference;
    private Set<Ticket> allOpenedTickets;
    private Set<Ticket> allClosedTickets;

    public Customer(String userName, NOTIFICATION_PREFERENCE_ENUM notificationPreference) {
        super(userName);
        this.libraryInterfaceForCustomer = Library.getLibrary();
        allOpenedTickets = new HashSet<>();
        allClosedTickets = new HashSet<>();
        this.notificationPreference = notificationPreference;
        libraryInterfaceForCustomer.registerCustomerToLibrary(this);
    }

    public String issueAndReturnSomeBooks(){
        System.out.println("I am inside issueAndReturn book");
        SearchBookRequestDto searchBookRequestDto = new SearchBookRequestDto("Gita", "Shila Prabhupad");
        List<Book> allSearchedBooks = libraryInterfaceForCustomer.searchBooksFromLibrary(searchBookRequestDto);
        Ticket ticket = null;
        try{
             ticket = libraryInterfaceForCustomer.issueBookToMe(this, allSearchedBooks);
             allOpenedTickets.add(ticket);
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }


        //Returning the books
        if(ticket!=null){
            allOpenedTickets.remove(ticket);
            Ticket finalTicket = libraryInterfaceForCustomer.returnBooks(ticket);
            allClosedTickets.add(finalTicket);
        }
        return "I had issued some books and returned them...";
    }

    @Override
    public void notifyMeViaEmail() {
        System.out.println("Your books are due, please submit this on time - via email");
    }

    @Override
    public void notifyMeViaMobile() {
        System.out.println("Your books are due, please submit this on time - via mobile");
    }

    public NOTIFICATION_PREFERENCE_ENUM getNotificationPreference() {
        return notificationPreference;
    }
}
