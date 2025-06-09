package bd.edu.seu.seustreamz.models;

import bd.edu.seu.seustreamz.services.SubscriptionService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String email;
    private String password;
    private String profileImageUrl;
    private String currentSubscription;
    private List<String> subscriptionHistory;
    private List<String> watchHistory;
    private List<String> favorites;
    private LocalDateTime createdAt;

    // Constructors
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.profileImageUrl = "";
        this.currentSubscription = "";
        this.subscriptionHistory = new ArrayList<>();
        this.watchHistory = new ArrayList<>();
        this.favorites = new ArrayList<>();
        this.createdAt = LocalDateTime.now();
    }

    public User(String name, String email, String password, String profileImageUrl, String currentSubscription, List<String> subscriptionHistory, List<String> watchHistory, List<String> favorites, LocalDateTime createdAt) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.profileImageUrl = profileImageUrl;
        this.currentSubscription = currentSubscription;
        this.subscriptionHistory = subscriptionHistory;
        this.watchHistory = watchHistory;
        this.favorites = favorites;
        this.createdAt = createdAt;
    }


    // Methods
    public int getTotalSubscriptionsCount() {
        SubscriptionService subscriptionService = new SubscriptionService();
        return subscriptionService.getUserSubscriptionsCount(this.email);
    }

    public double getTotalRevenue() {
        double revenue = 0;
        SubscriptionService subscriptionService = new SubscriptionService();
        List<Subscription> mySubscriptions = subscriptionService.getUserSubscriptionsByEmail(this.email);
        for (Subscription subscription : mySubscriptions) {
            revenue += subscription.getPrice();
        }
        return revenue;
    }

    public String getCurrentSubscriptionIfExists() {
        if (this.currentSubscription.isEmpty() || this.currentSubscription == null || this.currentSubscription.equals("")) {
            return "None";
        } else return this.currentSubscription;
    }

    // Getters Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getCurrentSubscription() {
        return currentSubscription;
    }

    public void setCurrentSubscription(String currentSubscription) {
        this.currentSubscription = currentSubscription;
    }

    public List<String> getSubscriptionHistory() {
        return subscriptionHistory;
    }

    public void setSubscriptionHistory(List<String> subscriptionHistory) {
        this.subscriptionHistory = subscriptionHistory;
    }

    public List<String> getWatchHistory() {
        return watchHistory;
    }

    public void setWatchHistory(List<String> watchHistory) {
        this.watchHistory = watchHistory;
    }

    public List<String> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<String> favorites) {
        this.favorites = favorites;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
