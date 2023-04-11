package org.application.pdf;

import org.apache.pdfbox.pdmodel.PDPageContentStream;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.interactive.annotation.layout.PlainTextFormatter;
import org.application.*;

public class Table {

    public static int coordinateX = 5;
    public static int coordinateY = (int)DocumentPDF.page.getCropBox().getHeight() - 10;
    public final static String[] headers = new String[] {"Фамилия", "Имя", "Отчество", "Пол", "Возраст",
            "Дата рождения", "Место рождения", "Индекс", "Страна", "Область", "Город", "Улица",
            "Дом", "Квартира"};
    public static int rowCount;
    public static int colCount = 14;
    public static int cellHeight = 8;

    public void setRowCount(int rowCount) { this.rowCount = rowCount; }

    public void createTable(int n, PDPageContentStream contentStream, String[][] res) throws IOException {
        setRowCount(n);
        for(int i = 1; i <= rowCount; i++) {
            if (i == 1) {
                Row.createRow(contentStream, headers);
            }
            Row.createRow(contentStream, res[i-1]);
        }
    }

    public static class Row {
        private final static int[] widthArray = new int[] {60, 50, 55, 25, 30, 50, 50, 35, 40, 50, 50, 60, 20, 30};

        public static void createRow(PDPageContentStream contentStream, String[] elements) throws IOException {
            int initX = coordinateX;

            for(int i = 1; i <= colCount; i++) {
                contentStream.addRect(initX, coordinateY, widthArray[i-1], -cellHeight);
                contentStream.beginText();
                contentStream.newLineAtOffset(initX + 1, coordinateY - cellHeight + 2);
                float text_width = (DocumentPDF.pdfFont.getStringWidth(elements[i-1]) / 1000.f) * 6;
                while (text_width > widthArray[i-1]) {
                    elements[i-1] = elements[i-1].substring(0, elements[i-1].length()-1);
                    text_width = (DocumentPDF.pdfFont.getStringWidth(elements[i-1]) / 1000.f) * 6;
                }
                contentStream.setFont(DocumentPDF.pdfFont, 6);
                contentStream.showText(elements[i-1]);
                contentStream.endText();

                initX += widthArray[i-1];
            }
            coordinateY -= cellHeight;
        }

    }
}
