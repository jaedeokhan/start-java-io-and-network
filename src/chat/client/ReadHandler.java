package chat.client;

import util.MyLogger;

import java.io.DataInputStream;
import java.io.IOException;

import static util.MyLogger.*;

public class ReadHandler implements Runnable {

    private final DataInputStream input;
    private final Client client;
    public boolean closed = false;

    public ReadHandler(DataInputStream input, Client client) {
        this.input = input;
        this.client = client;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String received = input.readUTF();
                System.out.println(received);
            }
        } catch (IOException e) {
            log(e);
        } finally {
            client.close();
        }
    }

    // 스레드 동시 호출되지 않게 synchronized
    public synchronized void close() {
        if (closed) {
            return;
        }
        // 종료 로직 필요시 작성
        closed = true;
        log("readHandler 종료");
    }
}
