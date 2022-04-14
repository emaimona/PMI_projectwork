package DAL;

import Models.User;

public class DAL{
    public final int LOGIN_SIZE = 2;
    public final int MINUMUN_PASSWORD_SIZE = 1;


    protected boolean register_user(User user) {
        return true;
    }

    protected boolean authenticate(String login_name, String password) {
        if(login_name.length() != LOGIN_SIZE || password.length() < MINUMUN_PASSWORD_SIZE)
            return false;

        if(password.equals("0"))
            return true;

        return false;
    }
}
