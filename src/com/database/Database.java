package com.database;

import com.company.Doctor;
import com.company.Patient;
import com.company.Person;
import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    private final static String CONN_STRING = "jdbc:mysql://localhost:3306/healthcare";
    private final static String username = "root";
    private final static String password = "Omar2005Helwa";
    private static MysqlDataSource dataSource = new MysqlDataSource();


    public static void main(String[] args) {

    }

    public <T extends Doctor> int addD(T person) {
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

                if (resultSet.getString("phoneNumber" ).equals(person.getPhoneNumber())) {
                    System.out.println("Record already exists" );
                    return 0;
                }
            }

            preparedStmt.setString(1, person.getName());
            preparedStmt.setString(2, person.getAddress());
            preparedStmt.setString(3, person.getPhoneNumber());
            preparedStmt.setString(4, person.getSpeciality());
            preparedStmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 1;
    }


    public <T extends Patient> int addP(T person) {
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

                if (resultSet.getString("phoneNumber" ).equals(person.getPhoneNumber()) && resultSet.getString("name" ).equals(person.getName())) {
                    System.out.println("Record already exists" );
                    return 0;
                }
            }

            preparedStmt.setString(1, person.getName());
            preparedStmt.setString(2, person.getAddress());
            preparedStmt.setString(3, person.getPhoneNumber());
            preparedStmt.setString(4, person.getGender());
            preparedStmt.setString(5, person.getSymptoms());
            preparedStmt.setString(6, person.getPaymentMethod());
            preparedStmt.setString(7, person.getDiagnosis());
            preparedStmt.setBoolean(8, person.isEmergency());
            preparedStmt.setInt(9, person.getRoomNumber());
            preparedStmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 1;
    }

    public <T extends Person> int delete(T person) {
        dataSource.setURL(CONN_STRING);
        String query = "SELECT * FROM %s".formatted(person.getType());
        String qry = "DELETE FROM %s WHERE name= '%s'".formatted(person.getType(), person.getName());
        try (var connection = dataSource.getConnection(
                username, password);
             Statement statement = connection.createStatement();
             PreparedStatement preparedStatement = connection.prepareStatement(qry);
        ) {

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                if ((resultSet.getString("name").equals(person.getName()))) {
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


}
