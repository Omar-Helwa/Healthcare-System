package com.functions;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Patient extends Person{
    private String gender;
    private String symptoms;
    private String paymentMethod;
    private String diagnosis;
    private boolean emergency;
    private int roomNumber;
    private static int lastId = 1;
    private final int ID = lastId++;
    protected List<Doctor> doctorInCharge;

    private final static String CONN_STRING = "jdbc:mysql://localhost:3306/healthcare";
    private final static String username = "root";
    private final static String password = "Omar2005Helwa";
    private static MysqlDataSource dataSource = new MysqlDataSource();

    public Patient(String name, String address, String phoneNumber, String gender, String symptoms, String paymentMethod, String diagnosis, int roomNumber) {
        super(name, address, phoneNumber);
        this.gender = gender;
        this.symptoms = symptoms;
        this.paymentMethod = paymentMethod;
        this.diagnosis = diagnosis;
        this.roomNumber = roomNumber;
        this.doctorInCharge = new ArrayList<>();
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

    
    public boolean createAppointment(Doctor doctor, List<Appointment> appointments) {
        if (doctor == null || !HospitalManagement.getInstance().doctorExists(doctor) || !HospitalManagement.getInstance().patientExists(this)) {
            return false;
        }

        Appointment appointment = new Appointment(this, doctor);
        appointments.add(appointment);
        return true;
    }

    public <T extends Patient> int addP(T patient) {
        dataSource.setURL(CONN_STRING);
        String query = "SELECT * FROM patients";
        String sql = " insert into patients (name, address, phoneNumber, gender, symptoms, paymentMethod, diagno, emergency, roomNumber)"
                + " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (var connection = dataSource.getConnection(
                username, password);
             Statement statement = connection.createStatement();
             PreparedStatement preparedStmt = connection.prepareStatement(sql)
        ) {

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                if (resultSet.getString("idpatients" ).equals(patient.getID())) {
                    System.out.println("Record already exists" );
                    return 0;
                }
            }

            preparedStmt.setString(1, patient.name);
            preparedStmt.setString(2, patient.address);
            preparedStmt.setString(3, patient.phoneNumber);
            preparedStmt.setString(4, patient.getGender());
            preparedStmt.setString(5, patient.getSymptoms());
            preparedStmt.setString(6, patient.getPaymentMethod());
            preparedStmt.setString(7, patient.getDiagnosis());
            preparedStmt.setBoolean(8, patient.isEmergency());
//            preparedStmt.setInt(9, patient.getRoomNumber());
            preparedStmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 1;
    }

    public <T extends Patient> int delete(T patient) {
        dataSource.setURL(CONN_STRING);
        String query = "SELECT * FROM patients";
        String qry = "DELETE FROM patients WHERE idpatients= '%s'".formatted(patient.getID());
        try (var connection = dataSource.getConnection(
                username, password);
             Statement statement = connection.createStatement();
             PreparedStatement preparedStatement = connection.prepareStatement(qry);
        ) {

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                if ((resultSet.getString("idpatients").equals(patient.getID()))) {
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


    public List<Doctor> getDoctorInCharge() {
        return doctorInCharge;
    }

    public boolean addDoctorInCharge(Doctor doctor) {
        if (doctor == null) return false;
        return doctorInCharge.add(doctor);
    }

}
