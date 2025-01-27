package uk.co.payr.payremailapi.email.service;

import lombok.AllArgsConstructor;
import lombok.extern.flogger.Flogger;
import org.springframework.stereotype.Service;
import uk.co.payr.payremailapi.api.UserControllerApi;
import uk.co.payr.payremailapi.email.model.NotificationNewEvent;
import uk.co.payr.payremailapi.email.model.UserUpdateEvent;
import uk.co.payr.payremailapi.email.utils.DateUtils;
import uk.co.payr.payremailapi.invoker.ApiClient;
import uk.co.payr.payremailapi.invoker.ApiException;
import uk.co.payr.payremailapi.model.User;

@Service
@Flogger
@AllArgsConstructor
public class NewRegistrationEmailService implements  EmailService {

    private final EventService eventService;

    @Override
    public void send(String userId) {
        log.atInfo().log("Going to look up user with ID " + userId);

        try {
            final var client = new ApiClient();
            client.setBasePath("http://payr-users-api:8080");
            final var userControllerApi = new UserControllerApi(client);
            final var user = userControllerApi.findById(userId);

            log.atInfo().log("Fetched user from user API with email: " + user.getEmail());
            log.atInfo().log("Sending new registration email to " + user.getEmail());

            eventService.sendNotificationNew(NotificationNewEvent.builder()
                    .message("New registration email sent to " + user.getEmail())
                    .service("payr-email-api")
                    .timestamp(DateUtils.nowAsString())
                    .build());

            eventService.sendUserUpdate(UserUpdateEvent.builder()
                    .userId(userId)
                    .action("registration-email-sent")
                    .build());
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }
}
