package com.tt1.test;

import java.util.*;

/**
 * Simulación de una base de datos. Hay un mapa para controlar los ToDo y una lista con los emails.
 */
public class DBStub {

    private Map<String, ToDo> todos = new HashMap<>();
    private Set<String> emails = new HashSet<>();

    /**
     * Devuelve el Map que contiene los ToDo
     * @return Map de ToDo
     */
    public Map<String, ToDo> getTodos() {
        return todos;
    }
    /**
     * Devuelve la lista de emails
     * @return Lista de emails
     */
    public Set<String> getEmails() {
        return emails;
    }
}