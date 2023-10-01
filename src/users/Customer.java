package users;

import enums.NOTIFICATION_PREFERENCE_ENUM;
import library.Book;
import library.Library;
import library.CustomerInterface;
import library.Ticket;
import models.SearchBookDto;
import notifications.EmailObserver;
import notifications.MobileObserver;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Customer extends User implements EmailObserver, MobileObserver {
    private CustomerInterface customerInterface;
    private NOTIFICATION_PREFERENCE_ENUM notificationPreference;
    private Set<Ticket> allOpenedTickets;
    private Set<Ticket> allClosedTickets;

    public Customer(String userName, NOTIFICATION_PREFERENCE_ENUM notificationPreference) {
        super(userName);
        this.customerInterface = Library.getLibrary();
        allOpenedTickets = new HashSet<>();
        allClosedTickets = new HashSet<>();
        this.notificationPreference = notificationPreference;
    }

    public String issueAndReturnSomeBooks(){
        System.out.println("I am inside issueAndReturn book");
        List<Book> allSearchedBooks = customerInterface.searchBooksFromLibrary(new SearchBookDto("Gita",
                "Shila Prabhupad"));
        Ticket ticket = customerInterface.issueBookToMe(this, allSearchedBooks);
        allOpenedTickets.add(ticket);

        //Returning the books
//        allOpenedTickets.remove(ticket);
//        Ticket finalTicket = customerInterface.returnBooks(ticket);
//        allClosedTickets.add(finalTicket);
        return "I had issued some books and returned them";
    }


    @Override
    public String notifyMeViaEmail() {
        System.out.println("Your books are due, please submit this on time - via email");
        return "I am notified via email";
    }

    @Override
    public String notifyMeViaMobile() {
        System.out.println("Your books are due, please submit this on time - via mobile");
        return "I am notified via mobile";
    }

    public NOTIFICATION_PREFERENCE_ENUM getNotificationPreference() {
        return notificationPreference;
    }

    public void setNotificationPreference(NOTIFICATION_PREFERENCE_ENUM notificationPreference) {
        this.notificationPreference = notificationPreference;
    }
}
