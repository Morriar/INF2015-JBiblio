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

import net.sf.json.JSONObject;

/**
 */
public class Book {
    public Integer id;
    public String title;
    public String author;

    public Book(Integer id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    @Override
    public String toString() {
        return "(" + id + ") " + title + " - " + author;
    }

    public static Book fromJSONObject(JSONObject book) {
        Integer id = book.getInt("id");
        String title = book.getString("title");
        String author = book.getString("author");
        return new Book(id, title, author);
    }
}
