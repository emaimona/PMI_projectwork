package Models;

public class User {
    protected String login_name;
    protected String password;

    /*
    * Once a user can be any object
    * < object, number>
    * */

    public User(String login_name, String password) {
        this.login_name = login_name.toUpperCase();
        this.password = password;
    }

    public User() {
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name.toUpperCase();
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin_name() {
        return login_name;
    }

    public String getPassword() {
        return password;
    }

    public String[] getId() {
        String[] code = {this.login_name.substring(0,2), this.login_name.substring(2)};
        return code;
    }

}
