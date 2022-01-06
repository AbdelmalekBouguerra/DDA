/*
 * Author : Abdelmalek BOUGUERRA
 * Author Email : abdelmalekbouguerra2000@gmail.com
 * Created : Oct 2021
 * description : All calculation related to RE.
 *
 * (c) Copyright by Abdelmalek BOUGUERRA.
 */

package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static DAO.DB.*;

public class RE {

    // Obtenez d'abord toutes les données de l'année
    public static String[][] getRUB(String Matricule, String month, String year) {

        // en déclarant la matrice, ils contiendront toutes les informations du RUB.
        String[][] data = new String[50][];

        try {
            // Récupération des données
            String date = month+"/"+year;
            String query = "SELECT * FROM rub_" + year + " WHERE matricule = ? AND dateexpl = '"+date+"'";
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setString(1, Matricule);
            ResultSet resultSet = pStatement.executeQuery();
            // reading from DB.
            int i = 0;
            while (resultSet.next()) {
                String[] dataRow = new String[4];
                dataRow[0] = resultSet.getString("NumRub");
                dataRow[1] = String.valueOf(resultSet.getBigDecimal("MontantMois"));
                dataRow[2] = String.valueOf(resultSet.getBigDecimal("Taux"));
                dataRow[3] = String.valueOf(resultSet.getBigDecimal("Base"));
                data[i] = dataRow;
                i++;
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    // Récupérer saller de base et IAG de la table pers.
    public static double[] getSB_IAG(String Matricule, String month, String year) {
        double[] data = new double[2];
        try {
            // connect to DB.
            String date = month+"/"+year;
            String query = "SELECT * FROM pers_"+year+" WHERE matricule = ? AND dateexpl = '"+date+"'";
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setString(1, Matricule);
            ResultSet resultSet = pStatement.executeQuery();
            // reading from DB.
            while (resultSet.next()) {
                data[0] = Double.parseDouble(String.valueOf(resultSet.getBigDecimal("SalBase")));
                data[1] = Double.parseDouble(String.valueOf(resultSet.getBigDecimal("IAG")));
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    // Calculer IRG
    public static double getIRG(double IRGB) {
        IRGB = IRGB / 10;
        IRGB = Math.round(IRGB);
        IRGB = IRGB * 10;

        double  IRGV = 0;
        if (IRGB >= 0 && IRGB <= 8000) {
            System.out.println("error IRGB cant be under 8000");
        } else if (IRGB >= 8000.00 && IRGB <= 15000.00) {
            IRGV = 0;
        } else if (IRGB >= 15010 && IRGB <= 22500) {
            IRGV = ((IRGB - 15010) / 10) * 2;
        } else if (IRGB >= 22510 && IRGB <= 28750) {
            IRGV = ((IRGB - 22510) / 10) * 1.2 + 1501.20;
        } else if (IRGB >= 28760 && IRGB <= 30000) {
            IRGV = ((IRGB - 28760) / 10) * 2 + 2252;
        } else if (IRGB >= 30010 && IRGB <= 119990) {
            IRGV = ((IRGB - 30010) / 10) * 3 + 2503;
        } else {
            IRGV = ((IRGB - 120000) / 10) * 3.5 + 29496.5;
        }
        return IRGV;
    }

    // Calculer RE à l'aide des dernières fonctions
    public static double[] getRE(String MAT, String year,String month) {
//        String month = String.valueOf(Integer.parseInt(DATE.GetMonthNum()) - 1);
        String[][] data = RE.getRUB(MAT,month, year);
        for (String[] datum : data) {
            if (datum != null) {
                for (String s : datum) {
                    System.out.println(s);
                }
                System.out.println("============================");
            }

        }


        double SalaireBase,
                PrimePanier = 0,
                PrimeTransport = 0,
                IndNuisance = 0,
                IndNourriture = 0,
                IndemniteInterim = 0,
                Revalorisation = 0,
                IFA = 0,
                IAG,
                IZCV = 0,
                RAP_NOUR_IFA_TRANS_PAN = 0,
                RAP_NUIS_ITP_SB_IAG = 0,
                IZIN = 0,
                ITP = 0,
                AssuranceSocialeB,
                AssuranceSociale = 0,
                Retraite = 0,
                RetraiteAnticipee = 0,
                RetAssuanceChomage = 0,
                MIP = 0,
                PCR_MIP = 0,
                RetPret = 0,
                GainsImpo,
                GainsNonImpo = 0,
                IRGB,
                IRG,
                IRGRapB,
                TiersPayant = 0,
                IRGRap,
                RetPrimePanier = 0,
                RetenuesImposable,
                RetenuesNonImposable;


        // get IAG and salaire de base
        double[] sb_iag = getSB_IAG(MAT, month, year);
        SalaireBase = sb_iag[0];
        IAG = sb_iag[1];

        AssuranceSocialeB = SalaireBase + IndemniteInterim + Revalorisation + ITP + IndNuisance
                + IZIN + RAP_NUIS_ITP_SB_IAG + IZCV + IAG;

        int j = 0;
        while (data[j] != null) {
            // calculate prime panier and ind.Nourriture
            if (data[j][0].equals("143")) PrimePanier = (18.0 * Double.parseDouble(data[j][2]));
            if (data[j][0].equals("419")) RetPrimePanier = Double.parseDouble(data[j][1]);
            if (data[j][0].equals("147")){
                IndNourriture = (18.0 * Double.parseDouble(data[j][2]));
                System.out.println(18+" * "+Double.parseDouble(data[j][2])+" = "+ IndNourriture);
            }
            if (data[j][0].equals("300")) AssuranceSociale = AssuranceSocialeB * Double.parseDouble(data[j][2]) / 100;
            if (data[j][0].equals("302")) Retraite = AssuranceSocialeB * Double.parseDouble(data[j][2]) / 100;
            if (data[j][0].equals("304")) RetraiteAnticipee = AssuranceSocialeB * Double.parseDouble(data[j][2]) / 100;
            if (data[j][0].equals("308")) RetAssuanceChomage = AssuranceSocialeB * Double.parseDouble(data[j][2]) / 100;
            if (data[j][0].equals("406")) MIP = AssuranceSocialeB * Double.parseDouble(data[j][2]) / 100;
            if (data[j][0].equals("460")) PCR_MIP = AssuranceSocialeB * Double.parseDouble(data[j][2]) / 100;
            if (data[j][0].equals("4AT")) TiersPayant = Double.parseDouble(data[j][1]);
            /*
                448 - RET. PRET ACHAT LOGEMENT
                446 - RET. PRET CONSTRUCTION
                450 - RET.ASS.VIE/PRET CONSTR.OSL
                470 - RETENUE  PRET AIDE AU LOGEMENT
                486 - RET.ASS.VIE PRET ACH.LOG.NEUF
                430 - RET. PRET SOCIAL  O S L
                412 - RET. PRET C.N.E.P
                410 - RET. PRET POUR SINISTRES DU 21/05/03
                4BR - SOLDE.PRETS.SOC.PERSO EX-BRC
                4BE - RET. PRET ABC BANQUE AU PROFIT PERS T.R.C
                408 - RET. ACHAT VEHICULE
             */
            if (data[j][0].equals("448")) RetPret += Double.parseDouble(data[j][1]);
            if (data[j][0].equals("446")) RetPret += Double.parseDouble(data[j][1]);
            if (data[j][0].equals("486")) RetPret += Double.parseDouble(data[j][1]);
            if (data[j][0].equals("430")) RetPret += Double.parseDouble(data[j][1]);
            if (data[j][0].equals("412")) RetPret += Double.parseDouble(data[j][1]);
            if (data[j][0].equals("4BR")) RetPret += Double.parseDouble(data[j][1]);
            if (data[j][0].equals("4BE")) RetPret += Double.parseDouble(data[j][1]);
            if (data[j][0].equals("408")) RetPret += Double.parseDouble(data[j][1]);
            j++;
        }

        GainsImpo = SalaireBase + IndemniteInterim + Revalorisation + IFA + ITP + IndNuisance + IZIN
                + PrimeTransport + PrimePanier + IndNourriture + RAP_NOUR_IFA_TRANS_PAN + RAP_NOUR_IFA_TRANS_PAN
                + IZCV + IAG;
        RetenuesNonImposable = AssuranceSociale + Retraite + RetAssuanceChomage + RetraiteAnticipee + PCR_MIP;

        IRGRapB = IndemniteInterim + IZIN + PrimePanier + IndNourriture + RAP_NOUR_IFA_TRANS_PAN
                + RAP_NOUR_IFA_TRANS_PAN;

        IRGB = GainsImpo - IZCV - IRGRapB - RetenuesNonImposable;

        IRG = getIRG(IRGB);
        int nbrMois = 1;
        double temp = IRGRapB + (IRGB / nbrMois);


        IRGRap = (getIRG(temp) - IRG) * nbrMois; // div par mois de rap

        RetenuesImposable = MIP + RetPret + IRG + IRGRap + TiersPayant;


        double Net = (GainsImpo + GainsNonImpo) - (RetenuesImposable + RetenuesNonImposable);
        double NetPret = Net + RetPret;
        double NetPayer = NetPret * 12;

        System.out.println("SalaireBase : " + SalaireBase);
        System.out.println("PrimePanier : " + PrimePanier);
        System.out.println("IndNourriture : " + IndNourriture);
        System.out.println("AssuranceSocialeB : " + AssuranceSocialeB);
        System.out.println("AssuranceSociale : " + AssuranceSociale);
        System.out.println("IAG : " + IAG);
        System.out.println("Retraite : " + Retraite);
        System.out.println("RetAssuanceChomage  : " + RetAssuanceChomage);
        System.out.println("RetraiteAnticipee  : " + RetraiteAnticipee);
        System.out.println("MIP : " + MIP);
        System.out.println("PCR_MIP : " + PCR_MIP);
        System.out.println("IRGB : " + IRGB);
        System.out.println("IRG : " + IRG);
        System.out.println("IRGRapB : " + IRGRapB);
        System.out.println("temp : " + temp);
        System.out.println("IRGRap : " + IRGRap);
        System.out.println("TiersPayant : " + TiersPayant);
        System.out.println("GainsImpo : " + GainsImpo);
        System.out.println("GainsNonImpo : " + GainsNonImpo);
        System.out.println("RetenuesImposable : " + RetenuesImposable);
        System.out.println("RetenuesNonImposable : " + RetenuesNonImposable);
        System.out.println("RetPret : " + RetPret);
        System.out.println("Net : " + Net);
        System.out.println("NetPret : " + NetPret);
        System.out.println("NetPayer : " + NetPayer);

        double[] res = new double[8];
        res[0] = (NetPayer);
        res[1] = ((GainsImpo + GainsNonImpo) * 12);
        res[2] = ((IRGB + IRGRapB) * 12);
        res[3] = ((IRG + IRGRap) * 12);
        res[4] = ((RetenuesNonImposable - PCR_MIP) * 12);
        res[5] = ((PCR_MIP + MIP) * 12);
        res[6] = ((RetPrimePanier + TiersPayant) * 12) ;
        return res;
    }

}
