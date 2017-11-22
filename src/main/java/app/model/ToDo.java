package app.model;

public class ToDo {

    private int id;
    private String todo;

    public ToDo(int id, String todo) {
        this.id = id;
        this.todo = todo;
    }

    public ToDo() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }
}
