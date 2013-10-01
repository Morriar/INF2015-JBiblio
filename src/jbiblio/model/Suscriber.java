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
public class Suscriber {
    public Integer id;
    public String firstname;
    public String lastname;

    public Suscriber(Integer id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return "(" + id + ") " + firstname + " " + lastname;
    }

    public static Suscriber fromJSONObject(JSONObject suscriber) {
        Integer id = suscriber.getInt("id");
        String firstname = suscriber.getString("firstname");
        String lastname = suscriber.getString("lastname");
        return new Suscriber(id, firstname, lastname);
    }
}
