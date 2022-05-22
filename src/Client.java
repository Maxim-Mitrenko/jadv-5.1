import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Client {

    private final Scanner scanner = new Scanner(System.in);

    public void use() {
        try (Socket socket = new Socket("localhost", 8088);
             PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            System.out.println("Введите число!");
            printWriter.println(getLongFromUser());
            System.out.println(bufferedReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private long getLongFromUser() {
        try {
            return scanner.nextLong();
        } catch (InputMismatchException e) {
            System.out.println("Введено не число! Повторите ввод!");
            scanner.nextLine();
            return getLongFromUser();
        }
    }
}
