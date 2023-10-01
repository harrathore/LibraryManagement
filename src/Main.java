import enums.NOTIFICATION_PREFERENCE_ENUM;
import users.Admin;
import users.Customer;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to library management system !");

        Admin admin = new Admin("Admin");
        admin.createAndAddBooks();

        Customer customer = new Customer("Customer", NOTIFICATION_PREFERENCE_ENUM.EMAIL);
        customer.issueAndReturnSomeBooks();

        Customer customer2 = new Customer("Customer2", NOTIFICATION_PREFERENCE_ENUM.MOBILE);
        customer2.issueAndReturnSomeBooks();

        admin.notifyPeople();
    }
}