package bo.edu.ucb.est;

import java.util.ArrayList;
import java.util.Scanner;

public class Cajero {
	public static ArrayList<Cuenta> Cuentas = new ArrayList<Cuenta>();
	public static ArrayList<Cliente> Clientes = new ArrayList<Cliente>();
	ArrayList<Cuenta> cuentasCliente = new ArrayList<Cuenta>();
	Scanner entrada = new Scanner(System.in);

	public void inicializarCajero() {
		Cliente cliente1 = new Cliente("Juan Perez", "jperez", "3333");
		Cliente cliente2 = new Cliente("Maria Gomez", "mgomez", "4444");
		Cliente cliente3 = new Cliente("Tatiana Perez", "tperez", "5555");
		Clientes.add(cliente1);
		Clientes.add(cliente2);
		Clientes.add(cliente3);
		Cuenta cuenta11 = new Cuenta(cliente1.getCodigoCliente(), "111122", "Bolivianos", "Caja de ahorros", 12000);
		Cuenta cuenta12 = new Cuenta(cliente1.getCodigoCliente(), "11221", "USD", "Cuenta Corriente", 100);
		Cuenta cuenta21 = new Cuenta(cliente2.getCodigoCliente(), "221122", "Bolivianos", "Caja de ahorros", 0);
		Cuenta cuenta31 = new Cuenta(cliente3.getCodigoCliente(), "331122", "Bolivianos", "Caja de ahorros", 100);
		Cuenta cuenta32 = new Cuenta(cliente3.getCodigoCliente(), "332211", "USD", "Cuenta Corriente", 1000);
		Cuenta cuenta33 = new Cuenta(cliente3.getCodigoCliente(), "332233", "Bolivianos", "Caja de ahorros", 100000);
		Cuentas.add(cuenta11);
		Cuentas.add(cuenta12);
		Cuentas.add(cuenta21);
		Cuentas.add(cuenta31);
		Cuentas.add(cuenta32);
		Cuentas.add(cuenta33);
	}

	public void inicio() {
		String cod, pin;
		System.out.println("\n-------CAJERO AUTOMÁTICO-------");
		Boolean flag = false;
		while (!flag) {
			System.out.print("Ingrese el código de cliente: ");
			cod = entrada.nextLine();
			System.out.print("Ingrese su pin: ");
			pin = entrada.nextLine();
			if (busqueda(cod, pin)) {
				flag = true;
			} else {
				System.out.println("Incorrecto. \nIngrese los datos nuevamente\n");
			}
		}
	}

	public boolean busqueda(String cod, String pin) {
		Boolean flag = false;
		for (Cliente dato : Clientes) {
			if (dato.getCodigoCliente().equals(cod)) {
				if (dato.getPin().equals(pin)) {
					flag = true;
					menu(cod);
				}
			}
		}
		return flag;
	}

	public void menu(String cod) {
		Boolean flag = false;
		int nCuenta;
		do {
			System.out.println(
					"\nMenu:" + "\n1. Ver saldo" + "\n2. Retirar dinero" + "\n3. Depositar dinero" + "\n4. Salir al Menu\n5. Salir del sistema");
			String opcion = entrada.nextLine();
			if (!opcion.equals("1") && !opcion.equals("2") && !opcion.equals("3") && !opcion.equals("4")&&!opcion.equals("5")) {
				System.out.println("----------------------\nIngrese una opcion valida\n----------------------\n");
			} else if (opcion.equals("4")) {
				flag = true;
				inicio();
			}else if(opcion.equals("5")) {
				System.out.println("Gracias por usar el sistema  :) !");
				System.exit(0);
			}else {
				nCuenta = desplegarCuentas(cod) - 1;
				if (opcion.equals("1")) {
					System.out.println(cuentasCliente.get(nCuenta));
				} else if (opcion.equals("2")) {
					retiro(nCuenta);
				} else if(opcion.equals("3")){
					deposito(nCuenta);
				}
			}
		} while (flag == false);
	}

	public void retiro(int nCuenta) {
		Boolean flag1 = true;
		String monto;
		if(cuentasCliente.get(nCuenta).getSaldo()==0) {
			System.out.println("No se puede retirar dinero de la cuenta ya que tiene un saldo de 0. \nSeleccione otra cuenta o deposite.");
			menu(cuentasCliente.get(nCuenta).getCodCliente());
		}else {
			do {
				flag1 = true;
				System.out.print("\nSaldo: " + cuentasCliente.get(nCuenta).getSaldo() + "\n------------------------------"
						+ "\nIngrese el monto a retirar (" + cuentasCliente.get(nCuenta).getMoneda() + "): ");
				monto = entrada.nextLine();
				try {
					if (Integer.parseInt(monto) > cuentasCliente.get(nCuenta).getSaldo()) {
						flag1 = false;
						System.err.println("El monto a retirar debe ser menor al saldo actual.");
					}
				} catch(Exception e) {
					System.err.println("Ingrese un numero entero.");
					flag1 = false;
				}
			} while (!validacion(monto) || !flag1);
			cuentasCliente.get(nCuenta).retirar(Integer.parseInt(monto));
		}
		
	}

	public void deposito(int nCuenta) {
		String monto;
		do {
			System.out.print("\nSaldo: " + cuentasCliente.get(nCuenta).getSaldo() + "\n------------------------------"
					+ "\nIngrese el monto a depositar (" + cuentasCliente.get(nCuenta).getMoneda() + "): ");
			monto = entrada.nextLine();
		} while (!validacion(monto));

		cuentasCliente.get(nCuenta).depositar(Integer.parseInt(monto));
	}

	public int desplegarCuentas(String cod) {
		int n = 0;
		boolean flag = true;
		cuentasCliente.clear();
		for (Cuenta dato : Cuentas) {
			if (dato.getCodCliente().equals(cod)) {
				cuentasCliente.add(dato);
			}
		}
		do {
			flag = true;
			System.out.println("\nSeleccione una cuenta:");
			for (int i = 0; i < cuentasCliente.size(); i++) {
				System.out.println((i + 1) + ". Cuenta " + (i + 1) + ": " + cuentasCliente.get(i).getNroCuenta());
			}
			try {
				n = Integer.parseInt(entrada.nextLine());
				if(n>cuentasCliente.size()||n<=0) {
					System.err.println("No se encontró la cuenta, ingrese la opción correctamente");
					flag=false;
				}
			} catch (Exception e) {
				System.out.println("Ingrese la opción en formato numérico y entero");
				flag = false;
			}
		} while (!flag);

		return n;
	}

	public boolean validacion(String monto) {
		try {
			double montoReal = Double.parseDouble(monto);
			if (montoReal == 0) {
				System.err.println("El monto debe ser diferente a 0.");
				return false;
			} else if (montoReal < 0) {
				System.err.println("El monto debe ser positivo.");
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			System.err.println("Ingrese el monto en formato numérico.");
			return false;
		}
	}
}
