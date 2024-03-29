package app.model;

import static app.model.TaskProgress.valueOf;
import static java.util.Objects.hash;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.AUTO;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name = "TODO_LIST", schema = "public")
public class ToDo {

    @Id
    @GeneratedValue(strategy = AUTO)
    @NotNull
    @Min(1)
    @Digits(fraction = 0, integer = 10)
    private int id;

    @Size(max = 500, message = "Task can not be too long")
    @NotBlank(message = "Task needs to be assigned!")
    private String task;

    @Enumerated(STRING)
    @NotNull(message = "Progress needs to be assigned")
    private TaskProgress progress;

    @Size(max = 250, message = "Assignees names can not be too long")
    @NotBlank(message = "Someone needs to be assigned")
    private String assignee;

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

    @JsonIgnore
    public String getProgressEnumConstant() {
        return progress.name();
    }

    public void setProgress(String progress) {
        this.progress = valueOf(progress);
    }

    public String toJsonString() {
        return "{\"id\":" + id + "," +
                "\"task\":\"" + task + "\"," +
                "\"progress\":\"" + progress.getName() + "\"," +
                "\"assignee\":\"" + assignee + "\"}";
    }

    public String toJsonEnumString() {
        return "{\"id\":" + id + "," +
                "\"task\":\"" + task + "\"," +
                "\"progress\":\"" + progress + "\"," +
                "\"assignee\":\"" + assignee + "\"}";
    }

    @Override
    public String toString() {
        return "{\"id\": " + id + ",\n" +
                "\"task\": \"" + task + "\",\n" +
                "\"progress\": \"" + progress.getName() + "\",\n" +
                "\"assignee\": \"" + assignee + "\"}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDo toDo = (ToDo) o;
        return (getId() == toDo.getId()) &&
                Objects.equals(getTask(), toDo.getTask());
    }

    @Override
    public int hashCode() {
        return hash(getId(), getTask());
    }

}