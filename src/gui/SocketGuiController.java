package gui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.util.Duration;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class SocketGuiController {
    public Button sendBtn;
    public Button connectBtn;
    public Button countBtn;
    public TextArea inputArea;
    public TextArea displayArea;

    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;
    private Scanner scanServerOutput;
    private PrintWriter printWriter; //skriver til serveren
    private int duration = 5000;
    private int cycleCount = 100;


    @FXML
    public void handleSendBtnAction(ActionEvent actionEvent) {
        setInputOutput();
        timer();

        if(!inputArea.getText().isEmpty()){
            //inputArea is not empty
            printWriter.println(inputArea.getText());

            if (scanServerOutput.hasNextLine()){
                displayArea.appendText(scanServerOutput.nextLine() + "\n");
            }
        }
    }

    @FXML
    public void handleCountBtnAction(ActionEvent actionEvent) {
        //setInputOutput();
        //printWriter.println("COUNT:");
        //displayArea.appendText(scanServerOutput.nextLine() + "\n");

    }

    @FXML
    public void handleConnectBtnAction(ActionEvent actionEvent) {
        try{
            socket = new Socket("192.168.1.10", 8001);
            displayArea.appendText("Tilsluttet til server" + "\n");

            setInputOutput();

            displayArea.appendText(scanServerOutput.nextLine() + "\n");

        }
        catch (UnknownHostException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        }


    private void setInputOutput(){
        try{
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();

            scanServerOutput = new Scanner(inputStream);

            printWriter = new PrintWriter(outputStream, true);

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public void counter(){
        setInputOutput();
        printWriter.println("COUNT:");
        displayArea.appendText(scanServerOutput.nextLine() + "\n");
    }
public void timer(){
    Timeline timeline = new Timeline(new KeyFrame(
            Duration.millis(duration), ae -> counter()));
    timeline.setCycleCount(cycleCount);
    timeline.play();
}

}
