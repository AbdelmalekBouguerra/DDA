/*
 * Author : Abdelmalek BOUGUERRA
 * Author Email : abdelmalekbouguerra2000@gmail.com.
 * Created : Aug 2021
 * Library : Free Spire.Doc for Java. https://www.e-iceblue.com/Introduce/free-doc-for-java.html
 * description : This class take data from Db then creat the pdf files and the review of the files svg or png.
 *
 * (c) Copyright by Abdelmalek BOUGUERRA.
 */

package Classes;

import DAO.*;
import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import com.spire.doc.Table;
import com.spire.doc.documents.VerticalAlignment;
import com.spire.pdf.PdfDocument;
import com.spire.pdf.xmp.XmpMetadata;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.Map;

import static DAO.DB.*;

public class Printer {

    String MAT;
    public Printer(String MAT) {
        this.MAT = MAT;
    }

    // Diviser le nom complet en prénom et nom de famille
    public static String[] splitName(String name) {
        String[] nameS = name.split(" ");
        String[] nes = new String[5];
        int j = 0;
        for (String s : nameS) {
            if (!s.isEmpty()) {

                if (s.equals("EP")) {
                    break;
                } else {
                    nes[j] = s.trim();
                    j++;
                }
            }
        }
        System.out.println("output" + Arrays.toString(nameS));
        System.out.println("output filtered " + Arrays.toString(nes));
        return nes;
    }

    public static String[] splitByNumber(String str, int size) {
        return (size < 1 || str == null) ? null : str.split("(?<=\\G.{" + size + "})");
    }

    public static String splitWillaya(String loc) {
        LOCDAO locdao = new LOCDAO();
        String[] tmp;
        String newloc = "";
        System.out.println("code :" + loc + " loc : " + locdao.getLOC(loc));
        if (loc.length() == 3) {
            tmp = splitByNumber(loc, 1);
            assert tmp != null;
            newloc = tmp[0]+"000";
        } else if (loc.length() == 4) {
            tmp = splitByNumber(loc, 2);
            assert tmp != null;
            newloc = tmp[0]+"00";
        }
        System.out.println("new code :" + newloc + " loc : " + locdao.getLOC(newloc));
        return locdao.getLOC(newloc);
    }

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

    public static String reverseArray(String inputArray) {
        String[] RA = inputArray.split("-");
        return RA[2]+"-"+RA[1]+"-"+RA[0];
    }

    public static int numberOfMonths(String Start,String End){

        String[] StartSplit = Start.split("-");
        String[] EndSplit = End.split("-");
        int m1,y1,m2, y2;
        //2021-10-05
        m1 = Integer.parseInt(StartSplit[1]);
        m2 = Integer.parseInt(EndSplit[1]);

        y1 = Integer.parseInt(StartSplit[0]);
        y2 = Integer.parseInt(EndSplit[0]);
        System.out.println(m1+" "+m2+" "+y1+" "+y2);

        if (y1 == y2){
            return m2-m1+1;
        }else{
            return  ((12 - m1) + 1 + m2);
        }
    }

    public static String printerRoot(){
        String path = Printer.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String[] paths = path.split("/");
        for (String a : paths)
            System.out.println(a);
        System.out.println("Path is : "+path);
        System.out.println("--------------------------------");
        String root = "";
        for (int i = 1; i < paths.length-2; i++) {
            root += paths[i]+"\\";
        }
        root = root.replaceAll("%20"," ");
        return root;
    }

