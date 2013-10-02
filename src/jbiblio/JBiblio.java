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
import jbiblio.model.Database;
import jbiblio.model.Suscriber;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import utils.FileReader;

/**
 *
 */
public class JBiblio {

    static Database database;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        JBiblio.database = new Database();

        // list all suscribers in database
        listSuscribers();
    }

    public static void listSuscribers() {
        System.out.println("List of suscribers:");
        for (Suscriber suscriber: database.getSuscribers()) {
            if(!suscriber.borrowing.isEmpty()) {
                System.out.println(" * " + suscriber);
                System.out.println("\tis borrowing:");
                for(Book book: suscriber.borrowing) {
                    System.out.println("\t - " + book + " since " + database.borrowing.get(book));
                }
            }
        }
    }
}
