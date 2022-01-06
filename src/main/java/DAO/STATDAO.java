/*
 * Author : Abdelmalek BOUGUERRA
 * Author Email : abdelmalekbouguerra2000@gmail.com
 * Created : Aug 2021
 * description : stats.
 *
 * (c) Copyright by Abdelmalek BOUGUERRA.
 */
package DAO;


import static DAO.DB.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class STATDAO {

    // Number of download
    String Download ="SELECT COUNT(*) as count " +
            "FROM historique " +
            "WHERE typeaction = ? ;";

    public int getDownland(String typeAction){
        StringBuilder data = new StringBuilder();
        int count = 0;
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement pStatement = connection.prepareStatement(Download);
            pStatement.setString(1,typeAction);
            ResultSet resultSet = pStatement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt("count");
            }
            pStatement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }


    public int getCountUsers(){
        String get = "SELECT COUNT(*) as count FROM users";
        int count = 0;
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement pStatement = connection.prepareStatement(get);
            ResultSet resultSet = pStatement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt("count");
            }
            pStatement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    // Number of downloading  by month




    String DoMonth ="SELECT COUNT(*) as count,MONTH(Date) as month " +
            "FROM historique " +
            "WHERE YEAR(Date) = ? " +
            "AND typeaction = ? "+
            "GROUP BY  MONTH(Date) "+
            "ORDER BY MONTH(Date) ";

    public StringBuilder getDownlandByMonth(String year, String typeAction){
        StringBuilder data = new StringBuilder();
        int month;
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement pStatement = connection.prepareStatement(DoMonth);
            pStatement.setString(1,year);
            pStatement.setString(2,typeAction);
            ResultSet resultSet = pStatement.executeQuery();
            while (resultSet.next()) {
                month = resultSet.getInt("month")-1;
                data.append("{ x: new Date("+year+", "+month+",1), y: "
                        +resultSet.getInt("count")+" },");
            }
            pStatement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    // Number of action by day
    String ActionByDay ="SELECT COUNT(*) AS count "+
                        "FROM historique " +
                        "WHERE day(Date) = ? " +
                        "AND MONTH(Date) = ? " +
                        "AND YEAR(Date) = ? " +
                        "AND typeaction = ? ";

    public int getCountAction(String day, String month, String year,String typeAction){
        int data = 0;
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement pStatement = connection.prepareStatement(ActionByDay);
            pStatement.setString(1,day);
            pStatement.setString(2,month);
            pStatement.setString(3,year);
            pStatement.setString(4,typeAction);
            ResultSet resultSet = pStatement.executeQuery();
            while (resultSet.next()) {
                data = resultSet.getInt("count");
            }
            pStatement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    //
    String ActionBySTR = "SELECT S.Structure,STR,COUNT(h.TypeAction) AS NbrAttestation " +
            "FROM historique AS H INNER JOIN ? p on H.matricule = p.matricule INNER JOIN structure AS S ON S.codestr=p.STR " +
            "where MONTH(Date) = ? " +
            "AND h.typeaction = ?" +
            "group by h.typeaction";
    public int getAllActionBySTR(String day, String month, String year,String typeAction){
        int data = 0;
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement pStatement = connection.prepareStatement(ActionBySTR);
            pStatement.setString(1,day);
            pStatement.setString(2,month);
            pStatement.setString(3,year);
            pStatement.setString(4,typeAction);
            ResultSet resultSet = pStatement.executeQuery();
            while (resultSet.next()) {
                data = resultSet.getInt("count");
            }
            pStatement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }




}
