package network.tcp.v6;

import java.util.ArrayList;
import java.util.List;

// 동시성 처리
// 여러 스레드에서 add, remove, closeAll 같이 호출 가능성 존재
public class SessionManagerV6 {

    private List<SessionV6> sessions = new ArrayList<>();

    public synchronized void add(SessionV6 session) {
        sessions.add(session);
    }

    public synchronized void remove(SessionV6 session) {
        sessions.remove(session);
    }

    public synchronized void closeAll() {
        for (SessionV6 session : sessions) {
            session.close();
        }
        sessions.clear();
    }

}
