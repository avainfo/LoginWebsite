package fr.avainfo.loginwebsite.bean;

public class ClasseDeCours {
    private String name;
    private int students;
    private int chairs;
    private int table;
    private int currentStudents;

    public ClasseDeCours() {
    }

    public ClasseDeCours(String name, int students, int chairs, int table, int actualStudents) {
        this.name = name;
        this.students = students;
        this.chairs = chairs;
        this.table = table;
        this.currentStudents = actualStudents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStudents() {
        return students;
    }

    public void setStudents(int students) {
        this.students = students;
    }

    public int getChairs() {
        return chairs;
    }

    public void setChairs(int chairs) {
        this.chairs = chairs;
    }

    public int getTable() {
        return table;
    }

    public void setTable(int table) {
        this.table = table;
    }

    public int getCurrentStudents() {
        return currentStudents;
    }

    public void setCurrentStudents(int currentStudents) {
        this.currentStudents = currentStudents;
    }

    public void sayHello() {
        System.out.println("Les élèves disent bonjour !");
    }

}
