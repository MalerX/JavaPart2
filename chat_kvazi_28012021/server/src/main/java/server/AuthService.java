package server;

public interface AuthService {
    String getNicknameByLoginAndPassword(String login, String password);
    String setNicknameByLoginAndPassword(String login, String password);
}
