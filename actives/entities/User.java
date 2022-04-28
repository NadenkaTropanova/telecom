package actives.entities;

public class User {
    private Integer id_user;
    private String password;

    public User(Integer id_user, String password) {
        this.id_user = id_user;
        this.password = password;
    }

    public User() {

    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
