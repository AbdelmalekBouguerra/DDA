package Classes;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class XLS {

    public static void main(String[] args) throws IOException {
        File excelFile = new File("G:\\Mon Drive\\PFE\\Donne IAP\\PERS\\PERS_01_22.xlsx");
        FileInputStream fis = new FileInputStream(excelFile);

        // we create an XSSF Workbook object for our XLSX Excel File
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        // we get first sheet
        XSSFSheet sheet = workbook.getSheetAt(0);

        // we iterate on rows
        Iterator<Row> rowIt = sheet.iterator();
        LinkedHashMap<String, String> infoList = new LinkedHashMap<>();
        int i = 1;
        while (rowIt.hasNext()) {

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
                        infoList.put("matricule" + i, cell.toString());
                        break;
                    case 3 :
                        infoList.put("suitorg"+i, cell.toString());
                        break;
                    case 4:
                        infoList.put("loctrav"+i, cell.toString());
                        break;
                    case 6:
                        infoList.put("nom" + i, cell.toString());
                        break;
                    case 2:
                        infoList.put("str" + i, cell.toString());
                        break;
                    case 9:
                        infoList.put("codelieunais" + i, cell.toString());
                        break;
                    case 8:
                        infoList.put("datenais" + i, cell.toString());
                        break;
                    case 10:
                        infoList.put("sexe" + i, cell.toString());
                        break;
                    case 12:
                        infoList.put("daterec" + i, cell.toString() );
                        break;
                    case 16:
                        infoList.put("sf" + i, cell.toString());
                        break;
                    case 17:
                        infoList.put("scjt" + i, cell.toString());
                        break;
                    case 19:
                        infoList.put("nbrenfs10" + i, cell.toString());
                        break;
                    case 20:
                        infoList.put("nbrenfm10" + i, cell.toString());
                        break;
                    case 21:
                        infoList.put("gsang" + i, cell.toString());
                        break;
                    case 33:
                        infoList.put("groupe"+i, cell.toString());
                        break;
                    case 34:
                        infoList.put("echelle"+i, cell.toString());
                        break;
                    case 41:
                        infoList.put("salbase" + i, cell.toString());
                        break;
                    case 44:
                        infoList.put("fonction" + i, cell.toString());
                        break;
                    case 53:
                        infoList.put("cpaiem" + i, cell.toString());
                        break;
                    case 57:
                        infoList.put("nssagt" + i, cell.toString());
                        break;
                    case 58:
                        infoList.put("nssemp" + i, cell.toString());
                        break;
                    case 73:
                        infoList.put("adresse" + i, cell.toString());
                        break;
                    case 82 :
                        infoList.put("rib" + i, cell.toString());
                        break;
                    case 86:
                        infoList.put("iag" + i, cell.toString());
                        break;


                }
                cpt++;
            }

            i++;
        }
        workbook.close();
        fis.close();

        System.out.println(infoList);
    }

}
