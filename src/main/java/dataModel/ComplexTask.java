package dataModel;

import java.util.List;

public non-sealed class ComplexTask extends Task {
    private List<Task> tasks;

    public ComplexTask(int idTask, String statusTask) {
        super(idTask, statusTask);
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
}
