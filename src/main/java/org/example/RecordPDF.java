package org.example;

import org.apache.pdfbox.contentstream.PDContentStream;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class RecordPDF {

    private PDDocument document;
    private PDPage page;
    private final int[] widthArray = new int[] {60, 50, 55, 25, 30, 50, 50, 35, 40, 50, 50, 60, 20, 30};
    private final String[] headers = new String[] {"Фамилия", "Имя", "Отчество", "Пол", "Возраст",
            "Дата рождения", "Место рождения", "Индекс", "Страна", "Область", "Город", "Улица",
            "Дом", "Квартира"};

    private PDFont pdfFont;

    public void setFont() throws IOException {
        pdfFont = PDType0Font.load(document, new File("D:\\PDF generator\\PDF-generator\\ArialMT.ttf"));
    }

    public void createDoc() {
        document = new PDDocument();
        page = new PDPage();
        document.addPage(page);
    }

    public PDPageContentStream startStream() throws IOException {
        PDPageContentStream stream = new PDPageContentStream(document, page);
        stream.setStrokingColor(Color.DARK_GRAY);
        stream.setLineWidth(0.5f);
        setFont();
        return stream;
    }

    public void stopStream(PDPageContentStream stream) throws IOException {
        stream.stroke();
        stream.close();
    }

    public void createTable(int n) throws IOException {
        int initX = 5;
        int initY = (int)page.getCropBox().getHeight() - 10;

        int cellHeight = 8;

        int rowCount = n+1;
        int colCount = 14;


        for(int i = 1; i <= rowCount; i++) {
            for(int j = 1; j <= colCount; j++) {
            }
            initX = 5;
            initY -= cellHeight;
        }


        document.save("D:\\PDF generator\\mypdf.pdf");
        document.close();
        System.out.println("PDF created");
    }
}
