package dataModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public non-sealed class ComplexTask extends Task implements Serializable {
    private List<Task> tasks;

    public ComplexTask(int idTask, String statusTask) {
        super(idTask, statusTask);
        tasks = new ArrayList<Task>();
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    @Override
    public int estimateDuration() {
        int duration = 0;
        for(Task task : tasks){
            duration += task.estimateDuration();
        }
        return duration;
    }
    @Override
    public String toString() {
        return super.toString() + "(complex)";
    }
}
