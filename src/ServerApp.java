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

            Calculation calculation = new Calculation();

            while (true){
                String clientRequest = dataInputStream.readUTF();
                String[] pars = clientRequest.split(" "); // сплитуем выражение, получаем массив стрингов
                for (int i = 0; i < pars.length; i++) {  // проходимся по массиву, ищем операцию и производим действия
                    if(pars[i].equals("*")){
                        dataOutputStream.writeUTF(calculation.mult(Integer.parseInt(pars[i - 1]), Integer.parseInt(pars[i + 1])));
                    }
                    else if (pars[i].equals("/")){
                        dataOutputStream.writeUTF(calculation.div(Integer.parseInt(pars[i - 1]), Integer.parseInt(pars[i + 1])));
                    }
                    else if (pars[i].equals("+")) {
                        dataOutputStream.writeUTF(calculation.sum(Integer.parseInt(pars[i - 1]), Integer.parseInt(pars[i + 1])));
                    }
                    else if (pars[i].equals("-")){
                        dataOutputStream.writeUTF(calculation.sub(Integer.parseInt(pars[i - 1]), Integer.parseInt(pars[i + 1])));
                    }
                    else {
                        dataOutputStream.writeUTF("Введены некорректные данные");
                    }
                }
                if (clientRequest.equals("end")) break;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
