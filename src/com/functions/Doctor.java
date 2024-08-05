package com.functions;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Doctor extends Person {

    private String specialty;
    private static int lastId = 1;
    private final int ID = lastId++;

    private final static String CONN_STRING = "jdbc:mysql://localhost:3306/healthcare";
    private final static String username = "root";
    private final static String password = "Omar2005Helwa";
    private static MysqlDataSource dataSource = new MysqlDataSource();

    public Doctor(String name, String address, String phoneNumber, String specialty) {
        super(name, address, phoneNumber);
        this.specialty = specialty;
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


    public <T extends Doctor> int addD(T doctor) {
        dataSource.setURL(CONN_STRING);
        String query = "SELECT * FROM doctors";
        String sql = " insert into doctors (name, address, phoneNumber, specialty)"
                + " values (?, ?, ?, ?)";
        try (var connection = dataSource.getConnection(
                username, password);
             Statement statement = connection.createStatement();
             PreparedStatement preparedStmt = connection.prepareStatement(sql)
        ) {

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                if (resultSet.getString("idDoctors" ).equals(doctor.getID())) {
                    System.out.println("Record already exists" );
                    return 1;
                }
            }

            preparedStmt.setString(1, doctor.name);
            preparedStmt.setString(2, doctor.address);
            preparedStmt.setString(3, doctor.phoneNumber);
            preparedStmt.setString(4, doctor.getSpecialty());
            preparedStmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public <T extends Doctor> int delete(T doctor) {
        dataSource.setURL(CONN_STRING);
        String query = "SELECT * FROM doctors";
        String qry = "DELETE FROM doctors WHERE idDoctors= '%s'".formatted(doctor.getID());
        try (var connection = dataSource.getConnection(
                username, password);
             Statement statement = connection.createStatement();
             PreparedStatement preparedStatement = connection.prepareStatement(qry);
        ) {

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                if ((resultSet.getString("idDoctor").equals(doctor.getID()))) {
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
}