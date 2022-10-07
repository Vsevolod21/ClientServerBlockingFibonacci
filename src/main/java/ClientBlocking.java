import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientBlocking {
    public static void main(String[] args) throws IOException {
        String host = "localhost";
        int port = 23444;
        try (Socket clientSocket = new Socket(host, port);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new
                     InputStreamReader(clientSocket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {
            String inputNumber;
            while (true) {
                System.out.println("Введите порядковый номер числа Фибоначчи или end");
                inputNumber = scanner.nextLine();
                out.println(inputNumber);
                if (inputNumber.equals("end")) {
                    System.out.println("Программа завершена");
                    break;
                }
                System.out.println("Число = " + in.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
