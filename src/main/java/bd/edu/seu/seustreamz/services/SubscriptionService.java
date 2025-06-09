package bd.edu.seu.seustreamz.services;

import bd.edu.seu.seustreamz.interfaces.SubscriptionInterface;
import bd.edu.seu.seustreamz.models.Subscription;
import bd.edu.seu.seustreamz.models.User;
import bd.edu.seu.seustreamz.utils.ConnectionSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionService implements SubscriptionInterface {

    @Override
    public void insertSubscription(Subscription subscription) {
        int id = subscription.getId();
        String userEmail = subscription.getUserEmail();
        String packageName = subscription.getPackageName();
        double price = subscription.getPrice();
        int duration = subscription.getDuration();
        String status = subscription.getStatus();
        LocalDateTime startDateTime = subscription.getStartDateTime();
        LocalDateTime endDateTime = subscription.getEndDateTime();

        String query = "INSERT INTO subscriptions (id, userEmail, packageName, price, duration, status, startDate, endDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection connection = ConnectionSingleton.getConnection();
            PreparedStatement pt = connection.prepareStatement(query);

            pt.setInt(1, id);
            pt.setString(2, userEmail);
            pt.setString(3, packageName);
            pt.setDouble(4, price);
            pt.setInt(5, duration);
            pt.setString(6, status);
            pt.setString(7, startDateTime.toString());
            pt.setString(8, endDateTime.toString());

            pt.executeUpdate();
            System.out.println("Inserted subscription into database");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Subscription getSubscriptionById(int id) {
        return null;
    }

    @Override
    public List<Subscription> getAllSubscriptions() {
        List<Subscription> subscriptions = new ArrayList<>();
        try {
            Connection connection = ConnectionSingleton.getConnection();
            String query = "SELECT * FROM subscriptions";
            PreparedStatement pt = connection.prepareStatement(query);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String userEmail = rs.getString("userEmail");
                String packageName = rs.getString("packageName");
                double price = rs.getDouble("price");
                int duration = rs.getInt("duration");
                String status = rs.getString("status");
                LocalDateTime startDateTime = LocalDateTime.parse(rs.getString("startDate"));
                LocalDateTime endDateTime = LocalDateTime.parse(rs.getString("endDate"));


                subscriptions.add(new Subscription(id, userEmail, packageName, price, duration, status, startDateTime, endDateTime));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return subscriptions;
    }

    @Override
    public List<Subscription> getUserSubscriptionsByEmail(String email) {
        List<Subscription> subscriptions = new ArrayList<>();
        try {
            Connection connection = ConnectionSingleton.getConnection();
            String query = "SELECT * FROM subscriptions WHERE userEmail = ?";
            PreparedStatement pt = connection.prepareStatement(query);
            pt.setString(1, email);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String userEmail = rs.getString("userEmail");
                String packageName = rs.getString("packageName");
                double price = rs.getDouble("price");
                int duration = rs.getInt("duration");
                String status = rs.getString("status");
                LocalDateTime startDateTime = LocalDateTime.parse(rs.getString("startDate"));
                LocalDateTime endDateTime = LocalDateTime.parse(rs.getString("endDate"));


                subscriptions.add(new Subscription(id, userEmail, packageName, price, duration, status, startDateTime, endDateTime));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return subscriptions;
    }

    @Override
    public int getUserSubscriptionsCount(String email) {
        int count = 0;
        String query = "SELECT COUNT(*) FROM subscriptions WHERE userEmail = ?";

        try {
            Connection connection = ConnectionSingleton.getConnection();
            PreparedStatement pt = connection.prepareStatement(query);
            pt.setString(1, email);
            ResultSet rs = pt.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    @Override
    public int getSubscriptionsCount() {
        int count = 0;
        String query = "SELECT COUNT(*) FROM subscriptions";

        try {
            Connection connection = ConnectionSingleton.getConnection();
            PreparedStatement pt = connection.prepareStatement(query);
            ResultSet rs = pt.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }
}
