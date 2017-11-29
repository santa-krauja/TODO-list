package app.model;

import javax.persistence.*;

import java.util.Objects;

import static app.model.TaskProgress.*;
import static javax.persistence.EnumType.*;
import static javax.persistence.GenerationType.*;

@Entity
@Table(name = "TODO_LIST", schema = "public")
public class ToDo {

    @Id
    @GeneratedValue(strategy = AUTO)
    private int id;

    private String task;

    @Enumerated(STRING)
    private TaskProgress progress;

    private String assignee;

    public ToDo(String task) {
        this.task = task;
        this.progress = NOT_STARTED;
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
        return progress.getName();
    }

    public String getProgressEnumConstant() {
        return progress.name();
    }

    public void setProgress(String progress) {
        this.progress = TaskProgress.valueOf(progress) ;
    }

    @Override
    public String toString() {
        return "ToDo{" +
                "id=" + id +
                ", task='" + task + '\'' +
                ", progress=" + progress +
                ", assignee='" + assignee + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDo toDo = (ToDo) o;
        return getId() == toDo.getId() &&
                Objects.equals(getTask(), toDo.getTask());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getTask());
    }
}