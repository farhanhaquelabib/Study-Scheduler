# 📚 Study Planner --- Java Console Application

A console-based **study planning tool** that reads a syllabus file,
allows the user to customize chapter durations and estimated study
hours, and automatically generates a **day‑by‑day study schedule** up to
a chosen end date. The final plan is exported to a **CSV file** for easy
review or printing.

## ✨ Features

-   📂 Reads syllabus from `syllabus.txt`
-   📚 Organizes content into **subjects and chapters**
-   ⚙️ Customize:
    -   number of days per chapter
    -   estimated hours per day for each chapter
-   🎲 Generates a study plan with **2--3 random subjects per day**
-   🕒 Splits the daily target study hours across subjects
-   🗓️ Creates a schedule between configurable start & end dates
-   📤 Exports the schedule to `study_sessions.csv` with blank‑line
    spacing between dates
-   🧩 Beginner‑friendly Java OOP structure


## 🛠️ Technologies Used

-   Java (OOP)
-   File parsing
-   Scheduling logic
-   CLI input handling
-   CSV export

## ▶️ How to Run (No IDE)

### 1️⃣ Compile

    javac -d out -cp src src/StudyPlannerMain.java


### 2️⃣ Run

    java -cp out StudyPlannerMain

## 📝 Syllabus File Format

    Physics 1st Paper:
    1(Vectors)
    2(Dynamics)

    Chemistry:
    1(Organic Chemistry)
    2(Periodic Table)

-   Lines ending with `:` → subject
-   Lines with `( )` → chapter name


