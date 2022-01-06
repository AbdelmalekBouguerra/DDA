/*
 * Author : Abdelmalek BOUGUERRA
 * Author Email : abdelmalekbouguerra2000@gmail.com
 * Created : Aug 2021
 * description : demande_ats table connection methods.
 *
 * (c) Copyright by Abdelmalek BOUGUERRA.
 */
package DAO;

import java.sql.*;
import java.util.LinkedHashMap;

import static DAO.DB.*;


public class DEMDAO {


    public LinkedHashMap<String ,String> getAllDEM() {
        LinkedHashMap<String ,String> infoList = new LinkedHashMap<>();
        try {
            String query = "SELECT * FROM demande_ats";

            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connection established");
            PreparedStatement pStatement = connection.prepareStatement(query);
            ResultSet resultSet = pStatement.executeQuery();
            int i=0;
            while (resultSet.next()) {
                boolean termine = resultSet.getBoolean("termine");
                if (!termine) {
                    infoList.put("id" + i, resultSet.getString("id"));
                    infoList.put("MAT" + i, resultSet.getString("matricule"));
                    infoList.put("DATE" + i, resultSet.getString("date"));
                    infoList.put("EMAIL" + i, resultSet.getString("email"));
                    System.out.println("demande : " + resultSet.getString("id"));
                    i++;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return infoList;
    }

    public String getMAT(String id) {
        String MAT = "";
        try {
            String query = "SELECT matricule FROM demande_ats WHERE id=?";

            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connection established");
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setString(1,id);
            ResultSet resultSet = pStatement.executeQuery();

            while (resultSet.next()) {
                MAT = resultSet.getString("matricule");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return MAT;
    }

    public String getEmail(String id) {
        String email = "";
        try {
            String query = "SELECT email FROM demande_ats WHERE id=?";

            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connection established");
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setString(1,id);
            ResultSet resultSet = pStatement.executeQuery();

            while (resultSet.next()) {
                email = resultSet.getString("email");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return email;
    }

    public void setDEM(String MAT,String email){
        try {
            // sql query
            String Add = "INSERT INTO demande_ats(matricule,date,email) VALUES(?,current_date(),?)";

            // Now, lets us start reading the rows
            Connection connection = DriverManager.getConnection(url, username, password);

                PreparedStatement pStatement = connection.prepareStatement(Add);
                pStatement.setString(1, MAT);
                pStatement.setString(2,email);
                pStatement.executeUpdate();
                pStatement.close();
                System.out.println("Demande's " + MAT +" added");

            pStatement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void Done(String id){
        try {
            // sql query
            String Add = "UPDATE demande_ats SET termine = true where id = "+id;

            // Now, lets us start reading the rows
            Connection connection = DriverManager.getConnection(url, username, password);

            PreparedStatement pStatement = connection.prepareStatement(Add);
            pStatement.executeUpdate();
            pStatement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
