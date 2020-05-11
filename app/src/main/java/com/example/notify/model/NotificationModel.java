package com.example.notify.model;

public class NotificationModel {
    private String title;
    private String message;
    private long notificationTimeStamp;

    public long getNotificationTimeStamp() {
        return notificationTimeStamp;
    }

    public void setNotificationTimeStamp(long notificationTimeStamp) {
        this.notificationTimeStamp = notificationTimeStamp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
