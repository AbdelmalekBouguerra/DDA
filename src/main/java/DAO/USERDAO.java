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
            String query = "SELECT * FROM users WHERE role = 'Admin_DGP'" +
                    "or role = 'Admin_EAZ' or role = 'Admin_EBM' or role = 'Admin_EHM' or role = 'Admin_ESK' ";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connection established");
            PreparedStatement pStatement = connection.prepareStatement(query);
            ResultSet resultSet = pStatement.executeQuery();
            int i = 0;
            while (resultSet.next()) {
                infoList.put("username" + i, resultSet.getString("username"));
                infoList.put("matricule" + i, resultSet.getString("matricule"));
                infoList.put("role" + i, resultSet.getString("role"));
                i++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(infoList);
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

    public static boolean isUsernameExist(String user) {
        try {
            String query = "SELECT * FROM users where username = ?;";
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connection established");
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setString(1,user);
            ResultSet resultSet = pStatement.executeQuery();
            while (resultSet.next()) {
                return  true;
            }
            connection.close();
            pStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public void setAdmin(String son, String role) throws SQLException, ClassNotFoundException {

        String add = "UPDATE users set role = ? where  username= ?";

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, username, password);


        PreparedStatement pStatement = connection.prepareStatement(add);
        pStatement.setString(1, role);
        pStatement.setString(2, son);

        pStatement.executeUpdate();
        System.out.println("done updating admin");
        pStatement.close();
        connection.close();
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
