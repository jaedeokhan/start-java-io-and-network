package network.tcp.v2.custom;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static util.MyLogger.log;

public class ServerCV2 {

    public static final int PORT = 12345;
    public static final String EXIT = "exit";

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(PORT);
        log("서버 ServerSocket 생성 : " + serverSocket);
        Socket socket = serverSocket.accept();
        log("서버 socket accept : " + socket);
        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());

        String received;
        while ((received = input.readUTF()) != null) {
            log("Client -> Server : " + received);

            if (EXIT.equals(received.toLowerCase())) {
                break;
            }

            output.writeUTF(received);
            log("Client <- Server : " + received);
        }

        System.out.printf("%s 를 입력했슴다. Bye~ ", EXIT);
        input.close();
        output.close();
        socket.close();
        serverSocket.close();
    }
}
