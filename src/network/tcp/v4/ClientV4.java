package network.tcp.v4;

import network.tcp.SocketCloseUtil;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static network.tcp.SocketCloseUtil.closeAll;
import static util.MyLogger.log;

public class ClientV4 {

    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) throws IOException {
        log("클라이언트 시작");

        Socket socket = null;
        DataInputStream input = null;
        DataOutputStream output = null;

        try {
            socket = new Socket("localhost", SERVER_PORT);
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
            log("소켓 연결 : " + socket);

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.print("전송 문자 : ");
                String toSend = scanner.next();

                // 서버에게 문자 보내기
                output.writeUTF(toSend);
                log("client -> server : " + toSend);

                if ("exit".equals(toSend)) {
                    break;
                }

                // 서버로부터 문자 받기
                String received = input.readUTF();
                log("client <- server : " + received);
            }
        } catch (IOException e) {
            log(e);
        } finally {
            closeAll(input, output, socket);
            log("연결 종료 : " + socket);
        }
    }
}
