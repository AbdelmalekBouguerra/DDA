/*
 * Author : Abdelmalek BOUGUERRA
 * Author Email : abdelmalekbouguerra2000@gmail.com
 * Created : Oct 2021
 * description : compress multiple files in one zip file.
 *
 * (c) Copyright by Abdelmalek BOUGUERRA.
 */

package Classes;

import java.io.*;
import java.nio.file.*;
import java.util.zip.*;

// Compresser des fichiers au format ZIP.

public class ZipFiles {
    public static void zipFiles(String[] filePaths,String zipFileName) {
        try {
            FileOutputStream fos = new FileOutputStream(zipFileName);
            ZipOutputStream zos = new ZipOutputStream(fos);

            for (String aFile : filePaths) {
                zos.putNextEntry(new ZipEntry(new File(aFile).getName()));

                byte[] bytes = Files.readAllBytes(Paths.get(aFile));
                zos.write(bytes, 0, bytes.length);
                zos.closeEntry();
            }

            zos.close();
            System.out.println("Zipped with success");

        } catch (FileNotFoundException ex) {
            System.err.println("A file does not exist: " + ex);
        } catch (IOException ex) {
            System.err.println("I/O error: " + ex);
        }
    }
}