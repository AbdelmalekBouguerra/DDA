/*
 * Author : Abdelmalek BOUGUERRA
 * Author Email : abdelmalekbouguerra2000@gmail.com
 * Created : Aug 2021
 * description : localite table connection methods.
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
import java.util.LinkedHashMap;


public class LOCDAO {
    public LinkedHashMap<String ,String> getAllLOC() {
        LinkedHashMap<String ,String> infoList = new LinkedHashMap<>();
        try {
            String query = "SELECT * FROM localite";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connection established");
            PreparedStatement pStatement = connection.prepareStatement(query);
            ResultSet resultSet = pStatement.executeQuery();
            int i=0;
            while (resultSet.next()) {
                infoList.put("codelieunais"+i,resultSet.getString("codelieunais"));
                System.out.println("codelieunais :"+resultSet.getString("codelieunais"));
                infoList.put("localite"+i,resultSet.getString("localite"));
                System.out.println("localite : " +resultSet.getString("localite"));
                i++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return infoList;
    }
    public String getLOC(String codelieunais) {
        String localite = "";
        try {
            String query = "SELECT localite FROM localite WHERE codelieunais=?";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connection established");
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setString(1,codelieunais);
            ResultSet resultSet = pStatement.executeQuery();

            while (resultSet.next()) {
                localite = resultSet.getString("localite");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return localite;
    }
    public void setLOC(String file){
        try {
            // sql query
            String Add = "INSERT INTO localite(codelieunais,localite) VALUES(?,?) ON DUPLICATE KEY UPDATE localite = ?";

            // create a DBFReader object
            DBFReader reader = new DBFReader(new FileInputStream(file));

            // Now, lets us start reading the rows
            Connection connection = DriverManager.getConnection(url, username, password);
            Class.forName("com.mysql.cj.jdbc.Driver");
            DBFRow row;
            while ((row = reader.nextRow()) != null) {
                PreparedStatement pStatement = connection.prepareStatement(Add);
                pStatement.setString(1, row.getString("CODLOC"));
                pStatement.setString(2, row.getString("LOC"));
                pStatement.setString(3, row.getString("LOC"));
                pStatement.executeUpdate();
                pStatement.close();
                System.out.println("CODLOC" + row.getString("CODLOC")+" added");
            }
            connection.close();
            DBFUtils.close(reader);
        } catch (SQLException | FileNotFoundException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }
}
