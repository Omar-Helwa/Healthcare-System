package com.database;

import com.functions.Doctor;
import com.functions.Patient;
import com.functions.Person;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Set;

public class Handler {

    HashMap<Integer, Doctor> doctors = new HashMap<>();
    HashMap<Integer, Patient> patients = new HashMap<Integer, Patient>();

    public  <T extends Person> boolean load() {
        DatabaseConnector.connect();
        String query = "SELECT * FROM doctors";
        String query2 = "SELECT * FROM patients";
        try (var connection = DatabaseConnector.connection();
             Statement statement = connection.createStatement()) {

                ResultSet resultSet = statement.executeQuery(query);
                ResultSet resultSet2 = statement.executeQuery(query2);

                while (resultSet.next()) {
                    doctors.put(resultSet.getInt(1),new Doctor(resultSet.getString("name"),
                            resultSet.getString("address"),resultSet.getString("phoneNumber"), resultSet.getNString("specialty")));

                    patients.put(resultSet2.getInt(1),new Patient(resultSet2.getString("name"),
                            resultSet2.getString("address"),resultSet2.getString("phoneNumber"), resultSet2.getString("gender"),
                            resultSet2.getString("symptoms"),resultSet2.getString("paymentMethod"),
                            resultSet2.getString("diagno"), resultSet2.getBoolean("emergency"),
                            resultSet2.getInt("roomNumber")));
                }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

}
