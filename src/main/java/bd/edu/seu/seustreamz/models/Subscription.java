package bd.edu.seu.seustreamz.models;

import bd.edu.seu.seustreamz.services.SubscriptionService;

import java.time.LocalDateTime;

public class Subscription {
    private int id;
    private String userEmail;
    private String packageName;
    private double price;
    private int duration;
    private String status;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    // Constructor
    public Subscription( String userEmail, String packageName, double price, int duration) {
        this.id = uniqueId();
        this.userEmail = userEmail;
        this.packageName = packageName;
        this.price = price;
        this.duration = duration;
        this.status = "active";
        this.startDateTime = LocalDateTime.now();
        this.endDateTime = LocalDateTime.now().plusDays(duration);
    }

    public Subscription(int id, String userEmail, String packageName, double price, int duration, String status, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.id = id;
        this.userEmail = userEmail;
        this.packageName = packageName;
        this.price = price;
        this.duration = duration;
        this.status = status;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    // Empty constructor (optional for frameworks)
    public Subscription() {
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserId(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    // Methods
    int uniqueId (){
        SubscriptionService subscriptionService = new SubscriptionService();
        return subscriptionService.getSubscriptionsCount() + 1;
    }

    public boolean isActive() {
        LocalDateTime now = LocalDateTime.now();
        return (status.equalsIgnoreCase("active") && (startDateTime.isBefore(now) || startDateTime.isEqual(now)) && endDateTime.isAfter(now));
    }

}

