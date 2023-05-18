package rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class CalculadoraCliente {

	public static void main(String[] args) {
		Registry reg = null;
		ICalculadora calc;
		try {
			reg = LocateRegistry.getRegistry(1099);
			calc = (ICalculadora) reg.lookup("calculadora");
			System.out.println("3+2 = " + calc.soma(3,2));
			System.out.println("3-2 = " + calc.subtrair(3,2));
			System.out.println("3x2 = " + calc.multiplicar(3,2));
			System.out.println("3 / 2 = " + calc.dividir(3, 2));

			System.out.println("3 / 0 = ...");
			calc.dividir(3, 0);

		} catch (RemoteException | NotBoundException | DivisionByZeroException e) {
			System.out.println(e);
			System.exit(0);
		}
	}

}
