package Main;

import java.util.*;
import java.time.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HabitManager manager = new HabitManager();
        boolean running = true;
        while (running) {
            System.out.println("Habit Tracker Menu");
            System.out.println("1.Add Habit");
            System.out.println("2.Modify Habit");
            System.out.println("3.Delete Habit");
            System.out.println("4.view Habit");
            System.out.println("5.Mark/Unmark Habit");
            System.out.println("6.Exit");
            System.out.println("Choose from the above options ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    List<HabitsActions> tempHabits = new ArrayList<>();
                    while (true) {
                        System.out.println("Enter habit name (or 1 to Save, 0 to Cancel):");
                        String input = sc.nextLine();

                        if (input.equals("1")) {
                            for (HabitsActions h : tempHabits) {
                                manager.addHabit(h.getName(), h.getTime());
                            }
                            System.out.println(tempHabits.size() + " Habits saved");
                            break;
                        } else if (input.equals("0")) {
                            System.out.println("Habits Discarded");
                            break;
                        } else {
                            String habitName = input;
                            LocalTime time = null;
                            while (true) {
                                System.out.println("Enter Time in 24 Hr format (HH:mm):");
                                String timeStr = sc.nextLine();
                                try {
                                    time = LocalTime.parse(timeStr);
                                    break;
                                } catch (Exception e) {
                                    System.out.println("Invalid Time format. Please try again.");
                                }
                            }
                            tempHabits.add(new HabitsActions(habitName, time));
                        }
                    }
                    break;

                case 2:
                    if (!manager.hasHabits()) {
                        System.out.println("No habits to edit/show");
                        break;
                    }
                    manager.listHabits();
                    System.out.println("Enter habit number to modify");
                    int index = sc.nextInt() - 1;
                    sc.nextLine();
                    manager.modifyHabit(index, sc);
                    break;
                case 3:
                    if (!manager.hasHabits()) {
                        System.out.println("No habits to delete");
                        break;
                    }
                    manager.listHabits();
                    System.out.println("Enter habit number to Delete");
                    int deleteInput = sc.nextInt() - 1;
                    manager.deleteHabits(deleteInput);
                    break;
                case 4:
                    manager.listHabits();
                    break;
                case 5:
                    System.out.print("Enter habit number to toggle status: ");
                    int toggleIndex = sc.nextInt() - 1;
                    sc.nextLine();
                    manager.HabitStatus(toggleIndex);
                    break;
                case 6:
                    running = false;
                    System.out.println("Exiting Habit Tracker");
                    break;
                default:
                    System.out.println("Invalid Option. Try Again");
            }

        }
        sc.close();
    }
}
