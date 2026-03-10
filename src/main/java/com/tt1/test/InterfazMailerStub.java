package com.tt1.test;

/**
 * Concepto para el servicio de envío de correo; en concreto para los tests.
 */
public interface InterfazMailerStub {
    boolean enviar(String email, String mensaje);
}