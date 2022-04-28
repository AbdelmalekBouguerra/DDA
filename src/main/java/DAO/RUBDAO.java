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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import static DAO.DB.password;
import static DAO.DB.url;
import static DAO.DB.username;

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
                infoList.put("taux" + i, String.valueOf(resultSet.getFloat("taux")));
                infoList.put("base" + i, String.valueOf(resultSet.getFloat("base")));
                System.out.println(i + ". matricule :" + resultSet.getString("matricule") + " added");
                i++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return infoList;
    }

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
        String eff = "";
        try {
            String query = "SELECT * FROM rub_" + year + " WHERE matricule = ? AND mois = ? AND annee = ? ;";

            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connection established");
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setString(1, matricule);
            pStatement.setString(2, month);
            pStatement.setString(3, year);
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

    public boolean check(String table) throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
        DatabaseMetaData dbm = connection.getMetaData();
        // check if "employee" table is there
        ResultSet tables = dbm.getTables(null, null, table, null);
        // Table exists return tru
        // Table does not exist return false
        connection.close();
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
            if (resultSet.next()){
                connection.close();
                return true;
            }
            pStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        // todo : fix null in datenais in PERS and datefin in RUB
        return false;
    }

    public static LinkedHashMap<String, String> readXLSRUB(String file) throws IOException {

        File excelFile = new File(file);
        FileInputStream fis = new FileInputStream(excelFile);

        // we create an XSSF Workbook object for our XLSX Excel File
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        // we get first sheetc
        XSSFSheet sheet = workbook.getSheetAt(0);

        // we iterate on rows
        Iterator<Row> rowIt = sheet.iterator();
        LinkedHashMap<String, String> infoList = new LinkedHashMap<>();
        Map<String, Integer> Headers = new HashMap<>();
        int i = 1;
        while (rowIt.hasNext()) {
            Row row = rowIt.next();
            // iterate on cells for the current row
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                if (row.getRowNum() == 0) {// first iteration
                    // we get all the headers we want and put them in list  like so : DATE_EXP,0 / MAT , 0
                    // to avoid errors when the not have the same order of the headers
                    switch (cell.toString()) {
                        case "DATE_EXPL": // if cell value is DATE_EXPL we save the index of that header in map list
                            Headers.put("DATE_EXPL", cell.getColumnIndex());
                            break;
                        case "MAT": // if cell value is DATE_EXPL we save the index of that header in map list
                            Headers.put("MAT", cell.getColumnIndex());
                            break;
                        case "MOIS_EFFET":
                            Headers.put("MOIS_EFFET", cell.getColumnIndex());
                            break;
                        case "ANN_EFFET":
                            Headers.put("ANN_EFFET", cell.getColumnIndex());
                            break;
                        case "LIB_RUB":
                            Headers.put("LIB_RUB", cell.getColumnIndex());
                            break;
                        case "NO_RUB":
                            Headers.put("NO_RUB", cell.getColumnIndex());
                            break;
                        case "CODE_NATUR":
                            Headers.put("CODE_NATUR", cell.getColumnIndex());
                            break;
                        case "TAUX":
                            Headers.put("TAUX", cell.getColumnIndex());
                            break;
                        case "ELEM_STAT":
                            Headers.put("ELEM_STAT", cell.getColumnIndex());
                            break;
                        case "DT_DEB":
                            Headers.put("DT_DEB", cell.getColumnIndex());
                            break;
                        case "DE_FIN":
                            Headers.put("DE_FIN", cell.getColumnIndex());
                            break;
                        case "MT_MOIS":
                            Headers.put("MT_MOIS", cell.getColumnIndex());
                            break;
                        case "MT_RAPPEL":
                            Headers.put("MT_RAPPEL", cell.getColumnIndex());
                            break;

                    }
                } else {
                    //now we creat our linked list using headers
                    int columnIndex = cell.getColumnIndex();
                    if (columnIndex == Headers.get("DATE_EXPL")) infoList.put("dateexpl" + i, cell.toString());
                    else if (columnIndex == Headers.get("MAT")) infoList.put("matricule" + i, cell.toString());
                    else if (columnIndex == Headers.get("NO_RUB")) infoList.put("numrub" + i, cell.toString());
                    else if (columnIndex == Headers.get("MOIS_EFFET")) infoList.put("mois" + i, cell.toString());
                    else if (columnIndex == Headers.get("ANN_EFFET")) infoList.put("annee" + i, cell.toString());
                    else if (columnIndex == Headers.get("LIB_RUB")) infoList.put("librub" + i, cell.toString());
                    else if (columnIndex == Headers.get("CODE_NATUR")) infoList.put("code_natur" + i, cell.toString());
                    else if (columnIndex == Headers.get("DT_DEB")) if (!infoList.get("code_natur" + i).equals("1"))
                        infoList.put("datedeb" + i, cell.toString());
                    else
                        infoList.put("datedeb" + i, null);
                    else if (columnIndex == Headers.get("DE_FIN")) if (!infoList.get("code_natur" + i).equals("1"))
                        infoList.put("datefin" + i, cell.toString());
                    else
                        infoList.put("datefin" + i, null);
                    else if (columnIndex == Headers.get("MT_MOIS")) {
                        if (infoList.get("code_natur" + i).equals("1"))
                            infoList.put("montantmois" + i, cell.toString());
                    } else if (columnIndex == Headers.get("MT_RAPPEL")) {
                        if (!infoList.get("code_natur" + i).equals("1"))
                            infoList.put("montantmois" + i, cell.toString());
                    } else if (columnIndex == Headers.get("TAUX")) infoList.put("taux" + i, cell.toString());
                    else if (columnIndex == Headers.get("ELEM_STAT")) infoList.put("base" + i, cell.toString());
                }
            }
            i++;
        }
        workbook.close();
        fis.close();
        return infoList;

    }

    public void setRUBXLS(String file, String year, String month) throws IOException {
        LinkedHashMap<String, String> infoList = readXLSRUB(file);
        String date = month + "/" + year;
        String yearRub = "";
        String yearEff = "";
        try {
            // sql query
            String creat = "CREATE TABLE rub_" + year + "(id int NOT NULL AUTO_INCREMENT," +
                    "matricule VARCHAR(30) NOT NULL,dateexpl VARCHAR(8),numRub VARCHAR(3),librub VARCHAR(255)," +
                    "datedeb VARCHAR(8),datefin VARCHAR(8),montantmois DECIMAL(19,2) ,taux DECIMAL(19,2)," +
                    "base DECIMAL(19,2),mois varchar(3),annee varchar(5),PRIMARY KEY (id));";
            String delete = "DELETE FROM rub_" + year + " WHERE mois = ? AND annee = ? ;";

            // Now, lets us start reading the rows
            Connection connection = DriverManager.getConnection(url, username, password);
            Class.forName("com.mysql.cj.jdbc.Driver");
            PreparedStatement pStatement;

            if (!check("rub_" + year)) {
                System.out.println("table rub_" + year + " do not exist");
                System.out.println("creating rub_" + year);
                pStatement = connection.prepareStatement(creat);
                pStatement.executeUpdate();
                pStatement.close();
            }
            if (checkDate(year, month)) {
                System.out.println(date + "already exist in table rub_" + year);
                System.out.println("deletion of the old insertion");
                pStatement = connection.prepareStatement(delete);
                pStatement.setString(1, month);
                pStatement.setString(2, year);
                pStatement.executeUpdate();
                pStatement.close();
            }


            //if it makes error use CODE_NATUR to determine if it has a "rappel" or not .
            for (int i = 0; i <= infoList.size(); i++) {
                if (!(infoList.get("matricule" + i) == null)) {
                    yearRub = infoList.get("annee" + i);

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
                    } else yearEff = year;

                    String Add = "INSERT INTO rub_" + yearEff + "(matricule, dateexpl, numrub, librub, datedeb," +
                            "datefin, montantmois, taux, base,mois,annee) VALUES(?,?,?,?,?,?,?,?,?,?,?);";

//                BigDecimal MT_MOIS = new BigDecimal(row.getString("MT_MOIS"));
                    BigDecimal MT_MOIS, taux, ELEM_STAT;

                    pStatement = connection.prepareStatement(Add);
                    pStatement.setString(1, infoList.get("matricule" + i));
                    pStatement.setString(2, infoList.get("dateexpl" + i));
                    pStatement.setString(3, infoList.get("numrub" + i));
                    System.out.println("row "+i+" "+infoList.get("numrub" + i)) ;
                    pStatement.setString(4, infoList.get("librub" + i));
                    pStatement.setString(5, infoList.get("datedeb" + i));
                    pStatement.setString(6, infoList.get("datefin" + i));
                    MT_MOIS = new BigDecimal(infoList.get("montantmois" + i));
                    pStatement.setBigDecimal(7, MT_MOIS);
                    taux = new BigDecimal(infoList.get("taux" + i));
                    System.out.println("taux : "+taux);
                    pStatement.setBigDecimal(8, taux);
                    ELEM_STAT = new BigDecimal(infoList.get("base" + i));
                    pStatement.setBigDecimal(9, ELEM_STAT);
                    pStatement.setString(10, infoList.get("mois" + i));
                    pStatement.setString(11, infoList.get("annee" + i));

                    pStatement.executeUpdate();
                    pStatement.close();

                }
            }
            connection.close();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

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
