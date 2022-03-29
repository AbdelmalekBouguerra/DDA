/*
 * Author : Abdelmalek BOUGUERRA
 * Author Email : abdelmalekbouguerra2000@gmail.com
 * Created : Aug 2021
 * description : rub table connection methods.
 *
 * (c) Copyright by Abdelmalek BOUGUERRA.
 */
package DAO;


import Classes.DATE;
import Classes.RE;
import com.linuxense.javadbf.DBFReader;
import com.linuxense.javadbf.DBFRow;
import com.linuxense.javadbf.DBFUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.LinkedHashMap;

import static DAO.DB.*;

public class RUBDAO {
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /** BaseCleaner
     * this function clear the null rows from the base[][]
     * so it doesn't show an empty row in the pdf file.
     *
     * @param base the base in ATC.
     *
     * @return newBase the clean base from nulls rows.
     */
    public static String[][] baseCleaner(String[][] base){
        int k = 0;
        for (int i = 0; i < base.length; i++) {
            if (base[i] != null) k++;
        }

        String[][] newBase = new String[k][];
        int j = 0;
        for (int i = 0; i < base.length; i++) {
            if (base[i] != null){
                newBase[j] = base[i];
                j++;
            }
        }
        return newBase;
    }

    public static void reverseArray(String[][] inputArray) {
        for (int left = 0, right = inputArray.length - 1;
             left < right; left++, right--) {
            // swap the values at the left and right indices
            String[] temp = inputArray[left];
            inputArray[left] = inputArray[right];
            inputArray[right] = temp;
        }
    }

