import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {
    public static void main(String[] args){
        try {
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("Сервер запущен и ожидает подключения");
            Socket socket = serverSocket.accept();
            System.out.println("Клиент подключился");
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

            ParserModel parserModel = new ParserModel();

            while (true){
                String clientRequest = dataInputStream.readUTF();
                dataOutputStream.writeUTF(parserModel.parserEquation(clientRequest));
                if (clientRequest.equals("end")) break;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
