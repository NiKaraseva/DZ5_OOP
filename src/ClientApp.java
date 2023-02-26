import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientApp {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String request;

        try {
            Socket socket = new Socket("localhost", 1234);
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

            while (true){
                System.out.println("Введите выражение: ");
                request = scanner.nextLine();
                dataOutputStream.writeUTF(request);
                if(request.equals("end")) break;
                System.out.println("Получили от сервера: " + dataInputStream.readUTF());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
