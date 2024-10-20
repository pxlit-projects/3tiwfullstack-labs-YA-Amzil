package be.pxl.services.controller.client;

import be.pxl.services.domain.NotificationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "NotificationServiceApplication") // -> naam van de service
public interface NotificationClient {

    @PostMapping("/api/notification")
    void sendNotification(@RequestBody NotificationRequest notifictionRequest);
}
