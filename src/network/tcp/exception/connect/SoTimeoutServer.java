package network.tcp.exception.connect;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SoTimeoutServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(12345);
        Socket socket = serverSocket.accept();

        // 무한 sleep 잠자는 서버 가정
        Thread.sleep(10000000);
    }
}
