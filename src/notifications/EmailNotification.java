package notifications;

import users.Customer;


public class EmailNotification implements NotificationServiceInterface{
    @Override
    public String notifyCustomer(Customer customer) {
        customer.notifyMeViaEmail();
        return null;
    }
}
