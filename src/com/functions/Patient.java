package com.functions;

import com.database.Database;
import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Patient extends Person{
    private String gender;
    private String symptoms;
    private String paymentMethod;
    private String diagnosis;
    private boolean emergency;
    private int roomNumber;
    private static int lastId = loadLastId();
    private final int ID;
    private List<Patient> patients = new ArrayList<>();


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

    public <T extends Patient> int add(T patient) {
        Database.connect();
        String query = "SELECT * FROM patients";
        String sql = " insert into patients (idpatients, name, address, phoneNumber, gender, symptoms, paymentMethod, diagno, emergency, roomNumber)"
                + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (var connection = Database.connection();
             Statement statement = connection.createStatement();
             PreparedStatement preparedStmt = connection.prepareStatement(sql)
        ) {

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                if (resultSet.getInt("idpatients" ) == (patient.getID())) {
                    System.out.println("Record already exists" );
                    return 0;
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

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 1;
    }

    public <T extends Patient> int delete(T patient) {
        Database.connect();
        String query = "SELECT * FROM patients";
        String qry = "DELETE FROM patients WHERE idpatients= '%s'".formatted(patient.getID());
        try (var connection = Database.connection();
             Statement statement = connection.createStatement();
             PreparedStatement preparedStatement = connection.prepareStatement(qry);
        ) {

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                if ((resultSet.getInt("idpatients") == (patient.getID()))) {
                    System.out.println("Record deleted");
                    preparedStatement.execute();
                    return 1;
                }
            }
            System.out.println("record doesn't exist");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    private static int loadLastId() {
        Database.connect();
        int lastId = 1; // Default value if loading fails
        String query = "SELECT MAX(idDoctors) AS idDoctors FROM doctors";
        try (Connection conn = Database.connection();
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
