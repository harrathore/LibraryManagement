package notifications;

import users.Customer;

public class MobileNotificationService implements NotificationServiceInterface{

    @Override
    public String notifyCustomer(Customer customer) {
        customer.notifyMeViaMobile();
        return "Notified";
    }
}
