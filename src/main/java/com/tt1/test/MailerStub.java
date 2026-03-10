package com.tt1.test;
/**
 * Implementación de servicio de envío de correo.
 * Actualmente solo tiene un método trivial para los tests.
 */
public class MailerStub implements InterfazMailerStub {

    /**
     * Método para el envío de correos
     * @param email dirección de correo
     * @param mensaje mensaje del correo
     * @return true siempre de momento; para los tests.
     */
    @Override
    public boolean enviar(String email, String mensaje) {
    	System.out.println("Envío en proceso");
        System.out.println("A: " + email);
        System.out.println("Con el mensaje: ");
        System.out.println(mensaje);
        return true;
    }
}