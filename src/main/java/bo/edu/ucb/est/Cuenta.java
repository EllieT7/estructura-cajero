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
public class Cuenta {
    private String nroCuenta;
    private String moneda;
    private String tipo;
    private int saldo;
    private String codCliente;
    
    public Cuenta(String codCliente,String nroCuenta, String moneda, String tipo, int saldo) {
        this.codCliente = codCliente;
        this.nroCuenta = nroCuenta;
        this.moneda = moneda;
        this.tipo = tipo;
        this.saldo = saldo;
    }

    public String getCodCliente() {
        return codCliente;
    }

    public String getNroCuenta() {
        return nroCuenta;
    }

    public String getMoneda() {
        return moneda;
    }

    public String getTipo() {
        return tipo;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }
    public void retirar(int n){
        setSaldo(getSaldo()-n);
        System.out.println("Dinero retirado exitosamente!\n Saldo actual: "+getSaldo()+"\n");
    }
    public void depositar(int n){
        setSaldo(getSaldo()+n);
        System.out.println("Dinero depositado exitosamente!\n Saldo actual: "+getSaldo()+"\n");
    }
    public String toString() {
    	return    "---------------------------------\n"
    			+ "     Información de la cuenta    \n"
    			+ "---------------------------------\n"
    			+ " Nº de cuenta: "+getNroCuenta()+"\n"
    			+ " Tipo: "+getTipo()+"\n"
    			+ " Moneda: "+getMoneda()+"\n"
    			+ " Saldo: "+getSaldo()+"\n"
    			+ "---------------------------------\n";
    }
}
