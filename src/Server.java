import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(8088);
             Socket socket = serverSocket.accept();
             PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            printWriter.println(count(getFromClient(printWriter, bufferedReader)));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private long count(long number) {
        if (number == 0) {
            return 0;
        } else if (number == 1 || number == 2) {
            return 1;
        } else {
            long first = 1;
            long second = 1;
            for (long i = 3; i < number; i ++) {
                long oldFirst = first;
                first += second;
                second = oldFirst;
            }
            return first + second;
        }
    }

    private long getFromClient(PrintWriter printWriter, BufferedReader bufferedReader) {
        try {
            return Long.parseLong(bufferedReader.readLine());
        } catch (IOException exception) {
            exception.printStackTrace();
            return 0;
        } catch (NumberFormatException numberFormatException) {
            printWriter.println("Введено не число! Введите повторно");
            return getFromClient(printWriter, bufferedReader);
        }
    }
}
