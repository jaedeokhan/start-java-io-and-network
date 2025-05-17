package network.tcp.v2.custom;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static util.MyLogger.log;

public class ClientCV2 {

    public static final int SERVER_PORT = 12345;
    public static final String EXIT = "exit";

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost", SERVER_PORT);
        log("클라 Socket 생성 : " + socket);
        Scanner scanner = new Scanner(System.in);
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        DataInputStream input = new DataInputStream(socket.getInputStream());

        String inputData;
        while (true) {

            inputData = scanner.next();

            // client -> server
            log("Client -> Server : " + inputData);
            output.writeUTF(inputData);

            if (EXIT.equals(inputData.toLowerCase())) {
                break;
            }

            // client <- server
            String received = input.readUTF();
            log("Client <- Server : " + received);
        }

        System.out.printf("%s 를 입력했슴다. Bye~ ", EXIT);
        input.close();
        output.close();
        socket.close();
        scanner.close();
    }
}
