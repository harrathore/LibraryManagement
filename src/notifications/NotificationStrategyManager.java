package notifications;

import enums.NOTIFICATION_PREFERENCE_ENUM;

public class NotificationStrategyManager {
    private static NotificationStrategyManager strategyManager = new NotificationStrategyManager();
    private NotificationStrategyManager(){

    }

    public static NotificationStrategyManager getStrategyManager(){
        return strategyManager;
    }

    public NotificationServiceInterface decideNotificationService(NOTIFICATION_PREFERENCE_ENUM notificationPreferenceEnum){
        if(notificationPreferenceEnum.equals(NOTIFICATION_PREFERENCE_ENUM.MOBILE)){
            return new MobileNotification();
        }else{
            return new EmailNotification();
        }
    }
}
