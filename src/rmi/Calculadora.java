package rmi;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Calculadora  implements ICalculadora {

	private static final long serialVersionUID = 1L;
	
	private static int chamadas = 0;

	public int soma(int a, int b) throws RemoteException {
		System.out.println("Método soma chamado " + chamadas++);
		return a + b;
	}

	@Override
	public int subtrair(int a, int b) throws RemoteException {
		return a - b;
	}

	@Override
	public int multiplicar(int a, int b) throws RemoteException {
		return a * b;
	}

	@Override
	public float dividir(int a, int b) throws RemoteException, DivisionByZeroException {
		if (b == 0) throw new DivisionByZeroException();
		return (float) a / b;
	}

	public static void main(String[] args) throws AccessException, RemoteException, AlreadyBoundException  {
		Calculadora calculadora = new Calculadora();		
		Registry reg = null;
		ICalculadora stub = (ICalculadora) UnicastRemoteObject.
				exportObject(calculadora, 1100);
		try {
			System.out.println("Creating registry...");
			reg = LocateRegistry.createRegistry(1099);
		} catch (Exception e) {
			try {
				reg = LocateRegistry.getRegistry(1099);
			} catch (Exception e1) {
				System.exit(0);
			}
		}
		reg.rebind("calculadora", stub);
	}
}
