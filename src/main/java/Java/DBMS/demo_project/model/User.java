package Bankmanagement.app.model;

public class User {
    private int id;
    private String name;
    private String Accno;

    public User() {
    }

    public User(int id, String name,String Accno) {
        this.id = id;
        this.name = name;
        this.Accno = Accno;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAccno() {
        return Accno;
    }
}