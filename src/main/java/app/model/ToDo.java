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
    private String assignee;

    public ToDo(String task) {
        this.task = task;
        this.progress = "Not started";
    }

    public ToDo() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }
}
