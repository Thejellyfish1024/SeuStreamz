package bd.edu.seu.seustreamz.interfaces;

import bd.edu.seu.seustreamz.models.Subscription;

import java.util.List;

public interface SubscriptionInterface {
    void insertSubscription(Subscription subscription);
    Subscription getSubscriptionById(int id);
    List<Subscription> getAllSubscriptions();
    List<Subscription> getUserSubscriptionsByEmail(String email);
    int getUserSubscriptionsCount(String email);
    int getSubscriptionsCount();

}
