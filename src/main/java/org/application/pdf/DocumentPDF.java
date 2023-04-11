package org.application.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class DocumentPDF {

    private PDDocument document;
    protected static PDPage page;
    public static PDFont pdfFont;
    private final int[] widthArray = new int[] {60, 50, 55, 25, 30, 50, 50, 35, 40, 50, 50, 60, 20, 30};
    private final String[] headers = new String[] {"Фамилия", "Имя", "Отчество", "Пол", "Возраст",
            "Дата рождения", "Место рождения", "Индекс", "Страна", "Область", "Город", "Улица",
            "Дом", "Квартира"};

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

    public void closeDoc() throws IOException {
        saveDoc();
        document.close();
    }

    public void saveDoc() throws IOException {
        document.save("D:\\PDF generator\\mypdf.pdf");
    }


}
