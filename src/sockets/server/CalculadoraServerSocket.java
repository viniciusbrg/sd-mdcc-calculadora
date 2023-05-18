package sockets.server;

import sockets.OperationCodes;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class CalculadoraServerSocket {


    public static double handleOperation(Calculadora calculadora, String opCode, String op1, String op2) {
        double operand1 = Double.parseDouble(op1);
        double operand2 = Double.parseDouble(op2);
        int operation = Integer.parseInt(opCode);

        double result;

        switch (operation) {
            case OperationCodes.SUM:
                result = calculadora.soma(operand1, operand2);
                break;
            case OperationCodes.SUBTRACT:
                result = calculadora.subtrair(operand1, operand2);
                break;
            case OperationCodes.MULTIPLY:
                result = calculadora.multiplicar(operand1, operand2);
                break;
            case OperationCodes.DIVIDE:
                result = calculadora.dividir(operand1, operand2);
                break;
            default:
                result = 0;
        }

        return result;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ServerSocket welcomeSocket;
        DataOutputStream socketOutput;
        BufferedReader socketInput;
        Calculadora calc = new Calculadora();
        try {
            welcomeSocket = new ServerSocket(9090);

            System.out.println("Servidor no ar");
            while (true) {

                Socket connectionSocket = welcomeSocket.accept();
                System.out.println("Nova conexão");

                //Interpretando dados do servidor
                socketInput = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                String operacao = socketInput.readLine();
                String oper1 = socketInput.readLine();
                String oper2 = socketInput.readLine();


                //Chamando a calculadora
                var result = String.valueOf(handleOperation(calc, operacao, oper1, oper2));

                //Enviando dados para o servidor
                socketOutput = new DataOutputStream(connectionSocket.getOutputStream());
                socketOutput.writeBytes(result + '\n');
                System.out.println(result);
                socketOutput.flush();
                socketOutput.close();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
