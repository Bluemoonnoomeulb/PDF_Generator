package org.application.pdf;

import org.apache.pdfbox.pdmodel.PDPageContentStream;
import java.io.IOException;

public class Table {

    public static int coordinateX = 5;
    public static int coordinateY = (int)DocumentPDF.page.getCropBox().getHeight() - 10;
    private final static String[] headers = new String[] {"Фамилия", "Имя", "Отчество", "Пол", "Возраст",
            "Дата рождения", "Место рождения", "Индекс", "Страна", "Область", "Город", "Улица",
            "Дом", "Квартира"};
    public static int rowCount;
    public static int colCount = 14;
    public static int cellHeight = 8;

    public void setRowCount(int rowCount) { this.rowCount = rowCount; }

    public void createTable(String[][] peopleInfo) throws IOException {
        setRowCount(peopleInfo.length);
        for(int i = 0; i < rowCount; i++) {
            if (i == 0) {
                Row.createRow(headers);
            }
            Row.createRow(peopleInfo[i]);
        }
    }

    public static class Row {
        private final static int[] widthArray = new int[] {53, 50, 55, 20, 27, 50, 50, 28, 33, 67, 50, 67, 20, 30};

        public static void createRow(String[] elements) throws IOException {
            int initX = coordinateX;

            for(int i = 1; i <= colCount; i++) {
                DocumentPDF.contentStream.addRect(initX, coordinateY, widthArray[i-1], -cellHeight);
                DocumentPDF.contentStream.beginText();
                DocumentPDF.contentStream.newLineAtOffset(initX + 1, coordinateY - cellHeight + 2);

                float text_width = (DocumentPDF.pdfFont.getStringWidth(elements[i-1]) / 1000.f) * 6;
                while (text_width > widthArray[i-1]) {
                    elements[i-1] = elements[i-1].substring(0, elements[i-1].length()-1);
                    text_width = (DocumentPDF.pdfFont.getStringWidth(elements[i-1]) / 1000.f) * 6;
                }
                DocumentPDF.contentStream.setFont(DocumentPDF.pdfFont, 6);
                DocumentPDF.contentStream.showText(elements[i-1]);
                DocumentPDF.contentStream.endText();

                initX += widthArray[i-1];
            }
            coordinateY -= cellHeight;
        }

    }
}
