package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICalculadora extends Remote{

	int soma(int a, int b) throws RemoteException;
	int subtrair(int a, int b) throws RemoteException;
	int multiplicar(int a, int b) throws RemoteException;
	float dividir(int a, int b) throws RemoteException, DivisionByZeroException;
}
