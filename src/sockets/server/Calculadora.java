package sockets.server;

public class Calculadora {
    public String sayHello(String nome, String sobrenome) {
        return "Fala "+ nome + " " + sobrenome;
    }
    public double soma(double oper1, double oper2) {
        return oper1 + oper2;
    }

    public double subtrair(double op1, double op2) {
        return op1 - op2;
    }

    public double multiplicar(double op1, double op2) {
        return op1 * op2;
    }

    public double dividir(double op1, double op2) {
        if (op2 == 0) return Double.MAX_VALUE;
        return op1 / op2;
    }
}