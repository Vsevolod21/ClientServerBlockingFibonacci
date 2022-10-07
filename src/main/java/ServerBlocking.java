import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class ServerBlocking {
    public static void main(String[] args) throws IOException {
        int port = 23444;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Сервак стартовал");
        while (true) {
            try (Socket socket = serverSocket.accept();
                 BufferedReader in = new BufferedReader(new
                         InputStreamReader(socket.getInputStream()));
                 PrintWriter out = new
                         PrintWriter(socket.getOutputStream(), true)) {
                String inputUser;
                while ((inputUser = in.readLine()) != null) {
                    if (inputUser.equals("end")) {
                        System.out.println("Программа завершена");
                        socket.close();
                        break;
                    }
                    int number = Integer.parseInt(inputUser) - 1;
                    int answer = calcFibonacci(number);
                    out.println(answer);
                }
                break;
            } catch (IOException e) {
                e.printStackTrace(System.out);
            }
        }
    }

    public static int calcFibonacci(int inputNumber) {
        int[] fib = new int[inputNumber + 1];
        fib[0] = 0;
        fib[1] = 1;
        for (int i = 2; i <= inputNumber; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        System.out.println("\nПоследовательность Фибоначчи:\n" + Arrays.toString(fib));
        return fib[inputNumber];
    }
}
