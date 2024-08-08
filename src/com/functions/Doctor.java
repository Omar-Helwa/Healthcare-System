package com.functions;

import com.database.Database;
import com.database.DatabaseConnector;

import java.sql.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Doctor extends Person implements Database {

    private String specialty;
    private static int lastId = loadLastId();
    private final int ID;
    private Set<Doctor> doctors = new HashSet<>();
    private HashMap<Integer, Doctor> doctors1= new HashMap<>();

    public Doctor(String name, String address, String phoneNumber, String specialty) {
        super(name, address, phoneNumber);
        synchronized (Person.class){
        ID = lastId++;
        this.specialty = specialty;
        addDoctor(this);
        }
    }

    public String getSpecialty() {
        return specialty;
    }


    public int getID() {
        return ID;
    }

    public Set<Doctor> getDoctors() {
        return doctors;
    }

    private void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    // Method to edit doctor info
//    public static void editDoctor(int id, String name, String address, String phoneNumber, String gender, String specialty) {
//        for (Doctor doctor : doctors) {
//            if (doctor.id == id) {
//                doctor.name = name;
//                doctor.address = address;
//                doctor.phoneNumber = phoneNumber;
//                doctor.gender = gender;
//                doctor.specialty = specialty;
//                return;
//            }
//        }
//    }

@Override
    public  <T extends Person> boolean save() {
        DatabaseConnector.connect();
        String query = "SELECT * FROM doctors";
        String sql = " insert into doctors (idDoctors, name, address, phoneNumber, specialty)"
                + " values (?, ?, ?, ?, ?)";
        try (var connection = DatabaseConnector.connection();
             Statement statement = connection.createStatement();
             PreparedStatement preparedStmt = connection.prepareStatement(sql)
        ) {
                for(var doctor: doctors) {
                    ResultSet resultSet = statement.executeQuery(query);

                    while (resultSet.next()) {

                        if (resultSet.getInt("idDoctors") == (doctor.getID())) {
                            System.out.println("Record already exists");
                        }
                    }
                    preparedStmt.setInt(1, doctor.getID());
                    preparedStmt.setString(2, doctor.name);
                    preparedStmt.setString(3, doctor.address);
                    preparedStmt.setString(4, doctor.phoneNumber);
                    preparedStmt.setString(5, doctor.getSpecialty());
                    preparedStmt.execute();
                }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

@Override
    public <T extends Person> boolean delete(T doctor) {
        DatabaseConnector.connect();
        String query = "SELECT * FROM doctors";
        String qry = "DELETE FROM doctors WHERE idDoctors= '%s'".formatted(doctor.getID());
        try (var connection = DatabaseConnector.connection();
             Statement statement = connection.createStatement();
             PreparedStatement preparedStatement = connection.prepareStatement(qry);
        ) {

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                if ((resultSet.getInt("idDoctors") == (doctor.getID()))) {
                    preparedStatement.execute();
                    System.out.println("Record deleted");
                    return true;
                }
            }
            System.out.println("record doesn't exist");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }


    private static int loadLastId() {
        DatabaseConnector.connect();
        int lastId = 1; // Default value if loading fails
        String query = "SELECT MAX(idDoctors) AS idDoctors FROM doctors";
        try (Connection conn = DatabaseConnector.connection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    lastId = rs.getInt("idDoctors");
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastId;
    }

}