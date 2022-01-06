/*
 * Author : Abdelmalek BOUGUERRA
 * Author Email : abdelmalekbouguerra2000@gmail.com
 * Created : Aug 2021
 * description : structure table connection methods.
 *
 * (c) Copyright by Abdelmalek BOUGUERRA.
 */
package DAO;

import com.linuxense.javadbf.DBFException;
import com.linuxense.javadbf.DBFReader;
import com.linuxense.javadbf.DBFRow;
import com.linuxense.javadbf.DBFUtils;
import static DAO.DB.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.LinkedHashMap;




public class STRDAO {
    public LinkedHashMap<String ,String> getAllSTR() {
        LinkedHashMap<String ,String> infoList = new LinkedHashMap<>();
        try {
            String query = "SELECT * FROM structure ORDER BY codestr";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connection established");
            PreparedStatement pStatement = connection.prepareStatement(query);
            ResultSet resultSet = pStatement.executeQuery();
            int i=0;
            while (resultSet.next()) {
                infoList.put("codestr"+i,resultSet.getString("codestr"));
                System.out.println("codestr :"+resultSet.getString("codestr"));
                infoList.put("structure"+i,resultSet.getString("structure"));
                System.out.println("structure : " +resultSet.getString("structure"));
                i++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return infoList;
    }


    public String getSTR(String codestr) {
        String str = "";
        try {
            String query = "SELECT structure FROM structure WHERE codestr=?";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connection established");
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setString(1,codestr);
            ResultSet resultSet = pStatement.executeQuery();

            while (resultSet.next()) {
                str = resultSet.getString("structure");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }
    public void setSTR(String file){
        DBFReader reader = null;
        try {
            // sql query
            String Add = "INSERT INTO structure(codestr,structure) VALUES(?,?) ON DUPLICATE KEY UPDATE structure = ?";

            // create a DBFReader object
            reader = new DBFReader(new FileInputStream(file));

            // Now, lets us start reading the rows
            Connection connection = DriverManager.getConnection(url, username, password);
            Class.forName("com.mysql.cj.jdbc.Driver");
            DBFRow row;
            while ((row = reader.nextRow()) != null) {
                PreparedStatement pStatement = connection.prepareStatement(Add);
                pStatement.setString(1, row.getString("CODE_STUCT"));
                pStatement.setString(2, row.getString("LIB_STRUCU"));
                pStatement.setString(3, row.getString("LIB_STRUCU"));
                pStatement.executeUpdate();
                pStatement.close();
                System.out.println("CODE_STUCTURE" + row.getString("CODE_STUCT")+" added");
            }
            connection.close();
            DBFUtils.close(reader);
        } catch (SQLException | DBFException | ClassNotFoundException | FileNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }
}
