/*
 * Author : Abdelmalek BOUGUERRA
 * Author Email : abdelmalekbouguerra2000@gmail.com
 * Created : Aug 2021
 * Library : Free Spire.Doc for Java. https://www.e-iceblue.com/Introduce/free-doc-for-java.html
 * description : Creat Barcodes with different parameters.
 *
 * (c) Copyright by Abdelmalek BOUGUERRA.
 */


package Classes;

import com.spire.barcode.BarCodeGenerator;
import com.spire.barcode.BarCodeType;
import com.spire.barcode.BarcodeSettings;
import com.spire.barcode.QRCodeECL;
import com.spire.doc.Document;
import com.spire.doc.documents.TextSelection;
import com.spire.doc.fields.DocPicture;
import com.spire.doc.fields.TextRange;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class BareCode {

    /*
    Création de code barre au format png fournissant le texte codé, le nom de l'image du code à barres,
     X et Y pour la taille, et recadrer pour redimensionner l'image
     */
    public void creatBareCode(String text, String png, float X, float Y, Boolean Crop) {
        //create an instance of BarcodeSetteings
        try {
            BarcodeSettings settings = new BarcodeSettings();
            //set barcode type
            /* for QR code use BarCodeType.QR_Code or
            for BareCode use : BarCodeType.CODE_128
             */
            settings.setType(BarCodeType.QR_Code);
            //set barcode data
            settings.setData(text);
            //don't show text on top
            settings.setShowText(false);
            //don't show text on bottom
            settings.setShowTextOnBottom(false);
            //set the border invisible
            settings.hasBorder(false);
            //set width of the barcode module
            settings.setX(X);
            settings.setY(Y);
            //set the error correction level
            settings.setQRCodeECL(QRCodeECL.M);
            //create BarCodeGenerator object based on settings
            BarCodeGenerator barCodeGenerator = new BarCodeGenerator(settings);
            //generate image data and store in BufferedImage instance
            BufferedImage bufferedImage = barCodeGenerator.generateImage();
            // corps the image
            if (Crop) {
                BufferedImage crop = new BareCode().crop(0.921, bufferedImage);
                //save to image
                ImageIO.write(crop, "png", new File(png));
            } else ImageIO.write(bufferedImage, "png", new File(png));
            System.out.println("Qr code for " + text + " completed !! and save at : " + png);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Appliquer le code à barres dans les documents fournissant le doc, code a barre et le position(text dans le doc)
    public void replaceTextWithImage(Document document, String image, String Text) {

        //Find the string in the document
        TextSelection[] selections = document.findAllString(Text, true, true);

        //Replace the string with an image
        int index;
        TextRange range;
        for (TextSelection obj : selections) {
            DocPicture pic = new DocPicture(document);
            pic.loadImage(image);
            range = obj.getAsOneRange();
            index = range.getOwnerParagraph().getChildObjects().indexOf(range);
            range.getOwnerParagraph().getChildObjects().insert(index, pic);
            range.getOwnerParagraph().getChildObjects().remove(range);
        }
    }

    // Suppression de l'image du code-barres
    public void delete(String filePath) {
        File file = new File(filePath);

        if (file.delete())
            System.out.println("File deleted successfully");
        else
            System.out.println("Failed to delete the file");
    }

    // Recadrer l'image si elle est grande
    public BufferedImage crop(double amount, BufferedImage originalImage) throws IOException {
        int height = originalImage.getHeight();
        int width = originalImage.getWidth();

        int targetWidth = (int) (width * amount);
        int targetHeight = (int) (height * amount);
        // Coordinates of the image's middle
        int xc = (width - targetWidth) / 2;
        int yc = (height - targetHeight) / 2;

        // Crop
        return originalImage.getSubimage(
                xc,
                yc,
                targetWidth, // widht
                targetHeight // height
        );
    }
}
