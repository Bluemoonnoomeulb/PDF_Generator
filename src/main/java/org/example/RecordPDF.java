package org.example;

import org.apache.pdfbox.contentstream.PDContentStream;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;

public class RecordPDF {

    public static void createFile(int n) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        int pageHeight = (int) page.getCropBox().getHeight();
        int pageWidth = (int) page.getCropBox().getWidth();

        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        contentStream.setStrokingColor(Color.DARK_GRAY);
        contentStream.setLineWidth(0.5f);

        int[] widthArray = new int[] {60, 50, 55, 25, 30, 50, 50, 35, 40, 50, 50, 60, 20, 30};
        String[] headers = new String[] {"Фамилия", "Имя", "Отчество", "Пол", "Возраст", "Дата рождения",
        "Место рождения", "Индекс", "Страна", "Область", "Город", "Улица", "Дом", "Квартира"};


        int initX = 5;
        int initY = pageHeight - 10;
        int cellHeight = 8;
        int cellWidth = 36;

        int rowCount = n+1;
        int colCount = 14;

        PDFont pdfFont = PDType0Font.load(document, new File("D:\\PDF generator\\PDF-generator\\ArialMT.ttf"));

        for(int i = 1; i <= rowCount; i++) {
            for(int j = 1; j <= colCount; j++) {
                contentStream.addRect(initX, initY, widthArray[j-1], -cellHeight);

                contentStream.beginText();
                contentStream.newLineAtOffset(initX+1, initY-cellHeight+2);
                contentStream.setFont(pdfFont, 6);
                contentStream.showText(headers[j-1]);
                contentStream.endText();

                initX += widthArray[j-1];
            }
            initX = 5;
            initY -= cellHeight;
        }

        contentStream.stroke();
        contentStream.close();

        document.save("D:\\PDF generator\\mypdf.pdf");
        document.close();
        System.out.println("PDF created");
    }
}
