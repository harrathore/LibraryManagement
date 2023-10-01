import enums.NOTIFICATION_PREFERENCE_ENUM;
import library.Library;
import models.Ticket;
import users.Admin;
import users.Customer;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to library management system !");

        Admin admin = new Admin("Admin");
        admin.createAndAddBooks();

        Thread[] arr = new Thread[10];
        for(int i=0; i<10; i++){
            int finalI = i;
            Thread t = new Thread(()-> {
                Customer customer = new Customer("Customer1"+String.valueOf(finalI), NOTIFICATION_PREFERENCE_ENUM.EMAIL);
                customer.issueAndReturnSomeBooks();
            });
            arr[i] = t;
        }
        for(int i=0; i<10; i++){
            arr[i].start();
        }


        try{
            Thread.sleep(5000);
            Set<Ticket> allOpenTickets = Library.getLibrary().getAllOpenedTickets();
            allOpenTickets.forEach(tc->{
                System.out.println(tc);
                System.out.println(tc.getCustomer().getUserName());
                System.out.println(tc.getBooksIssued().size());
            });

            System.out.println("****************************");

            Set<Ticket> allClosedTickets = Library.getLibrary().getAllClosedTickets();
            allClosedTickets.forEach(tc->{
                System.out.println(tc);
                System.out.println(tc.getCustomer().getUserName());
                System.out.println(tc.getBooksIssued().size());
            });

        }catch (Exception e){
            System.out.println(e.getMessage());
        }


    }
}