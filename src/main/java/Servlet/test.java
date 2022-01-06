package Servlet;

import Classes.RE;
import DAO.RUBDAO;

import java.util.Arrays;


public class test {

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

    public static void main(String[] args) {
        RUBDAO rubdao = new RUBDAO();

//
//       String[][] data = RE.getRUB("00433E", "01", "2020");
//        for (int i = 0; i < data.length; i++) {
//            System.out.println(Arrays.toString(data[i]));
//        }
//


    }
}
