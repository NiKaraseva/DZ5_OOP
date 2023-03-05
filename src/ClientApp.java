import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class ClientApp {
    public static void main(String[] args){

        try {
            Socket socket = new Socket("localhost", 1234);
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

            UserInterfaceView userInterfaceView = new UserInterfaceView();

            while (true){
                String request = userInterfaceView.inputExpression();
                dataOutputStream.writeUTF(request);
                if(request.equals("end")) break;
                System.out.println("Получили от сервера: " + dataInputStream.readUTF());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