    public LinkedHashMap<String, String> getAllRUB(String year,String month) {
        LinkedHashMap<String, String> infoList = new LinkedHashMap<>();
        try {
            String date = month+"/"+year;
            System.out.println("date : "+date);
            String query = "SELECT * FROM rub_" + year+" WHERE dateexpl = '"+date+"' LIMIT 100";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connection established");
            PreparedStatement pStatement = connection.prepareStatement(query);
            ResultSet resultSet = pStatement.executeQuery();
            int i = 1;
            while (resultSet.next()) {
                infoList.put("matricule" + i, resultSet.getString("matricule"));
                infoList.put("dateexpl" + i, resultSet.getString("dateexpl"));
                infoList.put("numrub" + i, resultSet.getString("numrub"));
                infoList.put("librub" + i, resultSet.getString("librub"));
                infoList.put("datedeb" + i, resultSet.getString("datedeb"));
                infoList.put("datefin" + i, resultSet.getString("datefin"));
                infoList.put("montantmois" + i, String.valueOf(resultSet.getBigDecimal("montantmois")));
                infoList.put("taux" + i, String.valueOf(resultSet.getInt("taux")));
                infoList.put("base" + i, String.valueOf(resultSet.getFloat("base")));
                System.out.println(i + ". matricule :" + resultSet.getString("matricule") + " added");
                i++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return infoList;
    }

//    public String[][] getRUB(String matricule, String month, String year) {
//
//        // declaring the matrix they will contain all rub info.
//        String[][] data = new String[50][];
//
//        try {
//            // connect to DB.
//
//            String query = "SELECT * FROM rub_" + year + " WHERE matricule = ? AND mois = ? AND annee = ? ;";
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection connection = DriverManager.getConnection(url, username, password);
//            System.out.println("Database connection established");
//            PreparedStatement pStatement = connection.prepareStatement(query);
//            pStatement.setString(1, matricule);
//            pStatement.setString(2,month);
//            pStatement.setString(3,year);
//            ResultSet resultSet = pStatement.executeQuery();
//            // reading from DB.
//            int i = 0;
//            while (resultSet.next()) {
//                String[] dataRow = new String[8];
//                int rub;
//                if (isNumeric(resultSet.getString("numrub")))
//                    rub = Integer.parseInt(resultSet.getString("numrub"));
//                else rub = 0;
//                if ((rub >= 100 && rub <= 500) || (resultSet.getString("numrub").equals("4AT"))) {
//                    dataRow[0] = resultSet.getString("numrub");
//                    dataRow[1] = resultSet.getString("librub");
//                    if (resultSet.getBigDecimal("base").compareTo(BigDecimal.ZERO) != 0) {
//                        dataRow[2] = String.valueOf(resultSet.getBigDecimal("base"));
//                    } else dataRow[2] = "";
//                    if (resultSet.getInt("taux") != 0) {
//                        dataRow[3] = String.valueOf(resultSet.getInt("taux"));
//                    } else dataRow[3] = "";
//
//                    if (rub >= 100 && rub < 200) {
//                        dataRow[4] = String.valueOf(resultSet.getBigDecimal("montantmois"));
//                        dataRow[5] = "";
//                        dataRow[6] = "";
//                        dataRow[7] = "";
//                    } else if (rub >= 200 && rub < 300 || resultSet.getString("numrub").equals("2B3")) {
//                        dataRow[4] = "";
//                        dataRow[5] = String.valueOf(resultSet.getBigDecimal("montantmois"));
//                        dataRow[6] = "";
//                        dataRow[7] = "";
//                    } else if ((rub >= 300 && rub < 400) || (rub == 460)) {
//                        dataRow[4] = "";
//                        dataRow[5] = "";
//                        dataRow[6] = "";
//                        dataRow[7] = String.valueOf(resultSet.getBigDecimal("montantmois"));
//                    } else if (rub >= 400 && rub < 500 || resultSet.getString("numrub").equals("4AT")) {
//                        dataRow[4] = "";
//                        dataRow[5] = "";
//                        dataRow[6] = String.valueOf(resultSet.getBigDecimal("montantmois"));
//                        dataRow[7] = "";
//                    }
//                    data[i] = dataRow;
//                    i++;
//                    if (resultSet.getString("datedeb") != null) {
//                        String[] dataRowR = new String[8];
//
//                        dataRowR[1] = "RAP DU " + DATE.ChangeFormat(resultSet.getString("datedeb"))
//                                + " AU " +DATE.ChangeFormat(resultSet.getString("datefin"));
//                        for (int j = 0; j < 8; j++) {
//                            if (j != 1) dataRowR[j] = "";
//                        }
//                        data[i] = dataRowR;
//                        i++;
//                    }
//                }
//            }
//            connection.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return data;
//    }


    public String[][] getRUB(String matricule, String month, String year) {

        // declaring the matrix they will contain all rub info.
        String[][] data = new String[50][];

        try {
            // connect to DB.

            String query = "SELECT * FROM rub_" + year + " WHERE matricule = ? AND mois = ? AND annee = ? ;";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connection established");
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setString(1, matricule);
            pStatement.setString(2, month);
            pStatement.setString(3, year);
            ResultSet resultSet = pStatement.executeQuery();
            // reading from DB.
            int i = 0;
            while (resultSet.next()) {
                String[] dataRow = new String[8];
                String[] rub = resultSet.getString("numrub").split("(?!^)");
                if (!rub[0].equals("5")) {
                    dataRow[0] = resultSet.getString("numrub");
                    dataRow[1] = resultSet.getString("librub");
                    if (resultSet.getBigDecimal("base").compareTo(BigDecimal.ZERO) != 0) {
                        dataRow[2] = String.valueOf(resultSet.getBigDecimal("base"));
                    } else dataRow[2] = "";
                    if (resultSet.getBigDecimal("taux").compareTo(BigDecimal.ZERO) != 0) {
                        dataRow[3] = String.valueOf(resultSet.getBigDecimal("taux"));
                    } else dataRow[3] = "";

                    if (rub[0].equals("1")) {
                        dataRow[4] = String.valueOf(resultSet.getBigDecimal("montantmois"));
                        dataRow[5] = "";
                        dataRow[6] = "";
                        dataRow[7] = "";
                    } else if (rub[0].equals("2")) {
                        dataRow[4] = "";
                        dataRow[5] = String.valueOf(resultSet.getBigDecimal("montantmois"));
                        dataRow[6] = "";
                        dataRow[7] = "";
                    } else if (rub[0].equals("3") || resultSet.getString("numrub").equals("460")) {
                        dataRow[4] = "";
                        dataRow[5] = "";
                        dataRow[6] = "";
                        dataRow[7] = String.valueOf(resultSet.getBigDecimal("montantmois"));
                    } else if (rub[0].equals("4")) {
                        dataRow[4] = "";
                        dataRow[5] = "";
                        dataRow[6] = String.valueOf(resultSet.getBigDecimal("montantmois"));
                        dataRow[7] = "";
                    }
                    data[i] = dataRow;
                    i++;
                    if (resultSet.getString("datedeb") != null) {
                        String[] dataRowR = new String[8];

                        dataRowR[1] = "RAP DU " + DATE.ChangeFormat(resultSet.getString("datedeb"))
                                + " AU " + DATE.ChangeFormat(resultSet.getString("datefin"));
                        for (int j = 0; j < 8; j++) {
                            if (j != 1) dataRowR[j] = "";
                        }
                        data[i] = dataRowR;
                        i++;
                    }

                }

            }
            connection.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return data;
    }


        public boolean isRetro(String matricule, String month, String year) {
        String  eff = "";
        try {
            String query = "SELECT * FROM rub_" + year + " WHERE matricule = ? AND mois = ? AND annee = ? ;";

            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connection established");
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setString(1, matricule);
            pStatement.setString(2,month);
            pStatement.setString(3,year);
            ResultSet resultSet = pStatement.executeQuery();

            while (resultSet.next()) {
                eff = resultSet.getString("dateexpl");
                String date = month+"/"+year;
                if (!date.equals(eff)){
                    return true;
                } else return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

//    public String[] getGrpEch(String matricule, String month, String year){
//        String date = month+"/"+year;
//        String[] rus = new String[2];
//        try {
//            String query = "SELECT * FROM rub_" + year + " WHERE matricule = ? AND dateexpl = '"+date+"'";
//
//            Connection connection = DriverManager.getConnection(url, username, password);
//            System.out.println("Database connection established");
//            PreparedStatement pStatement = connection.prepareStatement(query);
//            pStatement.setString(1, matricule);
//            ResultSet resultSet = pStatement.executeQuery();
//
//            while (resultSet.next()) {
//                rus[0] = resultSet.getString("echelle");
//                rus[1] = resultSet.getString("groupe");
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return rus;
//    }

    public boolean check(String table) throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
        DatabaseMetaData dbm = connection.getMetaData();
        // check if "employee" table is there
        ResultSet tables = dbm.getTables(null, null, table, null);
        // Table exists return tru
        // Table does not exist return false
        return tables.next();
    }

    public boolean checkDate(String year, String month) {

        try {
            String check = "SELECT * FROM rub_" + year + " WHERE mois = ? AND annee = ? ;";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connection established");
            PreparedStatement pStatement = connection.prepareStatement(check);
            pStatement.setString(1,month);
            pStatement.setString(2,year);
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


    public void setRUB(String file, String year,String month) {
        String date = month+"/"+year;
        String yearRub = "";
        String yearEff = "";
        try {
            // sql query
            String creat = "CREATE TABLE rub_" + year + "(id int NOT NULL AUTO_INCREMENT," +
                    "matricule VARCHAR(30) NOT NULL,dateexpl VARCHAR(8),numRub VARCHAR(3),librub VARCHAR(255)," +
                    "datedeb VARCHAR(8),datefin VARCHAR(8),montantmois DECIMAL(19,2) ,taux DECIMAL(19,2)," +
                    "base DECIMAL(19,2),mois varchar(3),annee varchar(5),PRIMARY KEY (id));";
            String delete = "DELETE FROM rub_" + year + " WHERE mois = ? AND annee = ? ;";

            // create a DBFReader object
            DBFReader reader = new DBFReader(new FileInputStream(file));

            // Now, lets us start reading the rows
            Connection connection = DriverManager.getConnection(url, username, password);
            Class.forName("com.mysql.cj.jdbc.Driver");
            PreparedStatement pStatement;

            if (!check("rub_" + year)) {
                System.out.println("table rub_"+year+" do not exist");
                System.out.println("creating rub_"+year);
                pStatement = connection.prepareStatement(creat);
                pStatement.executeUpdate();
                pStatement.close();
            }
            if (checkDate(year, month)){
                System.out.println(date + "already exist in table rub_"+year);
                System.out.println("deletion of the old insertion");
                pStatement = connection.prepareStatement(delete);
                pStatement.setString(1,month);
                pStatement.setString(2,year);
                pStatement.executeUpdate();
                pStatement.close();
            }
            DBFRow row;
            //if it makes error use CODE_NATUR to determine if it has a "rappel" or not .

            while ((row = reader.nextRow()) != null) {
                yearRub = row.getString("ANN_EFFET");

                if (!year.equals(yearRub)) {
                    // test annee actif si il existe table rub de cette annee
                    if (!check("rub_" + yearRub)) {
                        System.out.println("table rub_" + yearRub + " do not exist");
                        System.out.println("creating rub_" + yearRub);
                        String creatRub = "CREATE TABLE rub_" + yearRub + "(id int NOT NULL AUTO_INCREMENT," +
                                "matricule VARCHAR(30) NOT NULL,dateexpl VARCHAR(8),numRub VARCHAR(3),librub VARCHAR(255)," +
                                "datedeb VARCHAR(8),datefin VARCHAR(8),montantmois DECIMAL(19,2) ,taux DECIMAL(19,2)," +
                                "base DECIMAL(19,2),mois varchar(3),annee varchar(5),PRIMARY KEY (id));";
                        pStatement = connection.prepareStatement(creatRub);
                        pStatement.executeUpdate();
                        pStatement.close();
                    }
                    yearEff = yearRub;
                }else yearEff = year;

                String Add = "INSERT INTO rub_" + yearEff + "(matricule, dateexpl, numrub, librub, datedeb," +
                        "datefin, montantmois, taux, base,mois,annee) VALUES(?,?,?,?,?,?,?,?,?,?,?);";

//                BigDecimal MT_MOIS = new BigDecimal(row.getString("MT_MOIS"));
                BigDecimal MT_MOIS, taux;
                BigDecimal ELEM_STAT = new BigDecimal(row.getString("ELEM_STAT"));
                pStatement = connection.prepareStatement(Add);
                pStatement.setString(1, row.getString("MAT"));
                pStatement.setString(2, row.getString("DATE_EXPL"));
                pStatement.setString(3, row.getString("NO_RUB"));
                pStatement.setString(4, row.getString("LIB_RUB"));
                if (row.getString("CODE_NATUR").equals("2")) {
                    pStatement.setString(5, row.getString("DT_DEB"));
                    pStatement.setString(6, row.getString("DE_FIN"));
                    MT_MOIS = new BigDecimal(row.getString("MT_RAPPEL"));
                } else {
                    pStatement.setString(5, null);
                    pStatement.setString(6, null);
                    MT_MOIS = new BigDecimal(row.getString("MT_MOIS"));
                }
                pStatement.setBigDecimal(7, MT_MOIS);
                taux = new BigDecimal(row.getString("TAUX"));
                pStatement.setBigDecimal(8, taux);
                pStatement.setBigDecimal(9, ELEM_STAT);
                pStatement.setString(10, row.getString("MOIS_EFFET"));
                pStatement.setString(11, row.getString("ANN_EFFET"));
                pStatement.executeUpdate();
                pStatement.close();

                System.out.println("Rub " + row.getString("NO_RUB") + " for user" + row.getString("MAT"));
            }
            connection.close();
            DBFUtils.close(reader);
        } catch (SQLException | FileNotFoundException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

//    public String[][] CalBP(String[][] data) {
//        String[][] total = new String[1][7];
//        double GainsImp = 0;
//        double GainsNonImp = 0;
//        double RetenuesImp = 0;
//        double RetenuesNonImp = 0;
//        for (String[] datum : data) {
//            if (!(datum == null) && (datum[0] != null) && (!datum[0].equals(""))) {
//                if (isNumeric(datum[0])) {
//                    if (Integer.parseInt(datum[0]) >= 100 && Integer.parseInt(datum[0]) < 200) {
//                        GainsImp = GainsImp + Double.parseDouble(datum[4]);
//                    } else if (Integer.parseInt(datum[0]) >= 200 && Integer.parseInt(datum[0]) < 300) {
//                        GainsNonImp = GainsNonImp + Double.parseDouble(datum[5]);
////                        311 331 331 313 314 315 317 319 RetNonImp -
//                    } else if ((Integer.parseInt(datum[0]) >= 300 && Integer.parseInt(datum[0]) < 400)
//                            && ((Integer.parseInt(datum[0]) != 311))
//                            && ((Integer.parseInt(datum[0]) != 331))
//                            && ((Integer.parseInt(datum[0]) != 313))
//                            && ((Integer.parseInt(datum[0]) != 314))
//                            && ((Integer.parseInt(datum[0]) != 315))
//                            && ((Integer.parseInt(datum[0]) != 317))
//                            && ((Integer.parseInt(datum[0]) != 319))
//                            || ((Integer.parseInt(datum[0]) == 460))) {
//                        RetenuesNonImp = RetenuesNonImp + Double.parseDouble(datum[7]);
//                    } else if ((Integer.parseInt(datum[0]) >= 400 && Integer.parseInt(datum[0]) < 500)
//                            && !((Integer.parseInt(datum[0]) == 460))) {
//                        RetenuesImp = RetenuesImp + Double.parseDouble(datum[6]);
//                    }
//                } else if (datum[0].equals("4AT")) {
//                    RetenuesImp = RetenuesImp + Double.parseDouble(datum[6]);
//                }
//            }
//        }
//        DecimalFormat df = new DecimalFormat("#,###.##");
//        total[0][0] = "";
//        total[0][1] = "";
//        total[0][3] = (GainsImp == 0) ? "0,00" : df.format(GainsImp);
//        total[0][4] = (GainsNonImp == 0) ? "0,00" : df.format(GainsNonImp);
//        total[0][5] = (RetenuesImp == 0) ? "0,00" : df.format(RetenuesImp);
//        total[0][6] = (RetenuesNonImp == 0) ? "0,00" : df.format(RetenuesNonImp);
//        double net = (GainsImp + GainsNonImp) - (RetenuesImp + RetenuesNonImp);
//        total[0][2] = df.format(net);
//        return total;
//    }
//
    public String[][] CalBP(String[][] data) {
    String[][] total = new String[1][7];
    double GainsImp = 0;
    double GainsNonImp = 0;
    double RetenuesImp = 0;
    double RetenuesNonImp = 0;
    for (String[] datum : data) {
        if (!(datum == null) && (datum[0] != null) && (!datum[0].equals(""))) {
            String[] rub = datum[0].split("(?!^)");
            if (!datum[0].equals("5")) {
                if (rub[0].equals("1")) {
                    GainsImp = GainsImp + Double.parseDouble(datum[4]);
                } else if (rub[0].equals("2")) {
                    GainsNonImp = GainsNonImp + Double.parseDouble(datum[5]);
//                        311 331 331 313 314 315 317 319 RetNonImp -
                } else if (rub[0].equals("3")
                        && ((!datum[0].equals("311")))
                        && ((!datum[0].equals("331")))
                        && ((!datum[0].equals("313")))
                        && ((!datum[0].equals("314")))
                        && ((!datum[0].equals("315")))
                        && ((!datum[0].equals("317")))
                        && ((!datum[0].equals("319")))
                        || datum[0].equals("460")) {
                    RetenuesNonImp = RetenuesNonImp + Double.parseDouble(datum[7]);
                } else if (rub[0].equals("4")) {
                    RetenuesImp = RetenuesImp + Double.parseDouble(datum[6]);
                }
            }
        }
    }
    DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
    symbols.setGroupingSeparator(' ');
    symbols.setDecimalSeparator('.');
    DecimalFormat df = new DecimalFormat("#,##0.00",symbols);
    total[0][0] = "";
    total[0][1] = "";
    total[0][3] = (GainsImp == 0) ? "0.00" : df.format(GainsImp);
    total[0][4] = (GainsNonImp == 0) ? "0.00" : df.format(GainsNonImp);
    total[0][5] = (RetenuesImp == 0) ? "0.00" : df.format(RetenuesImp);
    total[0][6] = (RetenuesNonImp == 0) ? "0.00" : df.format(RetenuesNonImp);
    double net = (GainsImp + GainsNonImp) - (RetenuesImp + RetenuesNonImp);
    total[0][2] = df.format(net);
    return total;
}

//    public int count(String matricule, String month, String year) {
//        try {
//            String query = "SELECT COUNT(*) AS total FROM rub_" + year + " WHERE matricule=?";
//            String query2 = "SELECT COUNT(*) AS total FROM rub_" + year + " WHERE matricule=?" +
//                    " and datedeb IS NOT NULL";
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection connection = DriverManager.getConnection(url, username, password);
//            System.out.println("Database connection established");
//            PreparedStatement pStatement = connection.prepareStatement(query);
//            pStatement.setString(1, matricule);
//            ResultSet resultSet = pStatement.executeQuery();
//            resultSet.next();
//            int total = resultSet.getInt("total");
//            pStatement = connection.prepareStatement(query2);
//            pStatement.setString(1, matricule);
//            ResultSet resultSet2 = pStatement.executeQuery();
//            resultSet2.next();
//            int total2 = resultSet2.getInt("total");
//            connection.close();
//            return total + total2;
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }

    public String[][] baseSS(String matricule, String year, int numberOfMonths, int startMonth) {

        String[][] base = new String[numberOfMonths][];
        String[] baseRow;
        String[][] data;
        int j, cpt;
        double nbr;

        for (int i = 0, h = numberOfMonths, z = 0; ((i < numberOfMonths) && (h > 0)); i++, h--, z++) {

            baseRow = new String[5];
            j = 0;
            nbr = 0;
            cpt = 0;
            if ((startMonth - i) == 0) {
                startMonth = 12;
                year = String.valueOf(Integer.parseInt(year) - 1);
                i = 0;
            }
            data = RE.getRUB(matricule, String.format("%02d", (startMonth - i)), year);
            if (data[0] != null) {
                while (data[j] != null) {
                    if (data[j][0].equals("300")) baseRow[3] = data[j][3];
                    if (data[j][0].equals("311")) {
                        nbr += Double.parseDouble(data[j][3]);
                        cpt = 1;
                    }
                    if (data[j][0].equals("331")) {
                        nbr += Double.parseDouble(data[j][3]);
                        if (cpt == 1) cpt = 3;
                        else cpt = 2;
                    }
                    j++;
                }
                if (cpt == 0) baseRow[2] = "/";
                else if (cpt == 1) baseRow[2] = "Absence et/ou retard";
                else if (cpt == 2) baseRow[2] = "Congé maladie";
                else baseRow[2] = "Congé maladie et Absence et/ou retard";
                baseRow[4] = String.valueOf(Double.parseDouble(baseRow[3]) * 0.9);
                baseRow[1] = String.valueOf((30 - (int) nbr));
                baseRow[0] = DATE.MonthNameByNumber(String.format("%02d", (startMonth - i))) + " " + year;
                base[z] = baseRow;
                System.out.println((startMonth - i) + " : ");
                System.out.println(Arrays.toString(base[i]));
            }
        }
        return baseCleaner(base);
    }


}
