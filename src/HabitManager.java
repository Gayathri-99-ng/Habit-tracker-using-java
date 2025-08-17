package Main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.time.LocalTime;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class HabitManager {
    Scanner sc = new Scanner(System.in);
    private List<HabitsActions> habits;

    public HabitManager() {
        habits = new ArrayList<>();
    }

    public void addHabit(String habitName, LocalTime time) {
        habits.add(new HabitsActions(habitName, time));
        System.out.println("Habit added: " + habitName + " at " + time);
    }

    public void modifyHabit(int index, Scanner sc) {
        habits.sort(Comparator.comparing(HabitsActions::getTime));
        if (index >= 0 && index < habits.size()) {
            HabitsActions h = habits.get(index);
            System.out.println("What do u want to modify");
            System.out.println("1:name");
            System.out.println("2:Time");
            System.out.println("3.Both");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("enter new habitname");
                    String newName = sc.nextLine();
                    h.setName(newName);
                    break;
                case 2:
                    while (true) {
                        System.out.println("Enter new Time");
                        String strnewTime = sc.nextLine();
                        try {
                            LocalTime newTime = LocalTime.parse(strnewTime);
                            h.setTime(newTime);
                            break;
                        } catch (Exception e) {
                            System.out.println("Invalid Time format. Please try again.");
                        }
                    }
                    break;
                case 3:
                    System.out.println("enter new habit name");
                    String bothNamestr = sc.nextLine();
                    LocalTime bothTime = null;
                    while (true) {
                        System.out.println("Enter new Time");
                        String strTime = sc.nextLine();
                        try {
                            bothTime = LocalTime.parse(strTime);
                            break;
                        } catch (Exception e) {
                            System.out.println("Invalid Time format. Please try again.");
                        }
                    }
                    h.setName(bothNamestr);
                    h.setTime(bothTime);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } else {
            System.out.println("Invalid index");
        }
    }

    public void deleteHabits(int index) {
        if (habits.isEmpty()) {
            System.out.println("No habits to delete");
            return;
        }
        List<HabitsActions> sortedList = new ArrayList<>(habits);
        sortedList.sort(Comparator.comparing(HabitsActions::getTime));

        if (index >= 0 && index < sortedList.size()) {
            HabitsActions toDelete = sortedList.get(index);
            habits.remove(toDelete); // Remove from original list
            System.out.println("Habit deleted: " + toDelete.getName());
        } else {
            System.out.println("Invalid index");
        }
    }

    public void listHabits() {
        if (habits.isEmpty()) {
            System.out.println("No habits found");
            return;
        }

        List<HabitWithTime> sortedList = getSortedHabits();
        int doneCount = 0;

        for (int i = 0; i < sortedList.size(); i++) {
            HabitsActions h = sortedList.get(i).habit;
            Duration gap = sortedList.get(i).timeLeft;
            long hours = gap.toHours();
            long minutes = gap.toMinutes() % 60;
            String dayTag = sortedList.get(i).dayTag;
            String statusIcon = h.isCompleted() ? "✅Done" : "❌Not Done";

            if (h.isCompleted()) {
                doneCount++;
            }

            System.out.println((i + 1) + ". " + statusIcon + " " + h.displayHabits()
                    + " | Time left: " + hours + " hrs " + minutes + " min [" + dayTag + "]");
        }

        // Show progress summary at the end
        int total = sortedList.size();
        double percent = (total == 0) ? 0 : ((double) doneCount / total) * 100;
        System.out.printf("Progress: %d/%d habits completed (%.0f%%)%n", doneCount, total, percent);
    }

    private static class HabitWithTime {
        HabitsActions habit;
        LocalDateTime nextTime;
        Duration timeLeft;
        String dayTag;

        HabitWithTime(HabitsActions habit, LocalDateTime nextTime, Duration timeLeft, String dayTag) {
            this.habit = habit;
            this.nextTime = nextTime;
            this.timeLeft = timeLeft;
            this.dayTag = dayTag;
        }

    }

    public boolean hasHabits() {
        return !habits.isEmpty();
    }

    public void showNextHabit() {
        if (habits.isEmpty()) {
            System.out.println("No habits found.");
            return;
        }

        LocalTime now = LocalTime.now();
        HabitsActions nextHabit = null;
        Duration shortestGap = null;

        for (HabitsActions habit : habits) {
            LocalTime habitTime = habit.getTime();
            LocalTime nextOccurrence = habitTime;

            if (!habitTime.isAfter(now)) {
                nextOccurrence = habitTime.plusHours(24);
            }

            Duration currentGap = Duration.between(now, nextOccurrence);

            if (shortestGap == null || currentGap.compareTo(shortestGap) < 0) {
                shortestGap = currentGap;
                nextHabit = new HabitsActions(habit.getName(), nextOccurrence);
            }
        }

        if (nextHabit != null && shortestGap != null) {
            long hours = shortestGap.toHours();
            long minutes = shortestGap.toMinutes() % 60;

            System.out.println("Next Habit: " + nextHabit.getName() + " at " + nextHabit.getTime().toString());
            System.out.println("Time left: " + hours + " hours " + minutes + " minutes");
        } else {
            System.out.println("No upcoming habits found.");
        }
    }

    public String getHabitName(int index) {
        return (index >= 0 && index < habits.size()) ? habits.get(index).getName() : "";
    }

    public LocalTime getHabitTime(int index) {
        return (index >= 0 && index < habits.size()) ? habits.get(index).getTime() : LocalTime.now();
    }

    public int getHabitsCount() {
        return habits.size();
    }

    public void HabitStatus(int index) {
        List<HabitWithTime> sortedList = getSortedHabits();
        if (index >= 0 && index < sortedList.size()) {
            HabitsActions h = sortedList.get(index).habit;
            h.setCompleted(!h.isCompleted());
            System.out.println("habit marked as " + (h.isCompleted() ? "Completed ✅" : "Not Completed ❌"));
        } else {
            System.out.println("Invalid Habit Number");
        }
    }

    public List<HabitWithTime> getSortedHabits() {
        LocalDateTime now = LocalDateTime.now();
        List<HabitWithTime> sortedList = new ArrayList<>();

        for (HabitsActions h : habits) {
            LocalTime habitTime = h.getTime();
            LocalDateTime nextOccurrence = LocalDateTime.of(LocalDate.now(), habitTime);

            String dayTag = "Today";
            if (nextOccurrence.isBefore(now)) {
                nextOccurrence = nextOccurrence.plusDays(1);
                dayTag = "Tomorrow";
            }

            Duration gap = Duration.between(now, nextOccurrence);
            sortedList.add(new HabitWithTime(h, nextOccurrence, gap, dayTag));
        }

        sortedList.sort(Comparator.comparing(hw -> hw.habit.getTime()));
        return sortedList;
    }

}
