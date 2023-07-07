package com.electrolux.azure.notificationhub.model;

import lombok.Getter;
import lombok.Setter;

public class NotificationPayload {
    @Getter
    @Setter
    private String username, message ;

}
