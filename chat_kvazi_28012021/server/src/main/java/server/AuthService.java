package server;

import java.sql.SQLException;
import java.util.Map;

public interface AuthService {
    String getNicknameByLoginAndPassword(String login, String password);
    boolean registration(String login, String password, String nickname);
    boolean changeNickname(String login, String newNick);
    Map<String, String> getFilter();
}
