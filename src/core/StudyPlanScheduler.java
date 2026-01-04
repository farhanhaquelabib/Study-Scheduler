package core;
import model.*;
import java.time.LocalDate;
import java.util.*;

public class StudyPlanScheduler{
    
        private static class ChapterEntry {
        final Subject subject;
        final Chapter chapter;
        int remainingHours;

        ChapterEntry(Subject s, Chapter c, int remainingHours) {
            this.subject = s;
            this.chapter = c;
            this.remainingHours = remainingHours;
        }
    }

    private final Random random = new Random();

    public List<ScheduledSession> buildPlan(List<Subject> subjects, LocalDate start, LocalDate end, int targetDailyHours){
        List<ChapterEntry> pool = new ArrayList<>();
        for(Subject subject : subjects) {
            for(Chapter chapter : subject.getChapters()) {
                pool.add(new ChapterEntry(subject, chapter,
                        chapter.getTotalWorkHours()));
            }
        }
        Collections.shuffle(pool, random);

        List<ScheduledSession> sessions = new ArrayList<>();
        LocalDate date = start;

        while(!pool.isEmpty() && !date.isAfter(end)){

            int subjectsToday = random.nextBoolean() ? 2 : 3;
            subjectsToday = Math.min(subjectsToday, pool.size());

            List<ChapterEntry> today = pickRandomDistinct(pool, subjectsToday);

            int remainingHoursToday = targetDailyHours;

            int idx = 0;
            while (remainingHoursToday > 0 && !today.isEmpty()) {

                ChapterEntry entry = today.get(idx % today.size());
                int chunk = Math.min(remainingHoursToday, Math.min(2, entry.remainingHours));

                sessions.add(new ScheduledSession( entry.subject, entry.chapter, date, chunk));

                entry.remainingHours -= chunk;
                remainingHoursToday -= chunk;

                if(entry.remainingHours <= 0){
                    pool.remove(entry);
                    today.remove(entry);
                    if(today.isEmpty())break;
                    idx = 0;
                } 
                else{
                    idx++;
                }
            }
            date = date.plusDays(1);
        }

        return sessions;
    }
    private List<ChapterEntry> pickRandomDistinct(List<ChapterEntry> pool, int n) {
        List<ChapterEntry> copy = new ArrayList<>(pool);
        Collections.shuffle(copy, random);
        return new ArrayList<>(copy.subList(0, n));
    }
}
