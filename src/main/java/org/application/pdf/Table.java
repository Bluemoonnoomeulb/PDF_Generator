package org.application.pdf;

import java.io.IOException;

public class Table {

    private final static int coordinateX = 5;
    public static int coordinateY = (int)DocumentPDF.page.getCropBox().getHeight() - 10;
    private final static String[] headers = new String[] {"Фамилия", "Имя", "Отчество", "Пол", "Возраст",
            "Дата рождения", "Место рождения", "Индекс", "Страна", "Область", "Город", "Улица",
            "Дом", "Квартира"};
    private static int rowCount;
    private final static int colCount = 14;
    private final static int cellHeight = 8;

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

    private static class Row {
        private final static int[] widthArray = new int[] {53, 50, 55, 20, 27, 50, 50, 28, 33, 67, 50, 67, 20, 30};

        private static void createRow(String[] peopleInfo) throws IOException {
            int coordinateX1 = coordinateX;

            for(int i = 0; i < colCount; i++) {
                DocumentPDF.contentStream.addRect(coordinateX1, coordinateY, widthArray[i], -cellHeight);
                DocumentPDF.contentStream.beginText();
                DocumentPDF.contentStream.newLineAtOffset(coordinateX1 + 1, coordinateY - cellHeight + 2);
                DocumentPDF.contentStream.setFont(DocumentPDF.pdfFont, DocumentPDF.fontSize);
                DocumentPDF.contentStream.showText(cutText(peopleInfo[i], widthArray[i]));
                DocumentPDF.contentStream.endText();

                coordinateX1 += widthArray[i];
            }
            coordinateY -= cellHeight;
        }

        private static String cutText(String currentString, int cellWidth) throws IOException {
            float lineWidth = (DocumentPDF.pdfFont.getStringWidth(currentString) / 1000.f) * DocumentPDF.fontSize;

            while (lineWidth > cellWidth) {
                currentString = currentString.substring(0, currentString.length()-1);
                lineWidth = (DocumentPDF.pdfFont.getStringWidth(currentString) / 1000.f) * DocumentPDF.fontSize;
            }

            return currentString;
        }

    }
}
