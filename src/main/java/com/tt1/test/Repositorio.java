package com.tt1.test;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del repositorio usando DBStub.
 */
public class Repositorio implements InterfazRepToDo {

	private DBStub db;

	public Repositorio(DBStub db) {
		this.db = db;
	}

    /**
     * Método para guardar un ToDo en el DBStub
     * @param todo a guardar
     * @return Devuelve el propio ToDo
     * @throws IllegalArgumentException si ToDo es null o su nombre es null
     */
	@Override
	public ToDo guardarToDo(ToDo todo) {
		if (todo == null || todo.getNombre() == null) {
            throw new IllegalArgumentException("ToDo o nombre nulo");
        }
        db.getTodos().put(todo.getNombre(), todo);
        return todo;
	}

    /**
     * Método para encontrar el ToDo con el nombre proporcionado
     * @param nombre del ToDo que se quiere encontrar
     * @return el ToDo buscado o null si el nombre era null
     */
	@Override
	public ToDo encontrarPorNombre(String nombre) {
		if (nombre == null) return null;
        return db.getTodos().get(nombre);
	}

    /**
     * Devuelve todos los ToDos que se tienen almacenados
     * @return ArrayList con todos los ToDos
     */
	@Override
	public List<ToDo> encontrarToDos() {
		return new ArrayList<>(db.getTodos().values());
	}

    /**
     * Método para establecer un ToDo como completado
     * @param nombre del ToDo que se quiere marcar como completado
     * @return true siempre, para los tests, o false si ToDo es null
     */
	@Override
	public boolean marcarCompletado(String nombre) {
		ToDo t = db.getTodos().get(nombre);
        if (t == null) return false;
        t.setCompletado(true);
        db.getTodos().put(nombre, t);
        return true;
	}

    /**
     * Eliminar un ToDo almacenado por nombre
     * @param nombre del ToDo a eliminar
     * @return true si se ha eliminado correctamente, o false si no se elimina correctamente o no existia un Map que contuviera dicho ToDo con ese nombre
     */
	@Override
	public boolean eliminarNombre(String nombre) {
		return db.getTodos().remove(nombre) != null;
	}

    /**
     * Método para añadir email a la lista de emails
     * @param email que se desea guardar
     * @return true si se ha añadido correctamente, o false si email es null o si el email ya estaba presente en la lista.
     */
	@Override
	public boolean guardarEmail(String email) {
		if (email == null) return false;
        return db.getEmails().add(email);
	}

    /**
     * Método para obtener todos los emails presentes en la lista
     * @return ArrayList de emails presentes
     */
	@Override
	public List<String> listarEmails() {
		 return new ArrayList<>(db.getEmails());
	}

}