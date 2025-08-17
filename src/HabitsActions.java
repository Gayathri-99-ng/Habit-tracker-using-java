package Main;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class HabitsActions {
    private String habitName;
    private LocalTime scheduledTime;
    private boolean completed = false;

    public HabitsActions(String habitName, LocalTime scheduledTime) {
        this.habitName = habitName;
        this.scheduledTime = scheduledTime;

    }

    public String getName() {
        return habitName;
    }

    public LocalTime getTime() {
        return scheduledTime;
    }

    public void setName(String habitName) {
        this.habitName = habitName;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setTime(LocalTime scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public String displayHabits() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String StatusIcon = completed ? "✅" : "❌";
        return habitName + " at " + scheduledTime.format(formatter);
    }
}
