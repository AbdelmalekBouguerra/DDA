/*
 * Author : Abdelmalek BOUGUERRA
 * Author Email : abdelmalekbouguerra2000@gmail.com
 * Created : Oct 2021
 * description : All calculation related to RE.
 *
 * (c) Copyright by Abdelmalek BOUGUERRA.
 */

package Classes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;

import static DAO.DB.*;

public class RE {

    public static String[][] readIrgCsv(String file) {
        // loading the csv file

        String line = "";
        String[][] value = new String[52900][];
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            int i = 0;
            while ((line = br.readLine()) != null) {
                value[i] = line.split(",");
                i++;
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not founded");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("I/O error");
            e.printStackTrace();
        }
        return value;
    }

    public static String calIRG(String file, double IRGB) {
        String[][] IRGCsv = readIrgCsv(file);
        // cast double to int to delete any decimal numbers
        int num = (((int) IRGB / 10) * 10);

        for (int i = 0; i < IRGCsv.length; i++) {
            if (IRGCsv[i] != null) {
                for (int j = 0; j < IRGCsv[i].length; j++)
                    if (IRGCsv[i][0].equals(String.valueOf(num)))
                        return IRGCsv[i][1];
            }
        }
        return "0.0";
    }

    // Obtenez d'abord toutes les données de l'année
    public static String[][] getRUB(String Matricule, String month, String year) {

        // en déclarant la matrice, ils contiendront toutes les informations du RUB.
        String[][] data = new String[50][];

        try {
            // Récupération des données
            String date = month + "/" + year;
            String query = "SELECT * FROM rub_" + year + " WHERE matricule = ? AND mois = ? AND annee = ? ;";
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setString(1, Matricule);
            pStatement.setString(2, month);
            pStatement.setString(3, year);
            ResultSet resultSet = pStatement.executeQuery();
            // reading from DB.
            int i = 0;
            while (resultSet.next()) {
                String[] dataRow = new String[7];
                dataRow[0] = resultSet.getString("NumRub");
                dataRow[1] = String.valueOf(resultSet.getBigDecimal("MontantMois"));
                dataRow[2] = String.valueOf(resultSet.getBigDecimal("Taux"));
                dataRow[3] = String.valueOf(resultSet.getBigDecimal("Base"));
                dataRow[4] = (String.valueOf(resultSet.getString("datedeb")).equals("null")) ? "notRappel" : "Rappel";
                dataRow[5] = resultSet.getString("datedeb");
                dataRow[6] = resultSet.getString("datefin");
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
            String date = month + "/" + year;
            String query = "SELECT * FROM pers_" + year + " WHERE matricule = ? AND dateexpl = '" + date + "'";
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

    // Calculer RE à l'aide des dernières fonctions
    public static double[] getRE(String MAT, String year, String month) throws IOException {
        String[][] data = RE.getRUB(MAT, month, year);
        if (data[0] == null) {
            throw new IOException("L'utilsateur " + MAT + " n'existe pas dans le RUB " + month + " " + year);
        }
        System.out.println("Printing data =======================");
        for (String[] datum : data) {
            if (datum != null) {
                for (String s : datum) {
                    System.out.println(s);
                }
                System.out.println("============================");
            }

        }

        double SalaireBase,
                SalaireUnique = 0,
                PrimePanier = 0,
                PrimeTransportRub = 0,
                PrimeTransport = 0,
                IndNuisanceRub = 0,
                IndNuisance = 0,
                IndNourriture = 0,
                Heures_Supp_ForfaitRub = 0,
                Heures_Supp_Forfait = 0,
                INDEMN_FRAIS_VOYAGE = 0,
                IndemniteInterim = 0,
                Revalorisation = 0,
                IFA = 0,
                IFARub = 0,
                Abs = 0,
                IAG = 0,
                IZCV = 0,
                IZCV_Resident = 0,
                RAP_NOUR_IFA_TRANS_PAN = 0,
                RAP_NUIS_ITP_SB_IAG = 0,
                IZIN = 0,
                ITPRub = 0,
                ITP = 0,
                AssuranceSocialeB = 0,
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
                IRG = 0,
                IRGRapB,
                TiersPayant = 0,
                IRGRap,
                RetPrimePanier = 0,
                PanierPret = 0,
                RetenuesImposable,
                RetenuesNonImposable;
        int nbrMois = 1;

        // get IAG and salaire de base
        double[] sb_iag = getSB_IAG(MAT, month, year);
        SalaireBase = sb_iag[0];
        IAG = sb_iag[1] / 100;

        int j = 0;
        while (data[j] != null) {
            // Prime de transport
            if (data[j][0].equals("136")) PrimeTransportRub = (Double.parseDouble(data[j][1]));
            // Salaire Unique
            if (data[j][0].equals("200")) SalaireUnique = (Double.parseDouble(data[j][1]));
            // INDEMNITÉ. NUISANCES
            if (data[j][0].equals("116")) IndNuisanceRub = (Double.parseDouble(data[j][1]));
            // Heures_Supp_Forfait
            if (data[j][0].equals("133")) Heures_Supp_ForfaitRub = (Double.parseDouble(data[j][1]));

            // calculate prime panier and ind.Nourriture
            if (data[j][0].equals("143")) PrimePanier = (18.0 * Double.parseDouble(data[j][2]));
            if (data[j][0].equals("419")) RetPrimePanier = Double.parseDouble(data[j][1]);
            if (data[j][0].equals("147")) IndNourriture = (18.0 * Double.parseDouble(data[j][2]));

            if (data[j][0].equals("300") && data[j][4].equals("notRappel")) {

                // calculate indemnités fixes ===========================================================
                IFA = calInd(IFARub, Abs);
                ITP = calInd(ITPRub, Abs);
                PrimeTransport = calInd(PrimeTransportRub, Abs);
                IndNuisance = calInd(IndNuisanceRub, Abs);
                Heures_Supp_Forfait = calInd(Heures_Supp_ForfaitRub, Abs);
                // =========================================================================

                AssuranceSocialeB = SalaireBase + IndemniteInterim + Revalorisation + ITP + IndNuisance
                        + Heures_Supp_Forfait + IZIN + RAP_NUIS_ITP_SB_IAG + IZCV_Resident + IAG;

                AssuranceSociale = AssuranceSocialeB * Double.parseDouble(data[j][2]) / 100;
            }

            if (data[j][0].equals("302")) Retraite = AssuranceSocialeB * Double.parseDouble(data[j][2]) / 100;
            if (data[j][0].equals("304")) RetraiteAnticipee = AssuranceSocialeB * Double.parseDouble(data[j][2]) / 100;
            if (data[j][0].equals("308")) RetAssuanceChomage = AssuranceSocialeB * Double.parseDouble(data[j][2]) / 100;
            if (data[j][0].equals("406")) MIP = AssuranceSocialeB * Double.parseDouble(data[j][2]) / 100;
            if (data[j][0].equals("460")) PCR_MIP = AssuranceSocialeB * Double.parseDouble(data[j][2]) / 100;
            if (data[j][0].equals("4AT")) TiersPayant = Double.parseDouble(data[j][1]);
            if (data[j][0].equals("134") && data[j][4].equals("notRappel")) IFARub = Double.parseDouble(data[j][1]);

            // ITP =================================================================================================
            if (data[j][0].equals("158") && data[j][4].equals("notRappel")) ITPRub = Double.parseDouble(data[j][1]);
            if (data[j][0].equals("188") && data[j][4].equals("notRappel")) ITPRub = Double.parseDouble(data[j][1]);
            if (data[j][0].equals("190") && data[j][4].equals("notRappel")) ITPRub = Double.parseDouble(data[j][1]);
            if (data[j][0].equals("192") && data[j][4].equals("notRappel")) ITPRub = Double.parseDouble(data[j][1]);
            if (data[j][0].equals("194") && data[j][4].equals("notRappel")) ITPRub = Double.parseDouble(data[j][1]);
            if (data[j][0].equals("196") && data[j][4].equals("notRappel")) ITPRub = Double.parseDouble(data[j][1]);
            if (data[j][0].equals("198") && data[j][4].equals("notRappel")) ITPRub = Double.parseDouble(data[j][1]);
            if (data[j][0].equals("154") && data[j][4].equals("notRappel")) ITPRub = Double.parseDouble(data[j][1]);
            // Num of absence ==========================================================

            if (data[j][0].equals("311")) Abs += Double.parseDouble(data[j][3]);
            if (data[j][0].equals("331")) Abs += Double.parseDouble(data[j][3]);

            // =========================================================================

            // I Z I N ==================================================================
            if (data[j][0].equals("159")) IZIN = (18.0 * Double.parseDouble(data[j][2]));

            /*
             IZCV =======================================================================
             IZCV (4x4) SR dans le fichier excel
             279    I Z C V  B
             281    I Z C V  C
             283    I Z C V  D
             285    I Z C V  E
             287	I Z C V  F
             289	I Z C V  G
             291	I Z C V  H
             293	I Z C V  J
             295	I Z C V  K
             297	I Z C V  L
             299	I Z C V  A
             */
            if (data[j][0].equals("279")) IZCV = (27.0 * Double.parseDouble(data[j][2]));
            if (data[j][0].equals("281")) IZCV = (27.0 * Double.parseDouble(data[j][2]));
            if (data[j][0].equals("283")) IZCV = (27.0 * Double.parseDouble(data[j][2]));
            if (data[j][0].equals("285")) IZCV = (27.0 * Double.parseDouble(data[j][2]));
            if (data[j][0].equals("287")) IZCV = (27.0 * Double.parseDouble(data[j][2]));
            if (data[j][0].equals("289")) IZCV = (27.0 * Double.parseDouble(data[j][2]));
            if (data[j][0].equals("291")) IZCV = (27.0 * Double.parseDouble(data[j][2]));
            if (data[j][0].equals("293")) IZCV = (27.0 * Double.parseDouble(data[j][2]));
            if (data[j][0].equals("295")) IZCV = (27.0 * Double.parseDouble(data[j][2]));
            if (data[j][0].equals("297")) IZCV = (27.0 * Double.parseDouble(data[j][2]));
            if (data[j][0].equals("299")) IZCV = (27.0 * Double.parseDouble(data[j][2]));

            // ==========================================================================
            /*
            IZCV (NSR) Resident ================================================================
            231 	I.Z.C.V. A  COTISABLE
            233	    I.Z.C.V. B  COTISABLE
            235	    I.Z.C.V. C  COTISABLE
            237	    I.Z.C.V. D  COTISABLE
            239	    I.Z.C.V. E  COTISABLE
            241	    I.Z.C.V. F  COTISABLE
            243	    I.Z.C.V. G  COTISABLE
            245	    I.Z.C.V. H  COTISABLE
             */
            if (data[j][0].equals("231")) IZCV_Resident = (30 * Double.parseDouble(data[j][2]));
            if (data[j][0].equals("233")) IZCV_Resident = (30 * Double.parseDouble(data[j][2]));
            if (data[j][0].equals("235")) IZCV_Resident = (30 * Double.parseDouble(data[j][2]));
            if (data[j][0].equals("237")) IZCV_Resident = (30 * Double.parseDouble(data[j][2]));
            if (data[j][0].equals("239")) IZCV_Resident = (30 * Double.parseDouble(data[j][2]));
            if (data[j][0].equals("241")) IZCV_Resident = (30 * Double.parseDouble(data[j][2]));
            if (data[j][0].equals("243")) IZCV_Resident = (30 * Double.parseDouble(data[j][2]));
            if (data[j][0].equals("245")) IZCV_Resident = (30 * Double.parseDouble(data[j][2]));
            // =================================================================================
            if (data[j][0].equals("120")) IndemniteInterim = Double.parseDouble(data[j][1]);
            if (data[j][0].equals("256")) INDEMN_FRAIS_VOYAGE = Double.parseDouble(data[j][1]);


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
            if (data[j][0].equals("448") && data[j][4].equals("notRappel")) RetPret += Double.parseDouble(data[j][1]);
            if (data[j][0].equals("446") && data[j][4].equals("notRappel")) RetPret += Double.parseDouble(data[j][1]);
            if (data[j][0].equals("486") && data[j][4].equals("notRappel")) RetPret += Double.parseDouble(data[j][1]);
            if (data[j][0].equals("430") && data[j][4].equals("notRappel")) RetPret += Double.parseDouble(data[j][1]);
            if (data[j][0].equals("419") && data[j][4].equals("Rappel"))
                PanierPret = (18 * Double.parseDouble(data[j][2]));
            if (data[j][0].equals("412") && data[j][4].equals("notRappel")) RetPret += Double.parseDouble(data[j][1]);
            if (data[j][0].equals("4BR") && data[j][4].equals("notRappel")) RetPret += Double.parseDouble(data[j][1]);
            if (data[j][0].equals("4BE") && data[j][4].equals("notRappel")) RetPret += Double.parseDouble(data[j][1]);
            if (data[j][0].equals("408") && data[j][4].equals("notRappel")) RetPret += Double.parseDouble(data[j][1]);

            // calculate number of months of IRG rappel. (464 & Rappel)
            if (data[j][0].equals("464") && data[j][4].equals("Rappel")) {
                /* first we get number of month data[j][5] & data[j][6]
                    it contains data using format ddMMyyyy so for example
                    data[j][5] = '01012022' and data[j][6] = 28022022,
                    so we take MM using substring from second char to the forth
                    '01' and '02' then we cast it to int to calculate
                    02 - 01 + 1 = 2 (number of months)
                 */
                nbrMois = Integer.parseInt(data[j][6].substring(2, 4)) -
                        Integer.parseInt(data[j][5].substring(2, 4)) + 1;
            }

            j++;

        }


        GainsImpo = SalaireBase + IndemniteInterim + Revalorisation + IFA + ITP + IndNuisance + IZIN
                + PrimeTransport + PrimePanier + IndNourriture + Heures_Supp_Forfait + RAP_NOUR_IFA_TRANS_PAN + RAP_NOUR_IFA_TRANS_PAN
                + IZCV_Resident + IAG;

        GainsNonImpo = SalaireUnique + INDEMN_FRAIS_VOYAGE + IZCV;

        RetenuesNonImposable = AssuranceSociale + Retraite + RetAssuanceChomage + RetraiteAnticipee + PCR_MIP;

        IRGRapB = IndemniteInterim + IZIN + PrimePanier + IndNourriture + RAP_NOUR_IFA_TRANS_PAN
                + RAP_NOUR_IFA_TRANS_PAN;

        IRGB = (GainsImpo - IZCV_Resident) - IRGRapB - RetenuesNonImposable;
        String file = "C:\\template\\IRG.csv";

        IRG = Double.parseDouble(calIRG(file, IRGB));

        int temp = (int) ((IRGB / 10) + (IRGRapB / nbrMois / 10));
        temp = temp * 10;
        System.out.println("temp " + temp);
        System.out.println("nbr month " + nbrMois);
        IRGRap = (Double.parseDouble(Objects.requireNonNull(calIRG(file, temp))) - IRG) * nbrMois; // div par mois de rap

        RetenuesImposable = MIP + RetPret + PanierPret + IRG + IRGRap + TiersPayant;


        double Net = (GainsImpo + GainsNonImpo) - (RetenuesImposable + RetenuesNonImposable);
        double NetPret = Net + RetPret;
        double NetPayer = NetPret * 12;

        System.out.println("SalaireBase : " + SalaireBase);
        System.out.println("PrimePanier : " + PrimePanier);
        System.out.println("IndNourriture : " + IndNourriture);
        System.out.println("IndemniteInterim : " + IndemniteInterim);
        System.out.println("AssuranceSocialeB : " + AssuranceSocialeB);
        System.out.println("AssuranceSociale : " + AssuranceSociale);
        System.out.println("SalaireUnique : " + SalaireUnique);
        System.out.println("IZCV : " + IZCV);
        System.out.println("IZCV_Resident : " + IZCV_Resident);
        System.out.println("IZIN : " + IZIN);
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
        System.out.println("Nombre d'absence : " + Abs);
        System.out.println("IFARub : " + IFARub);
        System.out.println("IFA : " + IFA);
        System.out.println("GainsImpo : " + GainsImpo);
        System.out.println("GainsNonImpo : " + GainsNonImpo);
        System.out.println("RetenuesImposable : " + RetenuesImposable);
        System.out.println("RetenuesNonImposable : " + RetenuesNonImposable);
        System.out.println("PanierPret : " + PanierPret);
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
        res[6] = ((RetPrimePanier + TiersPayant) * 12);
        return res;
    }

    // calculer la valeur des indemnités de tout le mois sans absance
    public static double calInd(double Rub, double abs) {
        if (abs == 0) return Rub;
        return ((int) (Rub / (30 - abs))) * 30;
    }

}
