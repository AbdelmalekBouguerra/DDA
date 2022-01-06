/*
 * Author : Abdelmalek BOUGUERRA
 * Author Email : abdelmalekbouguerra2000@gmail.com
 * Created : Aug 2021
 * description : historique table connection methods.
 *
 * (c) Copyright by Abdelmalek BOUGUERRA.
 */
package DAO;


import java.sql.*;
import java.util.LinkedHashMap;

import static DAO.DB.*;


public class HISDAO {
    public void saveAction(String Mat, String typeaction) {
        try {
            //sql query
            String Add = "INSERT INTO historique(matricule,date,typeaction) VALUES(?,CURRENT_TIMESTAMP,?)";
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement(Add);
            statement.setString(1, Mat);
            statement.setString(2, typeaction);
            statement.executeUpdate();

            statement.close();
            connection.close();
            System.out.println("Action recorded ");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public LinkedHashMap<String, String> getAllHIS() {
        LinkedHashMap<String, String> infoList = new LinkedHashMap<>();
        try {
            String query = "SELECT * FROM historique";
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement pStatement = connection.prepareStatement(query);
            ResultSet resultSet = pStatement.executeQuery();
            int i = 0;
            while (resultSet.next()) {
                infoList.put("matricule" + i, resultSet.getString("matricule"));
                infoList.put("date" + i, resultSet.getDate("date") + " "
                        + resultSet.getTime("date"));
                infoList.put("typeaction" + i, resultSet.getString("typeaction"));
                i++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return infoList;
    }
}
