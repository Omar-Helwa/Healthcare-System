package com.company;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.swing.*;
import java.sql.*;
import java.util.Arrays;

public class Main {
    private final static String CONN_STRING = "jdbc:mysql://localhost:3306/music";

    public static void main(String[] args) {

//        String username = "devuser";
//        String password = "Omar2005Helwa@Rola";
        String username = JOptionPane.showInputDialog(
                null, "Enter DB Username");

        JPasswordField pf = new JPasswordField();
        int okCxl = JOptionPane.showConfirmDialog(null, pf,
                "Enter DB Password", JOptionPane.OK_CANCEL_OPTION);

        final char[] password =
                (okCxl == JOptionPane.OK_OPTION) ? pf.getPassword() : null;


        String query = "SELECT * FROM music.artists";
        var dataSource = new MysqlDataSource();
        dataSource.setURL(CONN_STRING);

        try (var connection = dataSource.getConnection(
                username, String.valueOf(password));
                Statement statement = connection.createStatement();
        ) {

            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                System.out.printf("%d %s %n", resultSet.getInt(1),
                        resultSet.getString("artist_name")
                );
            }
//            Arrays.fill(password, ' ');
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
