/*
 * Copyright 2013 Alexandre Terrasa <alexandre@moz-code.org>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jbiblio;

import java.io.FileNotFoundException;
import java.io.IOException;
import jbiblio.model.Book;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import utils.FileReader;

/**
 *
 */
public class JBiblio {
    static JSONArray books;
    static JSONArray suscribers;
    static JSONArray borrowers;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        loadDataBase();

        // list all books in database
        listBooks();

        // list all suscribers in database
        listSuscribers();

        // list all borrowers in database
        listBorrowers();
    }

    public static void loadDataBase() throws FileNotFoundException, IOException {
        // load files
        String booksStr = FileReader.loadFileIntoString("json/books.json", "utf-8");
        String suscribersStr = FileReader.loadFileIntoString("json/suscribers.json", "utf-8");
        String borrowersStr = FileReader.loadFileIntoString("json/borrowers.json", "utf-8");
        // parse json
        JBiblio.books = JSONArray.fromObject(booksStr);
        JBiblio.suscribers = JSONArray.fromObject(suscribersStr);
        JBiblio.borrowers = JSONArray.fromObject(borrowersStr);


    }

    public static void listBooks() {
        System.out.println("List of books:");
        for(int i = 0; i < books.size(); i++) {
            JSONObject bookJson = books.getJSONObject(i);
            Book book = Book.fromJSONObject(bookJson);

            System.out.println(" * " + book);
        }
    }

    public static void listSuscribers() {
        System.out.println("List of suscribers:");
        for(int i = 0; i < suscribers.size(); i++) {
            JSONObject suscriber = suscribers.getJSONObject(i);
            System.out.println(" * " + suscriber.getString("firstname") +  " " + suscriber.getString("lastname"));
        }
    }

    public static void listBorrowers() {
        System.out.println("List of borrowers:");
        for(int i = 0; i < borrowers.size(); i++) {
            JSONObject borrower = borrowers.getJSONObject(i);
            System.out.println(" * " + borrower.getString("date"));
        }
    }

}
