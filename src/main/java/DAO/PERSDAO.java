/*
 * Author : Abdelmalek BOUGUERRA
 * Author Email : abdelmalekbouguerra2000@gmail.com
 * Created : Aug 2021
 * description : pers table connection methods.
 *
 * (c) Copyright by Abdelmalek BOUGUERRA.
 */
package DAO;

import com.linuxense.javadbf.DBFReader;
import com.linuxense.javadbf.DBFRow;
import com.linuxense.javadbf.DBFUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import static DAO.DB.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class PERSDAO {
    public LinkedHashMap<String ,String> getAllPERS(String year,String month) {
        String date = month+"/"+year;
        LinkedHashMap<String ,String> infoList = new LinkedHashMap<>();
        try {
       String query = "SELECT * FROM pers_" + year+" WHERE dateexpl = '"+date+"'";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connection established");
            PreparedStatement pStatement = connection.prepareStatement(query);
            ResultSet resultSet = pStatement.executeQuery();
            int i=1;
            while (resultSet.next()) {
                infoList.put("matricule" + i, resultSet.getString("matricule"));
                infoList.put("nom" + i, resultSet.getString("nom"));
                infoList.put("str" + i, resultSet.getString("str"));
                infoList.put("codelieunais" + i, resultSet.getString("codelieunais"));
                infoList.put("datenais" + i, resultSet.getString("datenais"));
                infoList.put("sexe" + i, resultSet.getString("sexe"));
                infoList.put("daterec" + i, resultSet.getString("daterec"));
                infoList.put("fonction" + i, resultSet.getString("fonction"));
                infoList.put("gsang" + i, resultSet.getString("gsang"));
                infoList.put("sf" + i, resultSet.getString("sf"));
                infoList.put("scjt" + i, resultSet.getString("scjt"));
                infoList.put("nbrenfs10" + i, Integer.toString(resultSet.getInt("nbrenfs10")));
                infoList.put("nbrenfm10" + i, Integer.toString(resultSet.getInt("nbrenfm10")));
                infoList.put("rib" + i, resultSet.getString("rib"));
                infoList.put("nssagt" + i, resultSet.getString("nssagt"));
                infoList.put("nssemp" + i, resultSet.getString("nssemp"));
                infoList.put("cpaiem" + i, resultSet.getString("cpaiem"));
                infoList.put("salbase" + i, String.valueOf(resultSet.getBigDecimal("salbase")));
                infoList.put("iag" + i, String.valueOf(resultSet.getBigDecimal("iag")));
                infoList.put("adresse" + i, resultSet.getString("adresse"));
                infoList.put("loctrav"+i, resultSet.getString("loctrav"));
                infoList.put("groupe"+i, resultSet.getString("groupe"));
                infoList.put("echelle"+i, resultSet.getString("echelle"));
                infoList.put("css"+i, resultSet.getString("css"));
                infoList.put("suitorg"+i, resultSet.getString("suitorg"));
                System.out.println(i + ". matricule :" + resultSet.getString("matricule") + " added");
                i++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return infoList;
    }

    public Map<String,String> getPERS(String matricule,String year,String month) {

        String date = month+"/"+year;
        Map<String, String> infoList = new HashMap<>();
        try {
            String query = "SELECT * FROM pers_"+year+" where matricule = ? and dateexpl = '"+date+"'";
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connection established");
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setString(1,matricule);
            ResultSet resultSet = pStatement.executeQuery();
            while (resultSet.next()) {
                infoList.put("matricule", resultSet.getString("matricule"));
                infoList.put("nom", resultSet.getString("nom"));
                infoList.put("str", resultSet.getString("str"));
                infoList.put("codelieunais", Integer.toString(resultSet.getInt("codelieunais")));
                infoList.put("datenais", resultSet.getString("datenais"));
                infoList.put("sexe", resultSet.getString("sexe"));
                infoList.put("daterec", resultSet.getString("daterec"));
                infoList.put("fonction", resultSet.getString("fonction"));
                infoList.put("gsang", resultSet.getString("gsang"));
                infoList.put("sf", resultSet.getString("sf"));
                infoList.put("scjt", resultSet.getString("scjt"));
                infoList.put("nbrenfs10", "0"+Integer.toString(resultSet.getInt("nbrenfs10")));
                infoList.put("nbrenfm10", "0"+Integer.toString(resultSet.getInt("nbrenfm10")));
                infoList.put("rib", resultSet.getString("rib"));
                infoList.put("nssagt", resultSet.getString("nssagt"));
                if (resultSet.getString("sexe").equals("M")) infoList.put("civilite", "Monsieur");
                else if (resultSet.getString("sf").equals("M")) infoList.put("civilite", "Madame");
                else infoList.put("civilite", "Mademoiselle");
                infoList.put("nssemp", resultSet.getString("nssemp"));
                infoList.put("cpaiem", resultSet.getString("cpaiem"));
                infoList.put("salbase", String.valueOf(resultSet.getBigDecimal("salbase")));
                infoList.put("iag", String.valueOf(resultSet.getBigDecimal("iag")));
                infoList.put("adresse", resultSet.getString("adresse"));
                infoList.put("loctrav", resultSet.getString("loctrav"));
                infoList.put("groupe", resultSet.getString("groupe"));
                infoList.put("echelle", resultSet.getString("echelle"));
                infoList.put("css", resultSet.getString("css"));
                infoList.put("suitorg", resultSet.getString("suitorg"));
            }
            connection.close();
            pStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return infoList;
    }

    public boolean check(String table) throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
        DatabaseMetaData dbm = connection.getMetaData();
        // check if "employee" table is there
        ResultSet tables = dbm.getTables(null, null, table, null);
        // Table exists return true
        // Table does not exist return false
        return tables.next();
    }

    public boolean checkDate(String year, String month) {
        String date = month+"/"+year;
        try {
            String check = "SELECT * FROM pers_" + year + " WHERE dateexpl = '"+date+"'";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connection established");
            PreparedStatement pStatement = connection.prepareStatement(check);
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

    public void setPERS(String file,String year,String month){

        String date = month+"/"+year;
        try {

            String creat = "CREATE TABLE pers_"+year+"(" +
                    "id INT NOT NULL AUTO_INCREMENT," +
                    " matricule VARCHAR(30)  NOT NULL," +
                    " dateexpl VARCHAR(8)," +
                    " nom VARCHAR(600) NOT NULL," +
                    " str VARCHAR(5) NOT NULL," +
                    " loctrav int,"+
                    " datenais varchar(8)," +
                    " codelieunais varchar(4)," +
                    " sexe char," +
                    " fonction VARCHAR(600) NOT NULL," +
                    " daterec varchar(8)," +
                    " gsang varchar(3)," +
                    " sf char," +
                    " adresse VARCHAR(600) NOT NULL," +
                    " nbrenfm10 int," +
                    " nbrenfs10 int," +
                    " scjt char," +
                    " rib varchar(25)," +
                    " nssagt varchar(12)," +
                    " nssemp varchar(12)," +
                    " cpaiem varchar(12)," +
                    " iag DECIMAL(19 , 2 )," +
                    " salbase DECIMAL(19 , 2 )," +
                    "groupe varchar(4),"+
                    "echelle varchar(4),"+
                    "css char,"+
                    "suitorg varchar(3), "+
                    " PRIMARY KEY (id)," +
                    " FOREIGN KEY (codelieunais) REFERENCES localite(codelieunais)," +
                    " FOREIGN KEY (str) REFERENCES structure(codestr)" +
                    ");";

            String add = "INSERT INTO pers_"+year+"(matricule,nom,str,loctrav,datenais,codelieunais,sexe,fonction" +
                    ",daterec,gsang,sf,adresse,nbrenfm10,nbrenfs10,scjt,rib,nssagt,nssemp,cpaiem,iag,salbase,dateexpl," +
                    "groupe,echelle,css,suitorg)"+
                    " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

            String delete = "DELETE FROM pers_" + year + " WHERE dateexpl = '"+date+"'";
            // create a DBFReader object
            DBFReader reader = new DBFReader(new FileInputStream(file));
            // Now, lets us start reading the rows
            Connection connection = DriverManager.getConnection(url, username, password);
            Class.forName("com.mysql.cj.jdbc.Driver");
            PreparedStatement pStatement;
            if (!check("pers_" + year)) {
                System.out.println("table pers_"+year+" do not exist");
                System.out.println("creating pers_"+year);
                pStatement = connection.prepareStatement(creat);
                pStatement.executeUpdate();
                pStatement.close();
            }
            if (checkDate(year, month)){
                System.out.println(date + "already exist in table pers_"+year);
                System.out.println("deletion of the old insertion");
                pStatement = connection.prepareStatement(delete);
                pStatement.executeUpdate();
                pStatement.close();
            }
            
            DBFRow row;
            while ((row = reader.nextRow()) != null) {
                pStatement = connection.prepareStatement(add);
                pStatement.setString(1, row.getString("MAT"));
                pStatement.setString(2, row.getString("NOM"));
                pStatement.setString(3, row.getString("DIV"));
                pStatement.setString(4, row.getString("LOCTRAV"));
                pStatement.setString(5, row.getString("DATNAIS"));
                pStatement.setString(6, ((row.getString
                        ("LIEUNAIS").isEmpty()) ? "ETRG" : row.getString("LIEUNAIS")));

                pStatement.setString(7, row.getString("SEXE"));
                pStatement.setString(8, row.getString("LIBFONC"));
                pStatement.setString(9, row.getString("DATREC"));
                pStatement.setString(10, row.getString("GSANG"));
                pStatement.setString(11, row.getString("SF"));
                pStatement.setString(12, row.getString("adresse"));
                pStatement.setString(13, row.getString("ENFI10"));
                pStatement.setString(14, row.getString("ENFS10"));
                pStatement.setString(15, row.getString("SCJT"));
                pStatement.setString(16, row.getString("NO_RIB"));
                pStatement.setString(17, row.getString("NSSAGT"));
                pStatement.setString(18, row.getString("NSSEMP"));
                pStatement.setString(19, row.getString("CPAIEM"));
                
                BigDecimal SALBASE = BigDecimal.valueOf(Double.parseDouble(row.getString("SALBASE")) / 100);
                BigDecimal IAG = BigDecimal.valueOf(Double.parseDouble(row.getString("MONT1")) / 100);
                pStatement.setBigDecimal(20, IAG);
                pStatement.setBigDecimal(21, SALBASE);
                pStatement.setString(22,date);
                pStatement.setString(23,row.getString("GRPE"));
                pStatement.setString(24,row.getString("ECHL"));
                pStatement.setString(25,row.getString("CSS"));
                pStatement.setString(26,row.getString("SUITORG"));
                pStatement.executeUpdate();
                pStatement.close();
                System.out.println("user " + row.getString("MAT") + " added");
            }
            connection.close();
            DBFUtils.close(reader);
        } catch (SQLException | FileNotFoundException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

    public void setPERSXLS(String file,String year,String month){

        String date = month+"/"+year;
        try {
            String creat = "CREATE TABLE pers_"+year+"(" +
                    "id INT NOT NULL AUTO_INCREMENT," +
                    " matricule VARCHAR(30)  NOT NULL," +
                    " dateexpl VARCHAR(8)," +
                    " nom VARCHAR(600) NOT NULL," +
                    " str VARCHAR(5) NOT NULL," +
                    " loctrav int,"+
                    " datenais varchar(8)," +
                    " codelieunais varchar(4)," +
                    " sexe char," +
                    " fonction VARCHAR(600) NOT NULL," +
                    " daterec varchar(8)," +
                    " gsang varchar(3)," +
                    " sf char," +
                    " adresse VARCHAR(600) NOT NULL," +
                    " nbrenfm10 int," +
                    " nbrenfs10 int," +
                    " scjt char," +
                    " rib varchar(25)," +
                    " nssagt varchar(12)," +
                    " nssemp varchar(12)," +
                    " cpaiem varchar(12)," +
                    " iag DECIMAL(19 , 2 )," +
                    " salbase DECIMAL(19 , 2 )," +
                    "groupe varchar(4),"+
                    "echelle varchar(4),"+
                    "css char,"+
                    "suitorg varchar(3), "+
                    " PRIMARY KEY (id)," +
                    " FOREIGN KEY (codelieunais) REFERENCES localite(codelieunais)," +
                    " FOREIGN KEY (str) REFERENCES structure(codestr)" +
                    ");";

            String add = "INSERT INTO pers_"+year+"(matricule,nom,str,loctrav,datenais,codelieunais,sexe,fonction" +
                    ",daterec,gsang,sf,adresse,nbrenfm10,nbrenfs10,scjt,rib,nssagt,nssemp,cpaiem,iag,salbase,dateexpl," +
                    "groupe,echelle,css,suitorg)"+
                    " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

            String delete = "DELETE FROM pers_" + year + " WHERE dateexpl = '"+date+"'";
            // Now, lets us start reading the rows
            Connection connection = DriverManager.getConnection(url, username, password);
            Class.forName("com.mysql.cj.jdbc.Driver");
            PreparedStatement pStatement;
            if (!check("pers_" + year)) {
                System.out.println("table pers_"+year+" do not exist");
                System.out.println("creating pers_"+year);
                pStatement = connection.prepareStatement(creat);
                pStatement.executeUpdate();
                pStatement.close();
            }
            if (checkDate(year, month)){
                System.out.println(date + "already exist in table pers_"+year);
                System.out.println("deletion of the old insertion");
                pStatement = connection.prepareStatement(delete);
                pStatement.executeUpdate();
                pStatement.close();
            }
            FileInputStream fis = new FileInputStream(file);
            // we create an XSSF Workbook object for our XLSX Excel File
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            // we get first sheet
            XSSFSheet sheet = workbook.getSheetAt(0);

            // we iterate on rows
            Iterator<Row> rowIt = sheet.iterator();
            LinkedHashMap<String, String> infoList = new LinkedHashMap<>();
            int i = 1;
            while (rowIt.hasNext()) {
                pStatement = connection.prepareStatement(add);
                Row row = rowIt.next();
                // iterate on cells for the current row
                Iterator<Cell> cellIterator = row.cellIterator();
                int cpt = 0;
                while (cellIterator.hasNext()) {
                    // todo : add an arg her so we can skip the header
                    Cell cell = cellIterator.next();
                    // if we find MAT (it is the first header in pers file we break
                    if (cpt == 87) break; // bcz after 80 col we dont need to process that
                    if ("MAT".equals(cell.toString())) break;
                    // we need to get number of col
                    // use this website for ez ref
                    // https://www.vishalon.net/blog/excel-column-letter-to-number-quick-reference
                    //
                    switch (cpt) {
                        case 0:
                            pStatement.setString(1, cell.toString());
                            pStatement.setString(22,date);
                            break;
                        case 3 :
                            infoList.put("suitorg"+i, cell.toString());
                            break;
                        case 4:
                            pStatement.setString(4, cell.toString());
                            break;
                        case 6:
                            pStatement.setString(2,cell.toString());
                            break;
                        case 2:
                            pStatement.setString(3, cell.toString());
                            break;
                        case 9:
                            pStatement.setString(6, ((cell.toString().isEmpty()) ? "ETRG" : cell.toString()));
                            break;
                        case 8:
                            pStatement.setString(5, cell.toString());
                            break;
                        case 10:
                            pStatement.setString(7, cell.toString());
                            break;
                        case 12:
                            pStatement.setString(9,cell.toString());
                            break;
                        case 16:
                            pStatement.setString(11, cell.toString());
                            break;
                        case 17:
                            pStatement.setString(15, cell.toString());
                            break;
                        case 19:
                            pStatement.setString(14, cell.toString());
                            break;
                        case 20:
                            pStatement.setString(13,cell.toString());
                            break;
                        case 21:
                            pStatement.setString(10, cell.toString());
                            break;
                        case 33:
                            pStatement.setString(23,cell.toString());
                            break;
                        case 34:
                            pStatement.setString(24, cell.toString());
                            break;
                        case 41:
                            BigDecimal SALBASE = BigDecimal.valueOf(Double.parseDouble(cell.toString()) / 100);
                            pStatement.setBigDecimal(21, SALBASE);
                            break;
                        case 44:
                            pStatement.setString(8,cell.toString());
                            break;
                        case 49:
                            pStatement.setString(19, cell.toString());
                            break;
                        case 53:
                            pStatement.setString(25,cell.toString());
                            break;
                        case 57:
                            pStatement.setString(17, cell.toString());
                            break;
                        case 58:
                            pStatement.setString(18, cell.toString());
                            break;
                        case 73:
                            pStatement.setString(12, cell.toString());
                            break;
                        case 82 :
                            pStatement.setString(16, cell.toString());
                            break;
                        case 86:
                            BigDecimal IAG = BigDecimal.valueOf(Double.parseDouble(cell.toString()) / 100);
                            pStatement.setBigDecimal(20, IAG);
                            break;
                    }
                    cpt++;
                }
                pStatement.executeUpdate();
                pStatement.close();
                i++;
            }
            workbook.close();
            fis.close();

            System.out.println(infoList);
        } catch (SQLException | FileNotFoundException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
