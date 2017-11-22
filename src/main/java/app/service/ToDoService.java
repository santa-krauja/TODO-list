package app.service;

import app.model.ToDo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ToDoService {

    private List<ToDo> toDoList;



    {
        toDoList = new ArrayList<>();
        toDoList.add(new ToDo(1, "Ask for help"));
        toDoList.add(new ToDo(2, "Watch 9gag"));
        toDoList.add(new ToDo(2, "Watch 9gag"));
        toDoList.add(new ToDo(2, "Watch 9gag"));
        toDoList.add(new ToDo(2, "Watch 9gag"));
    }

    public List<ToDo> getToDoList() {
        return toDoList;
    }

    public void setToDoList(List<ToDo> toDoList) {
        this.toDoList = toDoList;
    }


}
