package Models;

public class User {
    protected String login_name;
    protected String password;

    /*
    * Once a user can be any object
    * < object, number>
    * */

    public User(String login_name, String password) {
        this.login_name = login_name;
        this.password = password;
    }

    public String[] getId() {
        String[] code = {this.login_name.substring(0,2), this.login_name.substring(2)};
        return code;
    }

}
