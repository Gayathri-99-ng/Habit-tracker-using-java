# Habit-tracker-using-java

## 📖 Overview  
- The Habit Tracker is a simple Java console application that helps users build and maintain productive routines.  
- It allows users to add, update, and evaluate habits, while managing them efficiently using Java Collections and LocalDate/LocalTime APIs.  
- This project was built to strengthen my skills in Java, OOP concepts, Collections, and time-based evaluation.  
  
  
## 🚀 Features    
- Add new habits with a target completion time  
- Update progress for existing habits  
- Track and display percentage completion of each habit  
- Manage multiple habits using ArrayList  
- Uses LocalDate and LocalTime for habit deadlines  
- Console-based, simple & user-friendly  
  
  
## 🛠️ Tech Stack 
  Language: Java  
Concepts Used: OOP, Collections (ArrayList), LocalDate/LocalTime  
Tools: Any IDE (IntelliJ IDEA / Eclipse / VS Code)  
  
## 📂 Project Structure      
habit-tracker-java/  
│  
├── src/  
│   ├── Habit.java  
│   ├── HabitTracker.java  
│   └── Main.java  
│  
└── README.md  

## ▶️ How to Run  

Clone the repository:
```bash
git clone https://github.com/YourUsername/habit-tracker-java.git
cd habit-tracker-java
```


Compile the Java file:
```bash
javac src/HabitTracker.java
```

Run the program:
```bash
java src/HabitTracker
```

Usage when u run the program you'll see a menu like this  

1. Add Habit 
2. Modify Habit 
3. Delete Habit 
4. View Habits 
5.Mark/Unmark Habits
6. Exit

## Example habit list output

1. ❌Not Done tiffin at 08:00 | Time left: 16 hrs 7 min [Tomorrow]  
2. ❌Not Done lunch at 13:00 | Time left: 21 hrs 7 min [Tomorrow]  
3. ❌Not Done dinner at 22:00 | Time left: 6 hrs 7 min [Today]  

   Progress: 0/3 habits completed (0%)   

## 🔮 Future Enhancements
- Add file/database support to store habits permanently  
- Build a GUI with JavaFX for better user experience  
- Introduce reminders & notifications using LocalTime  
- Generate reports & analytics (progress charts, streaks)  
- Add gamification (badges, points, streak rewards)  
- Extend to multi-user support and future cloud sync  
