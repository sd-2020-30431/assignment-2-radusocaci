package com.wasteless.sd.Service;

import com.wasteless.sd.Model.NotificationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationService implements ApplicationListener<NotificationEvent> {
    private String message;

    @Override
    public void onApplicationEvent(NotificationEvent event) {
        message = event.getMessage();
    }

    public String getMessage() {
        return message;
    }
}
