package uk.co.payr.payremailapi.email.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationNewEvent {
    private String timestamp;
    private String message;
    private String service;
}
