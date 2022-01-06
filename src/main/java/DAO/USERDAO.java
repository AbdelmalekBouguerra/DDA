/*
 * Author : Abdelmalek BOUGUERRA
 * Author Email : abdelmalekbouguerra2000@gmail.com
 * Created : Aug 2021
 * description : user table connection methods.
 *
 * (c) Copyright by Abdelmalek BOUGUERRA.
 */
package DAO;

import com.linuxense.javadbf.DBFReader;
import com.linuxense.javadbf.DBFRow;
import com.linuxense.javadbf.DBFUtils;
import static DAO.DB.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.HashMap;
import java.util.LinkedHashMap;


public class USERDAO {
    public LinkedHashMap<String, String> getAllUsers() {
        LinkedHashMap<String, String> infoList = new LinkedHashMap<>();
        try {
            String query = "SELECT * FROM users";
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connection established");
            PreparedStatement pStatement = connection.prepareStatement(query);
            ResultSet resultSet = pStatement.executeQuery();
            int i = 0;
            while (resultSet.next()) {
                infoList.put("matricule" + i, resultSet.getString("matricule"));
                infoList.put("password" + i, resultSet.getString("password"));
                infoList.put("role" + i, resultSet.getString("role"));
                i++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return infoList;
    }

    public HashMap<String, String> getUSER(String matricule) {
        HashMap<String, String> infoList = new HashMap<>();
        try {
            String query = "SELECT * FROM users WHERE matricule=?";
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connection established");
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setString(1, matricule);
            ResultSet resultSet = pStatement.executeQuery();

            while (resultSet.next()) {
                infoList.put("password", resultSet.getString("password"));
                infoList.put("role", resultSet.getString("role"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return infoList;
    }

    public void setAllUSER(String file) {
        try {
            // sql query
            String Add = "INSERT IGNORE INTO users(matricule) VALUES(?)";

            // create a DBFReader object
            DBFReader reader = new DBFReader(new FileInputStream(file));

            // Now, lets us start reading the rows
            Connection connection = DriverManager.getConnection(url, username, password);
            DBFRow row;
            while ((row = reader.nextRow()) != null) {
                PreparedStatement pStatement = connection.prepareStatement(Add);
                pStatement.setString(1, row.getString("MAT"));
                pStatement.executeUpdate();
                pStatement.close();
                System.out.println("User" + row.getString("MAT") + " added");
            }
            connection.close();
            DBFUtils.close(reader);
        } catch (SQLException | FileNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }
}
