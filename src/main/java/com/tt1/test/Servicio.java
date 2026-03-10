package com.tt1.test;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * Servicio principal que lleva a cabo las operaciones sobre ToDo u otros aspectos.
 * Usa la implementación de InterfazRepToDo (para almacenamiento) y un mailer stub.
 */
public class Servicio {

    private InterfazRepToDo repo;
    private InterfazMailerStub mailer;
    /**
     * Constructor de servicio.
     * @param repo repositorio de ToDos (la parte de persistencia)
     * @param mailer para enviar correos
     */
    public Servicio(InterfazRepToDo repo, InterfazMailerStub mailer) {
        this.repo = Objects.requireNonNull(repo);
        this.mailer = Objects.requireNonNull(mailer);
    }


    /**
     * Crea un ToDo y lo guarda en el repositorio.
     * @param nombre nombre de la tarea (no nulo ni vacío)
     * @param descripcion descripción de la tarea
     * @param fechaLimite fecha límite (no nula)
     * @throws IllegalArgumentException si nombre o fecha no son válidos
     */
    public void crearToDo(String nombre, String descripcion, LocalDate fechaLimite) {
    	if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("Nombre vacío");
        }

        if (fechaLimite == null) {
            throw new IllegalArgumentException("Fecha límite nula");
        }

        ToDo todo = new ToDo(nombre.trim(), descripcion, fechaLimite);
        repo.guardarToDo(todo);

        comprobarYMandarAlertas();
    }


    /**
     * Marca un ToDo como completado mediante su nombre.
     * @param nombre nombre (no vacío ni nulo)
     * @throws IllegalArgumentException si nombre inválido o la tarea no existe
     */
    public void marcarCompletado(String nombre) {
    	if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("Nombre inválido");
        }

        boolean ok = repo.marcarCompletado(nombre.trim());

        if (!ok) {
            throw new IllegalArgumentException("No existe la tarea");
        }

        comprobarYMandarAlertas();
    }

    /**
     * Lista ToDos pendientes sin completar
     * Comprueba alertas antes de devolver la lista
     * @return lista de ToDo pendientes
     */
    public List<ToDo> listarPendientes() {

    	comprobarYMandarAlertas();

        return repo.encontrarToDos().stream()
                .filter(t -> !t.isCompletado())
                .collect(Collectors.toList());
    }

    /**
     * Añade un email al repositorio para que reciba alertas
     * @param email dirección de correo que contiene '@' y '.' obligatoriamente
     * @throws IllegalArgumentException si email inválido
     */
    public void aniadirEmail(String email) {

    	 if (email == null || email.trim().isEmpty()) {
             throw new IllegalArgumentException("Email vacío");
         }

         String e = email.trim();

         if (!e.contains("@") || !e.contains(".")) {
             throw new IllegalArgumentException("Email no válido");
         }

         repo.guardarEmail(e);

         comprobarYMandarAlertas();
    }


    /**
     * Busca tareas vencidas (que no están completadas o cuya fecha límite es previa a hoy)
     * y envía alertas por email.
     */
    public void comprobarYMandarAlertas() {
    	List<ToDo> vencidos = repo.encontrarToDos().stream()
                .filter(t -> !t.isCompletado()
                        && t.getFechaLimite() != null
                        && t.getFechaLimite().isBefore(LocalDate.now()))
                .collect(Collectors.toList());

        if (vencidos.isEmpty()) {
            return;
        }

        String mensaje = "Hay tareas vencidas: \n";

        for (ToDo t : vencidos) {
            mensaje += "- " + t.getNombre() + " (límite: " + t.getFechaLimite() + ")\n";
        }

        List<String> emails = repo.listarEmails();

        for (String email : emails) {
            mailer.enviar(email, mensaje);
    }
}
}