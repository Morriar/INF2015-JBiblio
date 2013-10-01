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
package jbiblio.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jbiblio.JBiblio;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import utils.FileReader;

/**
 * A database unify datas from the json file
 */
public class Database {
    public List<Book> books;
    public List<Suscriber> suscribers;

    public Database() throws FileNotFoundException, IOException {
        this.books = loadBooks();
        this.suscribers = loadSuscribers();
    }

    private List<Book> loadBooks() throws FileNotFoundException, IOException {
        String booksStr = FileReader.loadFileIntoString("json/books.json", "utf-8");
        JSONArray booksJson = JSONArray.fromObject(booksStr);

        List<Book> books = new ArrayList<Book>();
        for (int i = 0; i < booksJson.size(); i++) {
            JSONObject bookJson = booksJson.getJSONObject(i);
            books.add(Book.fromJSONObject(bookJson));
        }

        return books;
    }

    private List<Suscriber> loadSuscribers() throws FileNotFoundException, IOException {
        String suscribersStr = FileReader.loadFileIntoString("json/suscribers.json", "utf-8");
        JSONArray suscribersJson = JSONArray.fromObject(suscribersStr);

        List<Suscriber> suscribers = new ArrayList<Suscriber>();
        for (int i = 0; i < suscribersJson.size(); i++) {
            JSONObject suscriberJson = suscribersJson.getJSONObject(i);
            suscribers.add(Suscriber.fromJSONObject(suscriberJson));
        }

        return suscribers;
    }

    // return null if there is no book with this id
    public Book getBookById(Integer id) {
        for(Book book: books) {
            if(book.id == id) {
                return book;
            }
        }
        return null;
    }

}
