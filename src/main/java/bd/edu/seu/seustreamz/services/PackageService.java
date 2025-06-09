package bd.edu.seu.seustreamz.services;

import bd.edu.seu.seustreamz.interfaces.PackageInterface;
import bd.edu.seu.seustreamz.models.Packages;
import bd.edu.seu.seustreamz.utils.ConnectionSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PackageService implements PackageInterface {
    @Override
    public void insertPackage(Packages newPackage) {
        String name = newPackage.getName();
        double price = newPackage.getPrice();
        int duration = newPackage.getDuration();

        try {
            Connection connection = ConnectionSingleton.getConnection();
            String query = "INSERT INTO packages (name, price, duration) VALUES (?, ?, ?)";
            PreparedStatement pt = connection.prepareStatement(query);

            pt.setString(1, name);
            pt.setDouble(2, price);
            pt.setInt(3, duration);
            pt.executeUpdate();
            System.out.println("Inserted package into database");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Packages> getPackages() {
        return List.of();
    }

    @Override
    public void updatePackage(Packages newPackage) {
        String name = newPackage.getName();
        double price = newPackage.getPrice();
        int duration = newPackage.getDuration();

        try {
            Connection connection = ConnectionSingleton.getConnection();
            String query = "UPDATE packages SET price = ?, duration = ? WHERE name = ?";
            PreparedStatement pt = connection.prepareStatement(query);

            pt.setDouble(1, price);
            pt.setInt(2, duration);
            pt.setString(3, name);
            pt.executeUpdate();

            int updatedRows = pt.executeUpdate();
            System.out.println("Package updated successfully");

        } catch (SQLException e) {
            throw new RuntimeException("Failed to update package", e);
        }
    }

    @Override
    public Packages getPackage(String packageName) {
        try {
            Connection connection = ConnectionSingleton.getConnection();
            String query = "SELECT * FROM packages WHERE name = ?";
            PreparedStatement pt = connection.prepareStatement(query);
            pt.setString(1, packageName);
            ResultSet rs = pt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int duration = rs.getInt("duration");
                return new Packages(name, price, duration);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
