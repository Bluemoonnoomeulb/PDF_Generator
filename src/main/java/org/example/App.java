package org.example;

import java.io.IOException;

public class App
{
    public static void main( String[] args ) throws IOException {
        PeopleGenerator p = new PeopleGenerator();
        p.modifyURI(1);
        p.printPeople(p.returnJson());
    }
}