    public static boolean isUserExist(String mat, String year, String month) {
        try {
            String check = "SELECT * FROM rub_" + year + " WHERE matricule = ? AND mois = ? AND annee = ? ;";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connection established");
            PreparedStatement pStatement = connection.prepareStatement(check);
            pStatement.setString(1,mat);
            pStatement.setString(2,month);
            pStatement.setString(3,year);
            ResultSet resultSet = pStatement.executeQuery();
            // if a record found return true
            System.out.println("Database Connection Terminated (Printer.isUserExist)");
            if (resultSet.next()) return true;
            pStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return false;
    }



    /** getAlphanumeric function :
     * find where alphanumeric is in the data matrix (numRub from rub table).
     * @param data data from rub table.
     * @return vector of index where alphanumeric rub is.
     */
    public static int[] getAlphanumeric(String[][] data) {
        // get how the number of alphanumeric rubs
        int k = 0;
        for (int i = 0; i < data.length && data[i] != null; i++) {
            if (!isNumeric(data[i][0]) && !data[i][0].isEmpty()) {
                k++;
            }
        }
        // initialise index vector.
        int[] num = new int[k];

        int j = 0;
        // insertion of indexes in the vector
        for (int i = 0; i < data.length && data[i] != null; i++) {
            if (!isNumeric(data[i][0]) && !data[i][0].isEmpty()) {
                num[j] = i;
                j++;
            }
        }
        return num;
    }

    /** firstChar function:
     * get the first char from a string.
     * @param str the input string.
     * @return one char string contain the first char from the input string.
     */
    public static String firstChar(String str){
        String[] splited = str.split("(?!^)");
        return splited[0];
    }

    /** sortingRub function:
     * this function take rub data matrix and place the alphanumeric before the numeric like "4AT" before "406".
     *
     * @param data data from rub table.
     * @return the same data table but sorted.
     */
    public static String[][] sortingRub(String[][] data){
        String fc = "";
        String[] save;

        // get the alphanumeric indexes.
        int[] indexes = getAlphanumeric(data);

        /*
         * get the first character from the alphanumeric and found the first match and place it before that
         * and shift the matrix down.
         */


        for (int i = 0; i < indexes.length; i++) {
            // get the first char of the rub
            fc = firstChar(data[indexes[i]][0]);
            // search where the fc is in the data table
            for (int j = 0; j < data.length && data[j] != null; j++) {
                if (firstChar(data[j][0]).equals(fc)){

                    // save the alphanumeric row
                    save = data[indexes[i]];

                    // shift down and erase the alphanumeric row
                    for (int k = indexes[i]; k > j-1; k--) {
                        data[k] = data[k-1];
                    }

                    // set the alphanumeric row in the position before all the numeric values.
                    data[j] = save;
                    break;
                }
            }
        }
        return data;
    }

    public void PrintAT() {

        HISDAO hisdao = new HISDAO();
        String year = DATE.GetYear();
        String day = DATE.GetDayNum();


        String month,lastyear;
        if (DATE.GetMonthNum().equals("01")){
            month = "12";
            lastyear = String.format("%02d", (Integer.parseInt(DATE.GetYear())-1));
        } else {
            month = String.format("%02d", (Integer.parseInt(DATE.GetMonthNum())-1));
            lastyear =  DATE.GetYear();
        }


        // check file if exist or not
        String filepath = printerRoot()+"RESULT\\AttestationDeTravail\\ATS" + MAT + "_" + day + "_" + DATE.GetMonthNum() +
                "_" + year;
        File tmpDir = new File(filepath + ".pdf");

        if (!tmpDir.exists()) {
            // track action
            hisdao.saveAction(MAT, "Creation de Attestation de travail");
            hisdao.saveAction(MAT, "Consultation de Attestation de travail");
            System.out.println("file :" + filepath + " not found start making it");

            PERSDAO persdao = new PERSDAO();
            LOCDAO locdao = new LOCDAO();
            STRDAO strdao = new STRDAO();
            BareCode bareCode = new BareCode();

            //create a document instance
            Document doc = new Document();
            //load the template file
            doc.loadFromFile("C:\\template\\AttestationDeTravail\\AttestationDeTravail.docx");
            // fetch data form DB
            Map<String, String> infoList = persdao.getPERS(MAT,lastyear,month);
            // creation qr code.
            String QR_Code = "QR" + MAT + "_" + year + "_" + DATE.GetMonthNum() + ".png";
            String text = MAT + " " + infoList.get("nom") + " " + infoList.get("fonction");
            bareCode.creatBareCode(text, QR_Code, 1, 1, false);

            // set ref
            REFDAO refdao = new REFDAO();
            Map<String, String> refList =
                    refdao.getREF(infoList.get("str"),splitWillaya(infoList.get("loctrav")));
            int ref = refdao.setREFdoc(refList.get("code"),"refAT");

            //replace text in the document
            doc.replace("#date", DATE.GetDate(), true, true);
            doc.replace("#NE", ((infoList.get("sexe").equals("F")) ? "née" : "né"), true, true);
            doc.replace("#EMP", ((infoList.get("sexe").equals("F")) ? "employée" : "employé"), true, true);
            doc.replace("#NOM", infoList.get("nom"), true, true);
            doc.replace("#M/Mme", infoList.get("civilite"), true, true);
            doc.replace("#DATNAIS", DATE.ChangeFormat(infoList.get("datenais")), true, true);
            doc.replace("#LIEUNAIS", locdao.getLOC(infoList.get("codelieunais")), true, true);
            doc.replace("#DATREC", DATE.ChangeFormat(infoList.get("daterec")), true, true);
            doc.replace("#LIBFONC", infoList.get("fonction"), true, true);
            doc.replace("#CODDEP", strdao.getSTR(infoList.get("str")), true, true);
            doc.replace("#WILLYA", splitWillaya(infoList.get("codelieunais")), true, true);

            bareCode.replaceTextWithImage(doc, QR_Code, "QR_Code");

            System.out.println("ref"+ Arrays.toString(new Map[]{refList}));

            doc.replace("#DIR", refList.get("lib"), true, true);
            doc.replace("#REF", String.valueOf(ref), true, true);
            doc.replace("#CODE", refList.get("code"), true, true);
            doc.replace("#YEAR", DATE.GetYear(), true, true);
            doc.replace("#DIRECTEUR", refList.get("directeur"), true, true);
            doc.replace("#WIL", refList.get("commune"), true, true);

            //set title to pdf
            doc.saveToFile(filepath + ".pdf", FileFormat.PDF);

            System.out.println("setting meta data");
            PdfDocument pdf = new PdfDocument();
            System.out.println("setting meta data");
            pdf.loadFromFile(filepath + ".pdf");
            XmpMetadata meta = pdf.getXmpMetaData();
            meta.setTitle("Attestation de travail");
            pdf.saveToFile(filepath + ".pdf");
            System.out.println("done setting meta data");
            bareCode.delete(QR_Code);
        } else {
            System.out.println("file" + filepath + " founded -- i'm fast af boi --");
            // track action
            hisdao.saveAction(MAT, "Consultation de Attestation de travail");
        }
    }

    public void PrintBP(String month, String year) {

        HISDAO hisdao = new HISDAO();

        // check file if exist or not
        String filepath = printerRoot()+"RESULT\\BulletinPaie\\PB" + MAT + "_" + year + "_" + month;
        File tmpDir = new File(filepath + ".pdf");

        if (!tmpDir.exists()) {
            System.out.println("file :" + filepath + " not found start making it");
            // track action
            hisdao.saveAction(MAT, "Creation de bulletin de paie");
            hisdao.saveAction(MAT, "Consultation de bulletin de paie");

            PERSDAO persdao = new PERSDAO();
            RUBDAO rubdao = new RUBDAO();
            BareCode bareCode = new BareCode();

            //create a document instance
            Document doc = new Document();
            doc.addSection();
            //load the template file
            // todo: add dynamic file path
            doc.loadFromFile("C:\\template\\FicheDePaie.docx");

            // fetch data form DB

            String[][] data =sortingRub(rubdao.getRUB(MAT, month, year));
            String[][] total = rubdao.CalBP(data);
            Map<String, String> infoList = persdao.getPERS(MAT,year,month);
            System.out.println("info \n"+infoList);
            // checking if pers of this month exist
            if (null == infoList.get("datenais")){

                String lastmonth,lastyear;
                if (DATE.GetMonthNum().equals("01")){
                    lastmonth = "12";
                    lastyear = String.format("%02d", (Integer.parseInt(DATE.GetYear())-1));
                } else {
                    lastmonth = String.format("%02d", (Integer.parseInt(DATE.GetMonthNum())-1));
                    lastyear =  DATE.GetYear();
                }

                infoList = persdao.getPERS(MAT,lastyear,lastmonth);
                System.out.println(infoList);
            }

            // creation qr code.
            String QR_Code = "QR" + MAT + "_" + year + "_" + month + ".png";
            String text = MAT + " " + infoList.get("nom") + "\nNet a paye :" + total[0][2];
            bareCode.creatBareCode(text, QR_Code, 0.53F, 0.5F, true);

            //replace text in the document

            doc.replace("#RETRO",((rubdao.isRetro(MAT,month,year)) ? "RETRO" : ""),true,true);
            doc.replace("#NOM", infoList.get("nom"), true, true);
            doc.replace("#DATNAIS", DATE.ChangeFormat(infoList.get("datenais")), true, true);
            doc.replace("#DATREC", DATE.ChangeFormat(infoList.get("daterec")), true, true);
            doc.replace("#LIBFONC", infoList.get("fonction"), true, true);
            doc.replace("#MAT", MAT, true, true);
            doc.replace("#NSSEMP", infoList.get("nssemp"), true, true);
            doc.replace("#SF", infoList.get("sf"), true, true);
            doc.replace("#GSANG", infoList.get("gsang"), true, true);
            String IRG = "";
            int Enf = Integer.parseInt(infoList.get("nbrenfs10")) + Integer.parseInt(infoList.get("nbrenfm10"));
            if ((infoList.get("sf").equals("C"))
                    || (infoList.get("sf").equals("D"))
                    || (infoList.get("sf").equals("V") && Enf == 0))
                IRG = "1";
            else if ((infoList.get("sf").equals("M") && Enf == 0)
                    || (infoList.get("sf").equals("D") && Enf == 1)
                    || (infoList.get("sf").equals("V") && Enf == 1))
                IRG = "2";
            else if ((infoList.get("sf").equals("M") && Enf >= 1)
                    || (infoList.get("sf").equals("D") && Enf >= 2)
                    || (infoList.get("sf").equals("V") && Enf >= 2))
                IRG = "3";

            doc.replace("#IRG", IRG, true, true);
            doc.replace("#SCJT", infoList.get("scjt"), true, true);
            doc.replace("#ENFT","0"+String.valueOf((Integer.parseInt(infoList.get("nbrenfm10")) +
                    Integer.parseInt(infoList.get("nbrenfs10")))), true, true);
            doc.replace("#ENFI", infoList.get("nbrenfm10"), true, true);
            doc.replace("#NO_RIB", infoList.get("rib"), true, true);

            String css = "";
            if (infoList.get("css").equals("1")){
                css = "\nCASORAL "+ infoList.get("nssagt");
            }else if (infoList.get("css").equals("2")){
                css = "\nCASORAN "+ infoList.get("nssagt");
            }else css = "\nCASOREC "+ infoList.get("nssagt");

//            doc.replace("#CAS",cas,true,true);
            String[] div = infoList.get("str").split("(?!^)");
            doc.replace("#DIV", div[0], true, true);
            doc.replace("#DIR", div[1] + div[2], true, true);
            doc.replace("#DPT", div[3], true, true);
            String[] CPAIEM = infoList.get("cpaiem").split("(?!^)");
            doc.replace("#MP", CPAIEM[0], true, true);
            doc.replace("#B", CPAIEM[1], true, true);
            doc.replace("#A", CPAIEM[2] + CPAIEM[3], true, true);
            doc.replace("#ANNE", year, true, true);
            doc.replace("#MOIE", DATE.formatMonth(Integer.parseInt(month)), true, true);
            doc.replace("#GROUPE",infoList.get("groupe") , true, true);
            doc.replace("#ECHELLE", infoList.get("echelle"), true, true);
            doc.replace("#SO", (infoList.get("suitorg") == null ? " " : infoList.get("suitorg"))
                    , true, true);


            bareCode.replaceTextWithImage(doc, QR_Code, "QR_Code");
            // get the table
            Table table = doc.getSections().get(0).getTables().get(0);
            DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
            symbols.setGroupingSeparator(' ');
            symbols.setDecimalSeparator('.');
            DecimalFormat df = new DecimalFormat("#,##0.00",symbols);
            for (int r = 0; r < data.length; r++) {
                if (!(data[r] == null)) {
                    for (int c = 0; c < data[r].length; c++) {
                        //fill data in cells
                        if (c == 2 || c == 3 ||  c == 4 || c == 5 || c == 6 || c == 7) {
                            if (!data[r][c].isEmpty())
                                table.getRows().get(r).getCells().get(c).getParagraphs().get(0).setText(df.format(Double.parseDouble(data[r][c])));
                            else
                                table.getRows().get(r).getCells().get(c).getParagraphs().get(0).setText("");
                        } else {
                            table.getRows().get(r).getCells().get(c).getParagraphs().get(0).setText((data[r][c]));
                        }
                        table.getRows().get(r).getCells().get(c).getCellFormat().setVerticalAlignment(VerticalAlignment.Middle);
                    }
                }

            }

            if (!(total[0] == null)) {
                for (int c = 0; c < total[0].length; c++) {
                    //fill data in cells
                    if (c == 0)
                        table.getRows().get(33).getCells().get(c).getParagraphs().get(0).setText(css);
                    else
                        table.getRows().get(33).getCells().get(c).getParagraphs().get(0).setText(total[0][c]);
                    table.getRows().get(33).getCells().get(c).getCellFormat().setVerticalAlignment(VerticalAlignment.Middle);

                }
                //save file in pdf format and svg format
                doc.saveToFile(filepath + ".pdf", FileFormat.PDF);

                System.out.println("setting meta data");
                PdfDocument pdf = new PdfDocument();
                System.out.println("setting meta data");
                pdf.loadFromFile(filepath + ".pdf");
                XmpMetadata meta = pdf.getXmpMetaData();
                meta.setTitle("Bulletin de paie");
                pdf.saveToFile(filepath + ".pdf");
                System.out.println("done setting meta data");

                // deleting QR code
                bareCode.delete(QR_Code);
            }
        } else {
            System.out.println("file" + filepath + " founded -- i'm fast af boi --");
            // track action
            hisdao.saveAction(MAT, "Consultation de bulletin de paie");

        }
    }

    public void PrintRE(String year) throws IOException {
        HISDAO hisdao = new HISDAO();

        // check file if exist or not
        String filepath = printerRoot()+"RESULT\\ReleveEmoluments\\RE" + MAT + "_" + year;
        File tmpDir = new File(filepath + ".pdf");
        if (!tmpDir.exists()) {
            System.out.println("file :" + filepath + " not found start making it");
            // track action
            hisdao.saveAction(MAT, "Creation de releve des emolument");
            hisdao.saveAction(MAT, "Consultation de releve des emolument");

            PERSDAO persdao = new PERSDAO();
            BareCode bareCode = new BareCode();

            //create a document instance
            Document doc = new Document();
            //load the template file
            doc.loadFromFile("C:\\template\\ReleveDesEmoluments.docx");

            // fetch data form DB

            String month,lastyear;
            if (DATE.GetMonthNum().equals("01")){
                month = "12";
                lastyear = String.format("%02d", (Integer.parseInt(DATE.GetYear())-1));;
            } else {
                month = String.format("%02d", (Integer.parseInt(DATE.GetMonthNum())-1));
                lastyear =  DATE.GetYear();
            }
            Map<String, String> infoList = persdao.getPERS(MAT,lastyear,month);
            // fix her 01 bcz i dont have 03
            // todo change it
            double[] net = RE.getRE(MAT, lastyear,month);


            // creation qr code.
            String QR_Code = "QR" + MAT + "_" + year + ".png";
            String text = MAT + " " + infoList.get("nom") + " " + infoList.get("fonction")
                    + " net annuel :" + net[0];
            bareCode.creatBareCode(text, QR_Code, 1, 1, true);

            // ref

            REFDAO refdao = new REFDAO();
            Map<String, String> refList =
                    refdao.getREF(infoList.get("str"),splitWillaya(infoList.get("loctrav")));
            int ref = refdao.setREFdoc(refList.get("code"),"refRE");

            //replace text in the document
            doc.replace("#MAT", MAT, true, true);
            doc.replace("#NOM", infoList.get("nom"), true, true);
            doc.replace("#NAIS", DATE.ChangeFormat(infoList.get("datenais")), true, true);
            doc.replace("#REC", DATE.ChangeFormat(infoList.get("daterec")), true, true);
            doc.replace("#FOC", infoList.get("fonction"), true, true);
            doc.replace("#NSS", infoList.get("nssagt"), true, true);
            doc.replace("#date", DATE.GetDate(), true, true);

            DecimalFormat df = new DecimalFormat("#,###.##");
            doc.replace("#TOTAL", df.format(net[0]), true, true);


            doc.replace("#DIR",refList.get("directeur") , true, true);
            doc.replace("#REF", String.valueOf(ref), true, true);
            doc.replace("#CODE", refList.get("code"), true, true);
            doc.replace("#YEAR", DATE.GetYear(), true, true);
            doc.replace("#DIRECTEUR", refList.get("directeur"), true, true);
            doc.replace("#WIL", refList.get("willaya"), true, true);

            bareCode.replaceTextWithImage(doc, QR_Code, "QR_Code");

            //save file to pdf and svg
            doc.saveToFile(filepath + ".pdf", FileFormat.PDF);
            //save every PDF to .png image
            System.out.println("setting meta data");
            PdfDocument pdf = new PdfDocument();
            System.out.println("setting meta data");
            pdf.loadFromFile(filepath + ".pdf");
            XmpMetadata meta = pdf.getXmpMetaData();
            meta.setTitle("relevé d'emolument");
            pdf.saveToFile(filepath + ".pdf");
            System.out.println("done setting meta data");
            bareCode.delete(QR_Code);

        } else {
            System.out.println("file" + filepath + " founded -- i'm fast af boi --");
            // track action
            hisdao.saveAction(MAT, "Consultation de releve des emolument");

        }

    }

    public void PrintRED(String year) throws IOException {
        HISDAO hisdao = new HISDAO();

        // check file if exist or not
        String filepath = printerRoot()+"RESULT\\ReleveEmoluments\\RED" + MAT + "_" + year;
        File tmpDir = new File(filepath + ".pdf");
        if (!tmpDir.exists()) {
            System.out.println("file :" + filepath + " not found start making it");
            // track action
            hisdao.saveAction(MAT, "Creation de releve des emolument Detaille");
            hisdao.saveAction(MAT, "Consultation de releve des emolument Detaille");

            PERSDAO persdao = new PERSDAO();
            BareCode bareCode = new BareCode();
            LOCDAO locdao = new LOCDAO();

            //create a document instance
            Document doc = new Document();
            //load the template file
            // todo: add dynamic file path
            doc.loadFromFile("C:\\template\\ReleveDesEmolumentsDetaille.docx");

            // fetch data form DB
            String month,lastyear;
            if (DATE.GetMonthNum().equals("01")){
                month = "12";
                lastyear = String.format("%02d", (Integer.parseInt(DATE.GetYear())-1));;
            } else {
                month = String.format("%02d", (Integer.parseInt(DATE.GetMonthNum())-1));;
                lastyear =  DATE.GetYear();
            }
            Map<String, String> infoList = persdao.getPERS(MAT,lastyear,month);

            double[] net = RE.getRE(MAT, year,month);

            // creation qr code.
            String QR_Code = "QR" + MAT + "_" + year + ".png";
            String text = MAT + " " + infoList.get("nom") + " " + infoList.get("fonction")
                    + "\n net annuel :" + net[0];
            bareCode.creatBareCode(text, QR_Code, 1, 1, false);

            // ref

            REFDAO refdao = new REFDAO();
            Map<String, String> refList =
                    refdao.getREF(infoList.get("str"),splitWillaya(infoList.get("loctrav")));
            int ref = refdao.setREFdoc(refList.get("code"),"refRED");


            //replace text in the document
            doc.replace("#NOM", infoList.get("nom"), true, true);
            doc.replace("#DATNAIS", DATE.ChangeFormat(infoList.get("datenais")), true, true);
            doc.replace("#LIEUNAIS", locdao.getLOC(infoList.get("codelieunais")), true, true);
            doc.replace("#WILLIYA", splitWillaya(infoList.get("codelieunais")), true, true);
            doc.replace("#DATREC", DATE.ChangeFormat(infoList.get("daterec")), true, true);
            doc.replace("#FONCT", infoList.get("fonction"), true, true);
            doc.replace("#date", DATE.GetDate(), true, true);

            DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
            symbols.setGroupingSeparator(' ');

            DecimalFormat df = new DecimalFormat("###,###.##", symbols);
            doc.replace("#SALBRUT", df.format(net[1]), true, true);
            doc.replace("#ASSIMP", df.format(net[2]), true, true);
            doc.replace("#IRG", df.format(net[3]), true, true);
            doc.replace("#SS", df.format(net[4]), true, true);
            doc.replace("#RGRCR", df.format(net[5]), true, true);
            doc.replace("#AUTRET", df.format(net[6]), true, true);
            doc.replace("#NET", df.format(net[0]), true, true);


            doc.replace("#DIR", refList.get("directeur"), true, true);
            doc.replace("#REF", String.valueOf(ref), true, true);
            doc.replace("#CODE", refList.get("code"), true, true);
            doc.replace("#YEAR", DATE.GetYear(), true, true);
            doc.replace("#DIRECTEUR", refList.get("directeur"), true, true);
            doc.replace("#WIL", refList.get("willaya"), true, true);

            bareCode.replaceTextWithImage(doc, QR_Code, "QR_Code");

            //save file to pdf and svg
            doc.saveToFile(filepath + ".pdf", FileFormat.PDF);
            //save every PDF to .png image


            System.out.println("setting meta data");
            PdfDocument pdf = new PdfDocument();
            System.out.println("setting meta data");
            pdf.loadFromFile(filepath + ".pdf");
            XmpMetadata meta = pdf.getXmpMetaData();
            meta.setTitle("relevé d'emolument detaillé");
            pdf.saveToFile(filepath + ".pdf");
            System.out.println("done setting meta data");

            bareCode.delete(QR_Code);

        } else {
            System.out.println("file" + filepath + " founded -- i'm fast af boi --");
            // track action
            hisdao.saveAction(MAT, "Consultation de releve des emolument");

        }

    }

    public void PrintATC(String datepicker_start,String datepicker_end,String yearStart,int monthStart,int sl) {
        HISDAO hisdao = new HISDAO();
        String year = DATE.GetYear();

        // check file if exist or not
        String filepath = printerRoot()+"RESULT\\ATC" + MAT + "_" + year + "_" + DATE.GetMonthNum();
        File tmpDir = new File(filepath + ".pdf");

        if (!tmpDir.exists()) {
            // track action
            hisdao.saveAction(MAT, "Creation de Attestation de travail CNAS");
            hisdao.saveAction(MAT, "Consultation de Attestation de travail CNAS");
            System.out.println("file :" + filepath + " not found start making it");

            PERSDAO persdao = new PERSDAO();
            LOCDAO locdao = new LOCDAO();

            //create a document instance
            Document doc = new Document();
            //load the template file
            doc.loadFromFile("C:\\template\\ATS.docx");
            // fetch data form DB
            String month,lastyear;
            if (DATE.GetMonthNum().equals("01")){
                month = "12";
                lastyear = String.format("%02d", (Integer.parseInt(DATE.GetYear())-1));;
            } else {
                month = String.format("%02d", (Integer.parseInt(DATE.GetMonthNum())-1));;
                lastyear =  DATE.GetYear();
            }
            Map<String, String> infoList = persdao.getPERS(MAT,lastyear,month);

            System.out.println("infoList");
            System.out.println(Arrays.toString(new Map[]{infoList}));

            //replace text in the document

            // set ref
            REFDAO refdao = new REFDAO();
            Map<String, String> refList =
                    refdao.getREF(infoList.get("str"),splitWillaya(infoList.get("loctrav")));

            doc.replace("#Agence", refList.get("commune"), true, true);
            doc.replace("#CentrePaiment",refList.get("commune"), true, true);
            doc.replace("#ADDEMPLOYEUR", refList.get("address"), true, true);
            //todo debug here
            System.out.println("DEBUG start ==============================");
            System.out.println("NSSEMP : "+infoList.get("nssemp"));
            String[] nbrAdh = splitByNumber(infoList.get("nssemp"), 3);
            String[] wl = splitByNumber(infoList.get("loctrav"),2);
            System.out.println("nbradh 0 : "+ nbrAdh[0]);
            System.out.println("nbradh 1 : "+ nbrAdh[1]);

            assert wl != null;
            doc.replace("#NbrAdh",  wl[0]+" " + nbrAdh[0] + " "
                    + nbrAdh[1] + " " + "56", true, true);
            String[] name = Printer.splitName(infoList.get("nom"));
            doc.replace("#NOM", name[0], true, true);

            if (datepicker_start.isEmpty())
                doc.replace("#DJT","", true, true);
            else
                doc.replace("#DJT",reverseArray(datepicker_start), true, true);

            if (datepicker_end.isEmpty())
                doc.replace("#DRT","", true, true);
            else
                doc.replace("#DRT",reverseArray(datepicker_end), true, true);

            doc.replace("#PRENOM", name[1], true, true);
            doc.replace("#DATNAIS", DATE.ChangeFormat(infoList.get("datenais")), true, true);
            doc.replace("#DATREC", DATE.ChangeFormat(infoList.get("daterec")), true, true);
            doc.replace("#LIBFONC", infoList.get("fonction"), true, true);
            doc.replace("#ADDEMP", infoList.get("adresse"), true, true);
            doc.replace("#FONCTION", infoList.get("fonction"), true, true);
            doc.replace("#DATEREC", DATE.ChangeFormat(infoList.get("daterec")), true, true);

//           String[] der = der(infoList.get("str"), "refAT");
            doc.replace("#DIR", refList.get("directeur"), true, true);
            doc.replace("#DIRECTEUR", refList.get("directeur"), true, true);
            doc.replace("#Date", DATE.GetDate(), true, true);
            String[] NSS = splitByNumber(infoList.get("nssagt"), 2);
            assert NSS != null;
            doc.replace("#NSS", NSS[0] + " " + NSS[1] + NSS[2] + " " + NSS[3] + NSS[4] + " " + NSS[5], true, true);
            doc.replace("#WILLYA", splitWillaya(locdao.getLOC(infoList.get("codelieunais"))), true, true);
            // get the table
            RUBDAO rubdao = new RUBDAO();
            String[][] data = rubdao.baseSS(MAT,yearStart,sl,monthStart);
            RUBDAO.reverseArray(data);
            Table table = doc.getSections().get(1).getTables().get(0);
            DecimalFormat df = new DecimalFormat("#,###.##");
            for (int r = 0; r < data.length; r++) {
                if (!(data[r] == null)) {
                    for (int c = 0; c < data[r].length; c++) {
                        //fill data in cells
                        if (c == 3 || c == 4)
                            table.getRows().get(r).getCells().get(c).getParagraphs().get(0).setText(df.format(Double.parseDouble(data[r][c])));
                        else
                            table.getRows().get(r).getCells().get(c).getParagraphs().get(0).setText(data[r][c]);
                        table.getRows().get(r).getCells().get(c).getCellFormat().setVerticalAlignment(VerticalAlignment.Middle);
                    }
                }

            }
            //save file to pdf and svg
            doc.saveToFile(filepath + ".pdf", FileFormat.PDF);
            System.out.println("setting meta data");
            PdfDocument pdf = new PdfDocument();
            System.out.println("setting meta data");
            pdf.loadFromFile(filepath + ".pdf");
            XmpMetadata meta = pdf.getXmpMetaData();
            meta.setTitle("Attestation de travail CNAS");
            pdf.saveToFile(filepath + ".pdf");
            System.out.println("done setting meta data");

        }

    }

}
