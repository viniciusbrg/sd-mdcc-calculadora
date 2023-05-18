package httpclient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class CalculadoraClientHTTP {

    public static String getRequest(int operationCode, float operand1, float operand2) {
        return "oper1=" + operand1 + "&oper2=" + operand2 + "&operacao=" + operationCode;
    }

    public static String doOperation(float operand1, float operand2, int operation) {
        String result = "";
        try {

            URL url = new URL("https://double-nirvana-273602.appspot.com/?hl=pt-BR");
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            //ENVIO DOS PARAMETROS
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(getRequest(operation, operand1, operand2)); //1-somar 2-subtrair 3-multiplicar 4-dividir
            writer.flush();
            writer.close();
            os.close();

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK) {

                //RECBIMENTO DOS PARAMETROS


                BufferedReader br = new BufferedReader(
                        new InputStreamReader(conn.getInputStream(), "utf-8"));
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }

                return response.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Erro";
    }

    public static void main(String[] args) {
        System.out.println("3+2 = " + doOperation(3,2, OperationCodes.SUM));
        System.out.println("3-2 = " + doOperation(3,2, OperationCodes.SUBTRACT));
        System.out.println("3x2 = " + doOperation(3,2, OperationCodes.MULTIPLY));
        System.out.println("3 / 2 = " + doOperation(3, 2, OperationCodes.DIVIDE));
    }
}
