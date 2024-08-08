package com.functions;

import com.database.DatabaseConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HospitalManagement {

    public <T extends Doctor> void displayDoctors() {
        DatabaseConnector.connect();
        String query = "SELECT * FROM doctors";
        try (var connection = DatabaseConnector.connection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                System.out.printf("%s %s %s %s%n", resultSet.getString("name"), resultSet.getString("address"),
                        resultSet.getString("phoneNumber"), resultSet.getString("specialty"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public <T extends Doctor> void displayPatients() {
        DatabaseConnector.connect();
        String query = "SELECT * FROM patients";
        try (var connection = DatabaseConnector.connection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                System.out.printf("%s %s %s %s %s %s %s %b %d%n", resultSet.getString("name"), resultSet.getString("address"),
                        resultSet.getString("phoneNumber"), resultSet.getString("gender"),
                        resultSet.getString("symptoms"), resultSet.getString("paymentMethod"),
                        resultSet.getString("diagno"), resultSet.getBoolean("emergency"),
                        resultSet.getInt("roomNumber"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
