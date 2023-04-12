package org.application;

import java.io.IOException;
import java.util.Scanner;

import org.application.api.WorkingAPI;
import org.application.pdf.*;
import org.application.json.*;

public class App {

    public static void main( String[] args ) throws IOException {
        Scanner in = new Scanner(System.in);
        int peopleNumber = 0;

        while(peopleNumber <= 0 || peopleNumber > 30) {
            System.out.print("Введите количество людей (от 1 до 30), информацию для которых нужно сгенерировать: ");
            peopleNumber = in.nextInt();
        }

        // Передаем параметр (количество данных для генерации) для запроса к API.
        WorkingAPI.setQuantity(peopleNumber);
        // Получаем JSON ответ от API в формате String.
        String json = WorkingAPI.returnJSONResponse(WorkingAPI.getMainURI());
        // Передаем JSON на обработку.
        ProcessorJSON.SplitData(json);

        DocumentPDF doc = new DocumentPDF();
        doc.createDoc();
        doc.closeDoc();
    }
}
