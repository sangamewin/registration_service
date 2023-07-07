package com.electrolux.pushnotification.registrationservice.controller;


import com.electrolux.azure.notificationhub.model.DeviceRegistration;
import com.electrolux.azure.notificationhub.model.NotificationPayload;
import com.electrolux.azure.notificationhub.model.Notifications;
import com.windowsazure.messaging.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/")
public class RegistrationController {

    private final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    private NotificationHubClient hub;
    public  RegistrationController() {
        hub = Notifications.Instance.getHub();
    }

    //TODO: Make all the calls Async functions, define common FutureCallback handler and attach to the calls.
   // @GetMapping("/register")
    //earlier this was parameter @RequestParam(required = false)
    private String createRegistration(String handle) throws NotificationHubsException {
        String newRegistrationId = null;

         // make sure there are no existing registrations for this push handle (used for iOS and Android)
        if (handle != null)
        {
            CollectionResult allRegs = hub.getRegistrations();

            CollectionResult registrations =  hub.getRegistrationsByChannel(handle);

            CollectionResult registrations1 =  hub.getRegistrationsByTag("username:thakusan");

            for (Registration registration : registrations.getRegistrations())
            {

                if (newRegistrationId == null)
                {
                    newRegistrationId = registration.getRegistrationId();
                }
                else
                {
                     hub.deleteRegistration(registration);
                }
            }
        }

        if (newRegistrationId == null)
            newRegistrationId = hub.createRegistrationId();

        return newRegistrationId;
    }



//    @PostMapping("/employees")
//    @GetMapping("/employees/{id}")
//    @PutMapping("/employees/{id}")
//    @DeleteMapping("/employees/{id}")

    @PutMapping("/register")
    public String registerDeviceWithToken(@RequestBody DeviceRegistration deviceRegistration) throws NotificationHubsException {

        Registration registration = null;

        switch (deviceRegistration.Platform)
        {
            case "mpns":
                registration = new MpnsRegistration();
                break;
            case "wns":
                registration = new WindowsRegistration();
                break;
            case "apns":
                registration = new AppleRegistration(deviceRegistration.getHandle());
                break;
            case "fcm":
                registration = new FcmRegistration(deviceRegistration.getHandle());
                break;
            default:
                return "Invalid Platform";
        }

        registration.setRegistrationId(createRegistration(deviceRegistration.getHandle()));

        // add check if user is allowed to add these tags
        Set<String> tags = new HashSet<>();
        tags.add("username:"+deviceRegistration.getUsername());
        registration.setTags(tags);

        registration = hub.upsertRegistration(registration);

        return "device registered successfully" + registration.getRegistrationId();
    }

    @PutMapping("/installation")
    public String registerInstallationWithToken(@RequestBody DeviceRegistration deviceRegistration) throws NotificationHubsException {

        Installation installation;

        switch (deviceRegistration.Platform)
        {

            case "apns":
                installation = new AppleInstallation(deviceRegistration.getHandle());
                break;
            case "fcm":
                installation = new FcmInstallation(deviceRegistration.getHandle());
                break;
            default:
                return "Invalid Platform";
        }

        installation.setInstallationId(deviceRegistration.getUsername());
        installation.setUserId(deviceRegistration.getUsername());
        installation.setPushChannel("mypushchannel");

        hub.createOrUpdateInstallation(installation);

        return "device registered successfully" + installation.getInstallationId();
    }

    @PostMapping ("/sendNotification")
    public String sendNotification (@RequestBody NotificationPayload payload) throws NotificationHubsException {

        CollectionResult registrations = hub.getRegistrationsByTag("username:"+payload.getUsername());

        Notification nofi;
        NotificationOutcome no = null;


        Set<String> tags = new HashSet<String>();
        tags.add("username:"+payload.getUsername());

                    nofi = Notification.createFcmNotification("{\"notification\":{\"title\":\"NotificationHubTestNotification\",\"body\":\"ThisisasamplenotificationdeliveredbyAzureNotificationHubs.\"},\"data\":{\"property1\":\"value1\",\"property2\":42}}");
                    no = hub.sendNotification(nofi, tags);

                    System.out.println("Notification id" + no.getNotificationId());

                    nofi = Notification.createAppleNotification("{\"aps\":{\"alert\":\"NotificationHubtestnotification\"}}");
                    no = hub.sendNotification(nofi, tags);

        return "Message sent successfully: " + no.getNotificationId();
    }



    @PostMapping ("/sendInstallationNotification")
    public String sendNotificationInstallation (@RequestBody NotificationPayload payload) throws NotificationHubsException {

        Notification nofi;
        NotificationOutcome no = null;

        Set<String> tags = new HashSet<String>();
        tags.add("$UserId:{"+payload.getUsername()+"}");

        nofi = Notification.createFcmNotification("{\"notification\":{\"title\":\"NotificationHubTestNotification\",\"body\":\"This is tagged notification.\"},\"data\":{\"property1\":\"value1\",\"property2\":42}}");
        no = hub.sendNotification(nofi, tags);

        System.out.println("Notification id" + no.getNotificationId());

        nofi = Notification.createAppleNotification("{\"aps\":{\"alert\":\"this is tagged notification\"}}");
        no = hub.sendNotification(nofi, tags);

        no=hub.sendNotification(nofi, tags);
        return "Message sent successfully: " + no.getNotificationId();
    }

    @PostMapping("deleteAll")
        public String deleteAll()throws NotificationHubsException {
            CollectionResult result = hub.getRegistrations();
        for (Registration r: result.getRegistrations()) {
            hub.deleteRegistration(r);
        }
            return "All registrations deleted";
        }


        @PostMapping("/selectAll")
        public String selectAll()throws NotificationHubsException {
        CollectionResult result = hub.getRegistrations();

        return "All registrations selected";
    }

}
