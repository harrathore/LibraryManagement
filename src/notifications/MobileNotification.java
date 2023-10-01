package notifications;

import users.Customer;

import java.util.List;

public class MobileNotification implements NotificationServiceInterface{

    @Override
    public String notifyCustomer(Customer customer) {
        customer.notifyMeViaMobile();
        return "Notified";
    }
}
