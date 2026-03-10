package com.tt1.test;

import java.time.LocalDate;
import java.util.Objects;
/**
 * Representa una tarea con nombre, descripción, fecha límite y si está completado o no.
 */
public class ToDo {
    private String nombre;
    private String descripcion;
    private LocalDate fechaLimite;
    private boolean completado;

    /**
     * Crea una nueva tarea
     * @param nombre de la tarea (no nulo)
     * @param descripcion puede ser null
     * @param fechaLimite fecha límite para completar la tarea (puede ser null)
     */
    public ToDo(String nombre, String descripcion, LocalDate fechaLimite) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaLimite = fechaLimite;
        this.completado = false;
    }

    /**
     * Devuelve el nombre de la tarea
     * @return String nombre
     */
    public String getNombre() { return this.nombre; }

    /**
     * Establece el nombre de la tarea
     * @param nombre de la tarea
     */
    public void setNombre(String nombre) { this.nombre = nombre; }

    /**
     * Devuelve la descripción de la tarea
     * @return String descripcion
     */
    public String getDescripcion() { return this.descripcion; }

    /**
     * Establece la descripción de la tarea
     * @param descripcion de la tarea
     */
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    /**
     * Devuelve la fecha límite de la tarea
     * @return LocalDate fechaLimite
     */
    public LocalDate getFechaLimite() { return this.fechaLimite; }

    /**
     * Establece la fecha límite de la tarea
     * @param fechaLimite de la tarea
     */
    public void setFechaLimite(LocalDate fechaLimite) { this.fechaLimite = fechaLimite; }

    /**
     * Devuelve si está completado (true) o no (false)
     * @return boolean completado
     */
    public boolean isCompletado() { return this.completado; }

    /**
     * Establece si la tarea está completada (true) o no (false)
     * @param completado para marcarla como completada o no
     */
    public void setCompletado(boolean completado) { this.completado = completado; }

    /**
     * Dos ToDo son iguales si su nombre es igual
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ToDo)) return false;
        ToDo toDo = (ToDo) o;
        return Objects.equals(this.nombre, toDo.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }

    @Override
    public String toString() {
        return "ToDo{" +
                "name='" + nombre + '\'' +
                ", deadline=" + fechaLimite +
                ", completed=" + completado +
                '}';
    }
}