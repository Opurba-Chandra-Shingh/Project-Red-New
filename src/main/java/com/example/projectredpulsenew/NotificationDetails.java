package com.example.projectredpulsenew;

public class NotificationDetails {

    private String clickerEmail;
    private String clickerPassword;

    private String receiverEmail;
    private String receiverPassword;

    private String notiType;

    public NotificationDetails(String clickerEmail, String clickerPassword,
                               String receiverEmail, String receiverPassword,
                               String notiType) {
        this.clickerEmail = clickerEmail;
        this.clickerPassword = clickerPassword;
        this.receiverEmail = receiverEmail;
        this.receiverPassword = receiverPassword;
        this.notiType = notiType;
    }

    public String getClickerEmail() { return clickerEmail; }
    public String getClickerPassword() { return clickerPassword; }

    public String getReceiverEmail() { return receiverEmail; }
    public String getReceiverPassword() { return receiverPassword; }

    public String getNotiType() { return notiType; }
}

