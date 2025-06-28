package network.tcp.exception.close.normal.reset;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

import static util.MyLogger.log;

public class ResetCloseClient {

    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("localhost", 12345);
        log("소켓 연결 : " + socket);
        InputStream input = socket.getInputStream();
        OutputStream output = socket.getOutputStream();

        input.close();
        output.close();
        socket.close();

        // client <- server : FIN
        Thread.sleep(1000); // 서버가 close() 호출 할 때까지 잠시 대기

        // 서버에서 FIN을 날리면, FIN을 보내야하는데 PUSH 가정
        // client -> server : PUSH[1]
        output.write(1);

        // client <- server : RST
        Thread.sleep(1000); // RST 메시지 전송 대기

        try {
            // java.net.SocketException: Connection reset
            int read = input.read();
            System.out.println("read = " + read);
        } catch (SocketException e) {
            e.printStackTrace();
        }

        try {
            // java.net.SocketException: Broken pipe
            output.write(1);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
