package network.tcp.v1;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static util.MyLogger.log;

public class InetAddressMain {

    public static void main(String[] args) throws UnknownHostException {
        InetAddress localhost = InetAddress.getByName("localhost");
        InetAddress google = InetAddress.getByName("google.com");
        InetAddress toss = InetAddress.getByName("toss.im");
        InetAddress woori = InetAddress.getByName("wooribank.com");

        log(localhost);
        log(google);
        log(toss);
        log(woori);
    }
}
