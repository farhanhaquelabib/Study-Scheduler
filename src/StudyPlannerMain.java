import core.SyllabusParser;
import core.StudyPlanScheduler;
import model.ScheduledSession;
import model.Subject;
import model.Chapter;

import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

public class StudyPlannerMain{

    public static void main(String[] args) throws Exception{

        var parser = new SyllabusParser();
        List<Subject> subjects = parser.parse(Paths.get("src/syllabus.txt"));

        Scanner in = new Scanner(System.in);

        while(true){
            System.out.println("\nCustomization Menu");
            System.out.println("1) List subjects");
            System.out.println("2) Change days for a chapter");
            System.out.println("3) Change hours/day estimate for a chapter");
            System.out.println("4) Continue");
            System.out.print("Choose: ");

            String choice = in.nextLine().trim();
            if(choice.equals("4"))break;

            switch(choice){
                case "1":
                    for(int i = 0; i < subjects.size(); i++){
                        System.out.printf("%d) %s%n",i + 1, subjects.get(i).getName());
                    }
                    break;

                case "2":
                case "3":
                    System.out.print("Subject #: ");
                    int si = Integer.parseInt(in.nextLine()) - 1;
                    var s = subjects.get(si);

                    for(int j = 0; j < s.getChapters().size(); j++)
                        System.out.printf("%d) %s%n",
                                j + 1, s.getChapters().get(j));

                    System.out.print("Chapter #: ");
                    int ci = Integer.parseInt(in.nextLine()) - 1;
                    Chapter c = s.getChapters().get(ci);

                    if(choice.equals("2")){
                        System.out.print("New number of days: ");
                        c.setCustomDays(Integer.parseInt(in.nextLine()));
                    }
                    
                    else{
                        System.out.print("New estimated hours per day: ");
                        c.setEstimatedHoursPerDay(
                                Integer.parseInt(in.nextLine()));
                    }
                    System.out.println("Updated: " + c);
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }

        System.out.print("\nTarget study hours per day (e.g., 4-6): ");
        int targetDailyHours = Integer.parseInt(in.nextLine());

        var scheduler = new StudyPlanScheduler();

        LocalDate start = LocalDate.of(2026, 1, 4);
        LocalDate end = LocalDate.of(2026, 5, 31);

        List<ScheduledSession> plan =
                scheduler.buildPlan(subjects, start, end, targetDailyHours);

        System.out.println("\n===== STUDY SESSIONS =====");
        for(var s : plan) System.out.println(s);

        try(var out = new java.io.PrintWriter("study_sessions.csv")) {

    out.println("Date,Subject,Chapter,Hours");

    java.time.LocalDate lastDate = null;

    for(var s : plan){
        if(lastDate != null && !s.getDate().equals(lastDate)){
            out.println();
            out.println();
        }

        out.printf("%s,%s,%s,%d%n", s.getDate(), s.getSubject().getName(), s.getChapter().getTitle(), s.getHours());
        lastDate = s.getDate();
    }
}

System.out.println("\nSaved: study_sessions.csv");
    }
}
