/*
 * Author : Abdelmalek BOUGUERRA
 * Author Email : abdelmalekbouguerra2000@gmail.com
 * Created : Aug 2021
 * description : ref table connection methods.
 *
 * (c) Copyright by Abdelmalek BOUGUERRA.
 */
package DAO;

import Classes.DATE;
import static DAO.DB.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class REFDAO {
    public Map<String,String> getREF(String str,String willaya) {
        Map<String, String> infoList = new HashMap<>();
        try {
            String query = "SELECT * FROM reference WHERE str=? AND willaya=?";
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connection established");
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setString(1,str);
            pStatement.setString(2, willaya);
            ResultSet resultSet = pStatement.executeQuery();
            while (resultSet.next()) {
                infoList.put("code", resultSet.getString("code"));
                infoList.put("lib", resultSet.getString("lib"));
                infoList.put("commune", resultSet.getString("commune"));
                infoList.put("willaya", resultSet.getString("willaya"));
                infoList.put("address", resultSet.getString("address"));
                infoList.put("directeur", resultSet.getString("directeur"));
            }
            connection.close();
            pStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return infoList;
    }

    public int setREFdoc(String code,String type) {
        int[] infoList = new int[2];
        try {
            String query = "SELECT "+type+", YEAR (dateExp) as year FROM reference WHERE code=?";
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connection established");
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setString(1,code);
            ResultSet resultSet = pStatement.executeQuery();
            while (resultSet.next()) {
                infoList[0] = resultSet.getInt(type);
                infoList[1] = resultSet.getInt("year");
            }
            if (infoList[1] == Integer.parseInt(DATE.GetYear())){
                infoList[0] = infoList[0] + 1;
            } else{
                infoList[0] = 1;
            }

            String update = "UPDATE reference SET "+type+" =?,dateExp= CAST(CURRENT_TIMESTAMP AS Date) WHERE code =?";
            pStatement = connection.prepareStatement(update);
            pStatement.setInt(1,infoList[0]);
            pStatement.setString(2,code);
            pStatement.executeUpdate();

            connection.close();
            pStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return infoList[0];
    }


}
