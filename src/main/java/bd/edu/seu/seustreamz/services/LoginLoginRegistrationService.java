package bd.edu.seu.seustreamz.services;

import bd.edu.seu.seustreamz.interfaces.LoginRegistrationInterface;
import bd.edu.seu.seustreamz.models.User;
import bd.edu.seu.seustreamz.utils.ConnectionSingleton;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

public class LoginLoginRegistrationService implements LoginRegistrationInterface {

    @Override
    public void insertUser(User user) {
        String name = user.getName();
        String email = user.getEmail();
        String password = user.getPassword();
        String profileImageUrl = user.getProfileImageUrl();
        String currentSubscription = user.getCurrentSubscription();
        List<String> subscriptionHistory = user.getSubscriptionHistory();
        List<String> watchHistory = user.getWatchHistory();
        List<String> favorites = user.getFavorites();

        try {
            Connection connection = ConnectionSingleton.getConnection();
            String query = "INSERT INTO users (name, email, password, profileImageUrl, currentSubscription, subscriptionHistory, watchHistory, favorites) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pt = connection.prepareStatement(query);

            pt.setString(1, name);
            pt.setString(2, email);
            pt.setString(3, password);
            pt.setString(4, profileImageUrl);
            pt.setString(5, currentSubscription);
            pt.setString(6, String.join(",", subscriptionHistory));
            pt.setString(7, String.join(",", watchHistory));
            pt.setString(8, String.join(",", favorites));
            pt.executeUpdate();
            System.out.println("Inserted user into database");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getUserByEmail(String email) {
        try{
            Connection connection = ConnectionSingleton.getConnection();
            String query = "SELECT * FROM users WHERE email = ?";
            PreparedStatement pt = connection.prepareStatement(query);
            pt.setString(1, email);
            ResultSet rs = pt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String password = rs.getString("password");
                String profileImageUrl = rs.getString("profileImageUrl");
                String currentSubscription = rs.getString("currentSubscription");
                String subscriptionHistory = rs.getString("subscriptionHistory");
                String watchHistory = rs.getString("watchHistory");
                String favorites = rs.getString("favorites");
                LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();

                List<String> subscriptionHistoryList = List.of(subscriptionHistory.split(","));
                List<String> watchHistoryList = List.of(watchHistory.split(","));
                List<String> favoritesList = List.of(favorites.split(","));

                return new User(name, email, password, profileImageUrl, currentSubscription, subscriptionHistoryList, watchHistoryList, favoritesList, createdAt);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
