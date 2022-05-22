public class Main {

    public static void main(String[] args) {
        new Thread(new Server()::start).start();
        new Thread(new Client()::use).start();
    }
}
