/*
 * Author : Abdelmalek BOUGUERRA
 * Author Email : abdelmalekbouguerra2000@gmail.com
 * Created : Aug 2021
 * description : user table connection methods.
 *
 * (c) Copyright by Abdelmalek BOUGUERRA.
 */
package DAO;

import java.sql.*;
import static DAO.DB.*;
public class LoginDAO {

    // sql query
    String queryUname = "SELECT * FROM users WHERE username=?";
    String query = "SELECT * FROM users WHERE username=?";
    String isAdmin = "SELECT role FROM users WHERE username = ?";

    public boolean check(String uname) {
        try {
            System.out.println("uname :"+uname);
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println(uname);
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connection established");
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setString(1, uname);
            ResultSet resultSet = pStatement.executeQuery();
            // if a record found return true
            System.out.println("Database Connection Terminated");

            if (resultSet.next()) return true;
            pStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public String getMat(String uname) {
        String  mat = "";
        try {
            String query = "SELECT matricule FROM users WHERE username=?";

            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connection established");
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setString(1,uname);
            ResultSet resultSet = pStatement.executeQuery();

            while (resultSet.next()) {
                mat = resultSet.getString("matricule");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mat;
    }

//    public boolean checkUname(String uname) {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection connection = DriverManager.getConnection(url, username, password);
//            System.out.println("Database connection established");
//
//            PreparedStatement pStatement = connection.prepareStatement(queryUname);
//            pStatement.setString(1, uname);
//
//            ResultSet resultSet = pStatement.executeQuery();
//            // if a record found return true
//            System.out.println("Database Connection Terminated");
//
//            if (resultSet.next()) return true;
//            pStatement.close();
//            connection.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return false;
//    }

    public String isAdmin(String uname) {
        String Admin = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connection established");

            PreparedStatement pStatement = connection.prepareStatement(isAdmin);
            pStatement.setString(1, uname);

            ResultSet resultSet = pStatement.executeQuery();
            // if a record found return true

            resultSet.next();

            switch (resultSet.getString("role")) {
                case "SuperAdmin":
                    Admin = "SuperAdmin";
                    System.out.println("user set to \"SuperAdmin\"");
                    break;
                case "AdminPaie":
                    Admin = "AdminPaie";
                    System.out.println("user set to \"AdminPaie\"");
                    break;
                case "AdminPers":
                    Admin = "AdminPers";
                    System.out.println("user set to \"Admin\"");
                    break;

                case "user":
                    Admin = "user";
                    System.out.println("user set to \"user\"");
                    break;
            }
            pStatement.close();
            connection.close();
            System.out.println("Database Connection Terminated");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Admin;
    }

//    public void changePASS(String uname, String newPASS) {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection connection = DriverManager.getConnection(url, username, password);
//            System.out.println("Database connection established");
//
//            PreparedStatement pStatement = connection.prepareStatement(changePSS);
//            pStatement.setString(1, newPASS);
//            pStatement.setString(2, uname);
//
//           pStatement.executeUpdate();
//            // if a record found return true
//
//            pStatement.close();
//            connection.close();
//            System.out.println("Database Connection Terminated");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
