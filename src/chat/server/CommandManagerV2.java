package chat.server;

import java.io.IOException;
import java.util.List;

public class CommandManagerV2 implements CommandManager {

    private static final String DELIMITER = "\\|";
    private final SessionManager sessionManager;

    public CommandManagerV2(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void execute(String totalMessage, Session session) throws IOException {
        // /join|han
        if (totalMessage.startsWith("/join")) {
            String username = getBody(totalMessage);
            session.setUsername(username);
            sessionManager.sendAll(username + "님이 입장했습니다.");
        }
        else if (totalMessage.startsWith("/message")) {
            String message = getBody(totalMessage);
            sessionManager.sendAll("[" + session.getUsername() + "] " + message);
        }
        else if (totalMessage.startsWith("/change")) {
            String newUsername = getBody(totalMessage);
            sessionManager.sendAll(session.getUsername() + "님이 " + newUsername + "로 이름을 변경했습니다.");
            session.setUsername(newUsername);
        }
        else if (totalMessage.startsWith("/users")) {
            List<String> usernames = sessionManager.getAllUsername();
            StringBuilder sb = new StringBuilder();
            sb.append("전체 접속자 : ").append(usernames.size()).append("\n");
            for (String username : usernames) {
                sb.append(" - ").append(username).append("\n");
            }
            session.send(sb.toString());
        }
        else if (totalMessage.startsWith("/exit")) {
            throw new IOException("exit");
        }
        else {
            session.send("처리할 수 없는 명령어 입니다 : " + totalMessage);
        }
    }

    private static String getBody(String totalMessage) {
        return totalMessage.split(DELIMITER)[1];
    }
}