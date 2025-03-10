package dataModel;

import java.io.Serializable;

public non-sealed class SimpleTask extends Task implements Serializable {
    private int startHour;
    private int endHour;

    public SimpleTask(int idTask, String statusTask, int startHour, int endHour) {
        super(idTask, statusTask);
        this.startHour = startHour;
        this.endHour = endHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    public int getStartHour() {
        return startHour;
    }

    public int getEndHour() {
        return endHour;
    }

    @Override
    public int estimateDuration() {
        return endHour - startHour;
    }
    @Override
    public String toString() {
        return super.toString() + " [startHour=" + startHour + ", endHour=" + endHour + "]";
    }
}
