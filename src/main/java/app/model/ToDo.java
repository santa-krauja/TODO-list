package app.model;

import javax.persistence.*;

@Entity
@Table(name = "TODO_LIST", schema = "public")
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String task;

    private String progress;

    public ToDo(String task) {
        this.task = task;
        this.progress = "In progress";
    }

    public ToDo() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String gettask() {
        return task;
    }

    public void settask(String task) {
        this.task = task;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }
}
