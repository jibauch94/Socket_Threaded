package MultiServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadedServer
{
    public static void main(String[] args) {
        ServerSocket serverSocket;

        try {
            //server sættes til at lytte på port 8001
            serverSocket = new ServerSocket(8001);
            System.out.println("server is running");

            while (true){
                //får serveren til at stå og vente på en client
                Socket socket = serverSocket.accept();

                //Client connectionklassen oprettes og implementere Runnable - klassen håndtere al server logik
                Runnable runnable = new ClientConnection(socket);
                Thread thread = new Thread(runnable);
                thread.start();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
