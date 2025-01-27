package uk.co.payr.payrusersapi.user.service;

import uk.co.payr.payrusersapi.user.model.event.NotificationEvent;
import uk.co.payr.payrusersapi.user.model.event.OrderError;
import uk.co.payr.payrusersapi.user.model.UserEvent;

public interface EventService {

    void sendOrderError(final OrderError orderError);

    void sendNotification(final NotificationEvent notificationEvent);
}
