/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.ucb.est;

/**
 *
 * @author Nao
 */
public class Cliente {
    private String nombre;
    private String codigoCliente;
    private String pin;

    public Cliente(String nombre, String codigoCliente, String pin) {
        this.nombre = nombre;
        this.codigoCliente = codigoCliente;
        this.pin = pin;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public String getPin() {
        return pin;
    }
}
