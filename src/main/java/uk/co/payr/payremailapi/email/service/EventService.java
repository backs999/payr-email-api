package uk.co.payr.payremailapi.email.service;

import uk.co.payr.payremailapi.email.model.NotificationNewEvent;
import uk.co.payr.payremailapi.email.model.UserUpdateEvent;

public interface EventService {

    void sendUserUpdate(final UserUpdateEvent userUpdateEvent);

    void sendNotificationNew(final NotificationNewEvent notificationNewEvent);
}
