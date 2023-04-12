package org.application.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.*;
import org.application.PeopleInfoGenerator;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class DocumentPDF {

    private PDDocument document;
    protected static PDPage page;
    public static PDFont pdfFont;
    public static PDPageContentStream contentStream;

    public void setFont() throws IOException {
        pdfFont = PDType0Font.load(document, new File("D:\\PDF generator\\PDF-generator\\ArialMT.ttf"));
    }

    public void createDoc() throws IOException {
        document = new PDDocument();
        page = new PDPage();
        document.addPage(page);
        startStream();
        fillTable();
    }

    public void startStream() {
        try {
            contentStream = new PDPageContentStream(document, page);
            contentStream.setStrokingColor(Color.DARK_GRAY);
            contentStream.setLineWidth(0.5f);
            setFont();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void stopStream() throws IOException {
        contentStream.stroke();
        contentStream.close();
    }

    public void fillTable() throws IOException {
        Table tablePDF = new Table();
        tablePDF.createTable(PeopleInfoGenerator.assembleAllPeopleInfo());
    }

    public void closeDoc() throws IOException {
        stopStream();
        saveDoc();
        document.close();
    }

    public void saveDoc() throws IOException {
        document.save("D:\\PDF generator\\mypdf.pdf");
    }


}
