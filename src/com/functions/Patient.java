package com.functions;

import com.database.Database;
import com.database.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Patient extends Person implements Database {
    private String gender;
    private String symptoms;
    private String paymentMethod;
    private String diagnosis;
    private boolean emergency;
    private int roomNumber;
    private static int lastId = loadLastId();
    private final int ID;
    private Set<Patient> patients = new HashSet<>();


    public Patient(String name, String address, String phoneNumber, String gender, String symptoms, String paymentMethod, String diagnosis, int roomNumber) {
        super(name, address, phoneNumber);
        this.gender = gender;
        this.symptoms = symptoms;
        this.paymentMethod = paymentMethod;
        this.diagnosis = diagnosis;
        this.roomNumber = roomNumber;
        ID = lastId++;
        addPatient(this);
    }
    private void addPatient(Patient patient) {
        patients.add(patient);
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public boolean isEmergency() {
        return emergency;
    }

    public void setEmergency(boolean emergency) {
        this.emergency = emergency;
    }

    public int getID() {
        return ID;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public <T extends Person> boolean save() {
        DatabaseConnector.connect();
        String query = "SELECT * FROM patients";
        String sql = " insert into patients (idpatients, name, address, phoneNumber, gender, symptoms, paymentMethod, diagno, emergency, roomNumber)"
                + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (var connection = DatabaseConnector.connection();
             Statement statement = connection.createStatement();
             PreparedStatement preparedStmt = connection.prepareStatement(sql)
        ) {

            for(Patient patient : patients) {
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {

                    if (resultSet.getInt("idpatients") == (patient.getID())) {
                        System.out.println("Record already exists");
                    }
                }
                preparedStmt.setInt(1, patient.getID());
                preparedStmt.setString(2, patient.name);
                preparedStmt.setString(3, patient.address);
                preparedStmt.setString(4, patient.phoneNumber);
                preparedStmt.setString(5, patient.getGender());
                preparedStmt.setString(6, patient.getSymptoms());
                preparedStmt.setString(7, patient.getPaymentMethod());
                preparedStmt.setString(8, patient.getDiagnosis());
                preparedStmt.setBoolean(9, patient.isEmergency());
                preparedStmt.setInt(10, patient.getRoomNumber());
                preparedStmt.execute();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
@Override
    public <T extends Person> boolean delete(T patient) {
        DatabaseConnector.connect();
        String query = "SELECT * FROM patients";
        String qry = "DELETE FROM patients WHERE idpatients= '%s'".formatted(patient.getID());
        try (var connection = DatabaseConnector.connection();
             Statement statement = connection.createStatement();
             PreparedStatement preparedStatement = connection.prepareStatement(qry);
        ) {

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                if ((resultSet.getInt("idpatients") == (patient.getID()))) {
                    System.out.println("Record deleted");
                    preparedStatement.execute();
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
                lastId = rs.getInt("idDoctors") +1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastId;
    }
}
