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
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import utils.FileReader;

/**
 * A database unify datas from the json file
 */
public class Database {
    private Map<Integer, Book> books;
    private Map<Integer, Suscriber> suscribers;
    public Map<Book, String> borrowing;

    public Database() throws FileNotFoundException, IOException {
        this.books = loadBooks();
        this.suscribers = loadSuscribers();
        this.borrowing = loadBorrowers();
    }

    private Map<Integer, Book> loadBooks() throws FileNotFoundException, IOException {
        String booksStr = FileReader.loadFileIntoString("json/books.json", "utf-8");
        JSONArray booksJson = JSONArray.fromObject(booksStr);

        Map<Integer, Book> books = new HashMap<Integer, Book>();
        for (int i = 0; i < booksJson.size(); i++) {
            JSONObject bookJson = booksJson.getJSONObject(i);
            Book book = Book.fromJSONObject(bookJson);
            books.put(i, book);
        }

        return books;
    }

    private Map<Integer, Suscriber> loadSuscribers() throws FileNotFoundException, IOException {
        String suscribersStr = FileReader.loadFileIntoString("json/suscribers.json", "utf-8");
        JSONArray suscribersJson = JSONArray.fromObject(suscribersStr);

        Map<Integer, Suscriber> suscribers = new HashMap<Integer, Suscriber>();
        for (int i = 0; i < suscribersJson.size(); i++) {
            JSONObject suscriberJson = suscribersJson.getJSONObject(i);
            Suscriber suscriber = Suscriber.fromJSONObject(suscriberJson);
            suscribers.put(suscriber.id, suscriber);
        }

        return suscribers;
    }

    private Map<Book, String> loadBorrowers() throws FileNotFoundException, IOException {
        String borrowersStr = FileReader.loadFileIntoString("json/borrowers.json", "utf-8");
        JSONArray borrowers = JSONArray.fromObject(borrowersStr);

        Map<Book, String> borrowing = new HashMap<Book, String>();
        for (int i = 0; i < borrowers.size(); i++) {
            JSONObject borrower = borrowers.getJSONObject(i);
            Integer suscriberId = borrower.getInt("suscriber_id");
            Suscriber suscriber = suscribers.get(suscriberId);
            String date = borrower.getString("date");
            JSONArray bookJson = borrower.getJSONArray("book_ids");
            for (int j = 0; j < bookJson.size(); j++) {
                Integer bookId = bookJson.getInt(j);
                Book book = books.get(bookId);
                suscriber.borrowing.add(book);
                borrowing.put(book, date);
            }
        }
        return borrowing;
    }

    // return null if there is no book with this id
    public Book getBookById(Integer id) {
        if(books.containsKey(id)) {
            return books.get(id);
        }
        return null;
    }

    // return null if there is no suscriber with this id
    public Suscriber getSuscriberById(Integer id) {
        if(suscribers.containsKey(id)) {
            return suscribers.get(id);
        }
        return null;
    }

    public Collection<Book> getBooks() {
        return books.values();
    }

    public Collection<Suscriber> getSuscribers() {
        return suscribers.values();
    }

}
