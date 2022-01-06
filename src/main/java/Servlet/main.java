package Servlet;


import DAO.RUBDAO;

import java.util.Arrays;


public class main {
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


    public static void main(String[] args) {
        RUBDAO rubdao = new RUBDAO();
        String[][] data = rubdao.getRUB("89260D", "01", "2021");

        System.out.println("==================== data table");
        for (int i = 0; i < data.length && data[i] != null; i++) {
            System.out.println(Arrays.toString(data[i]));
        }

        System.out.println("==================== sorting out alphabetic nums");
        System.out.println(Arrays.toString(getAlphanumeric(data)));


        System.out.println("==================== sorting data");
        String[][] newData =sortingRub(data);
        for (int i = 0; i < newData.length && newData[i] != null; i++) {
            System.out.println(Arrays.toString(newData[i]));
        }

    }
}
