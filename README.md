# PDF_generator
Программа запрашивает данные у API, форматирует их и выводит в виде таблицы в файл result.pdf. Все совпадения с реальными людьми - всего лишь совпадение :)
## App.java
Здесь находится входная точка в приложение (метод main). Ожидается ввод с клавиатуры числа n, где n - количество сгенерированных данных пользователей, 1 <= n <= 30.
Далее вызываются необходимые методы для реализации поставленной задачи:

        // Передаем параметр (количество данных для генерации) для запроса к API.
        WorkingAPI.setQuantity(peopleNumber);
        // Получаем JSON ответ от API в формате String.
        String json = WorkingAPI.returnJSONResponse(WorkingAPI.getMainURI());
        // Передаем JSON на обработку.
        ProcessorJSON.SplitData(json);

        // Создаем документ, заполняем и сохраняем.
        DocumentPDF doc = new DocumentPDF();
        doc.createDoc();
        doc.closeDoc();
       
## WorkingAPI.java
Smth
## ProcessorJSON.java
Smth
## PeopleInfoGenerator.java
Smth
## package pdf: DocumentPDF.java & Table.java
Smth
