package org.example;

import java.io.IOException;

public class App {

    public static void main( String[] args ) throws IOException {
       /* WorkingAPI.setQuantity(10);
        ProcessorJSON p = new ProcessorJSON();
        PeopleGenerator.printPeople(p.SplitData(p.modifyStructure(WorkingAPI.returnJSONResponse(WorkingAPI.getMainURI()))));
        */
        RecordPDF.createFile(30);
    }
}
