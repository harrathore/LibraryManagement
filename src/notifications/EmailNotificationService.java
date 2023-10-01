package notifications;

import users.Customer;


public class EmailNotificationService implements NotificationServiceInterface{
    @Override
    public String notifyCustomer(Customer customer) {
        customer.notifyMeViaEmail();
        return null;
    }
}
