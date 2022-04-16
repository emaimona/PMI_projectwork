package Models;

public class Issue {
    private String login_name;
    private String message;
    private String date;

    public Issue(String login_name, String message, String date) {
        this.login_name = login_name;
        this.message = message;
        this.date = date;
    }

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
