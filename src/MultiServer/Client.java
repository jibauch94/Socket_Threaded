package MultiServer;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

        private static Socket socket;
        private static PrintStream printStream;
        private static PrintWriter printWriter;
        private static Scanner scanServerOutput;


    public static void main(String args[]){
        //Scanner oprettes til brugerinput
        Scanner userInput = new Scanner(System.in);

        try{
            socket = new Socket("192.168.1.10", 8001);

            //print velkommen ud fra serveren
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            scanServerOutput = new Scanner(inputStream);
            System.out.println(scanServerOutput.nextLine());

            while (true){
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();

                scanServerOutput = new Scanner(inputStream);

                //Accept af input fra clienten/brugeren
                String stringInput = userInput.nextLine();

                //printstream object for at sende string til serveren
                printWriter = new PrintWriter(outputStream, true);
                printWriter.println(stringInput);
                if(scanServerOutput.hasNextLine()){
                    System.out.println(scanServerOutput.nextLine());
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}

