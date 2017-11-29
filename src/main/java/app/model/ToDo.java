package app.model;

import javax.persistence.*;

@Entity
@Table(name = "TODO_LIST", schema = "public")
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String task;

    @Enumerated(EnumType.STRING)
    private TaskProgress progress;

    private String assignee;

    public ToDo(String task) {
        this.task = task;
        this.progress = TaskProgress.NOT_STARTED;
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
        return progress.toString();
    }

    public String getProgressEnumConstant() {
        return progress.name();
    }

    public void setProgress(String progress) {
        this.progress = TaskProgress.valueOf(progress) ;
    }
}
