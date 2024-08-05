package com.functions;

import com.database.Database;
import com.mysql.cj.jdbc.MysqlDataSource;

import javax.print.Doc;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Doctor extends Person {

    private String specialty;
    private static int lastId = loadLastId();
    private final int ID;
    private List<Doctor> doctors = new ArrayList<>();

    public Doctor(String name, String address, String phoneNumber, String specialty) {
        super(name, address, phoneNumber);
        synchronized (Person.class){
        ID = lastId++;
        this.specialty = specialty;
        addDoctor(this);}
    }

    // Getter and setter for specialty

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public int getID() {
        return ID;
    }

    public List<Doctor> getDoctors() {
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


    public  <T extends Doctor> int save() {
        Database.connect();
        String query = "SELECT * FROM doctors";
        String sql = " insert into doctors (idDoctors, name, address, phoneNumber, specialty)"
                + " values (?, ?, ?, ?, ?)";
        try (var connection = Database.connection();
             Statement statement = connection.createStatement();
             PreparedStatement preparedStmt = connection.prepareStatement(sql)
        ) {
                for(var doctor: doctors) {
                    ResultSet resultSet = statement.executeQuery(query);

                    while (resultSet.next()) {

                        if (resultSet.getInt("idDoctors") == (doctor.getID())) {
                            System.out.println("Record already exists");
//                            return 1;
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
        return 0;
    }


    public <T extends Doctor> int delete(T doctor) {
        Database.connect();
        String query = "SELECT * FROM doctors";
        String qry = "DELETE FROM doctors WHERE idDoctors= '%s'".formatted(doctor.getID());
        try (var connection = Database.connection();
             Statement statement = connection.createStatement();
             PreparedStatement preparedStatement = connection.prepareStatement(qry);
        ) {

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                if ((resultSet.getInt("idDoctors") == (doctor.getID()))) {
                    preparedStatement.execute();
                    System.out.println("Record deleted");
                    return 0;
                }
            }
            System.out.println("record doesn't exist");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 1;
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