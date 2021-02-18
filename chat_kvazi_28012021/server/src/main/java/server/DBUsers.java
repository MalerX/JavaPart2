package server;

import com.sun.istack.internal.NotNull;
import org.sqlite.core.DB;

import java.sql.*;

public class DBUsers implements AuthService {
    private final String ADRESS = "192.168.0.11";
    private final String USER = "kvazimodo";
    private final String PASSWORD = "kvazimodo";
    private final String DATA_BASE = "kvazimodo_auth";

    private Connection connection = null;
    private PreparedStatement ps = null;
    private ResultSet resultSet = null;

    private String trueLogin = "";
    private String truePassword = "";
    private String nickname = "";

    private void getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.mariadb.jdbc.Driver");
        String cntUrl = "jdbc:mysql://" + ADRESS + "/" + DATA_BASE;
        connection = DriverManager.getConnection(cntUrl, USER, PASSWORD);
    }

    private void getUserData(String inLogin) throws SQLException {
        try {
            getConnection();
            ps = connection.prepareStatement("SELECT * FROM users WHERE login = ?");
            ps.setString(1, inLogin);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                trueLogin = resultSet.getString("login");
                truePassword = resultSet.getString("password");
                nickname = resultSet.getString("nickname");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    @Override
    public String getNicknameByLoginAndPassword(@NotNull String inLogin, @NotNull String inPassword) {
        try {
            getUserData(inLogin);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (inLogin.equals(trueLogin) && inPassword.equals(truePassword))
            return nickname;
        return null;
    }

    @Override
    public boolean registration(@NotNull String login, @NotNull String password, @NotNull String nickname) {
        try {
            getUserData(login);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (login.equals(trueLogin))
            return false;
        return regNewUsers(login, password, nickname);
    }

    @Override
    public boolean changeNickname(@NotNull String login, @NotNull String newNick) {
        try {
            getConnection();
            ps = connection.prepareStatement("UPDATE users SET nickname = ? WHERE login = ?");
            ps.setString(1, newNick);
            ps.setString(2, login);
            resultSet = ps.executeQuery();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return true;
    }

    private boolean regNewUsers(String login, String password, String nickname) {
        try {
            getConnection();
            ps = connection.prepareStatement("INSERT INTO users (uid, login, password, nickname) VALUES (NULL, ?, ?, ?);");
            ps.setString(1, login);
            ps.setString(2, password);
            ps.setString(3, nickname);
            resultSet = ps.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return true;
    }

    private void closeConnection() {
        try {
            resultSet.close();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        DBUsers dbUsers = new DBUsers();
//        dbUsers.changeNickname("aaa","ooo");
//    }
}
