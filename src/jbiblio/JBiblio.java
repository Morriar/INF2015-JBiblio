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

import jbiblio.model.Book;
import jbiblio.model.Database;
import jbiblio.model.Suscriber;

public class JBiblio {

    public static void main(String[] args) {
        try {
            Database database = new Database("json/books.json", "json/suscribers.json", "json/borrowers.json");
            listSuscribers(database);
        } catch(Exception e) {
            System.err.println(e.getMessage());
            System.err.println(e.getCause());
        }
    }

    public static void listSuscribers(Database database) {
        System.out.println("List of suscribers:");
        for (Suscriber suscriber: database.getSuscribers()) {
            if(!suscriber.isBorrowing()) {
                System.out.println(" * " + suscriber);
                System.out.println("\tis borrowing:");
                for(Book book: suscriber.getBorrowedBooks()) {
                    System.out.println("\t - " + book + " since " + database.borrowing.get(book));
                }
            }
        }
    }
}
