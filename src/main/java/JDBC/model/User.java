package JDBC.model;

import java.util.Date;
import java.util.Objects;

public class User {
    int id;
    String nick;
    String passwd;

    public User(String nick, String passwd) {
        this.nick = nick;
        this.passwd = passwd;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(nick, user.nick) &&
                Objects.equals(passwd, user.passwd);

    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nick, passwd);
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nick='" + nick + '\'' +
                ", passwd='" + passwd +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
