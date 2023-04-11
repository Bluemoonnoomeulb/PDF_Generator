package org.application;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.application.pdf.*;
import org.application.api.*;
import org.application.json.*;

public class App {

    public static void main( String[] args ) throws IOException {
        WorkingAPI.setQuantity(30);
        ProcessorJSON p = new ProcessorJSON();



        DocumentPDF doc = new DocumentPDF();
        doc.createDoc();
        PDPageContentStream ph = doc.startStream();
        Table t = new Table();
        t.createTable(30, ph, PeopleInfoGenerator.printPeople(p.SplitData(p.modifyStructure(WorkingAPI.returnJSONResponse(WorkingAPI.getMainURI())))));
        doc.stopStream(ph);
        doc.closeDoc();
    }
}
