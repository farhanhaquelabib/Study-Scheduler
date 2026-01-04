package model;

import java.time.LocalDate;

public class ScheduledSession{
    private final Subject subject;
    private final Chapter chapter;
    private final LocalDate date;
    private final int hours;

    public ScheduledSession(Subject subject, Chapter chapter, LocalDate date, int hours){
        this.subject = subject;
        this.chapter = chapter;
        this.date = date;
        this.hours = hours;
    }

    public Subject getSubject(){ 
        return subject; 
    }
    public Chapter getChapter(){ 
        return chapter; 
    }
    public LocalDate getDate(){ 
        return date; 
    }
    public int getHours(){
        return hours; 
    }

    @Override
    public String toString(){
        return date + " | " + subject.getName() + " - " + chapter.getTitle() + " (" + hours + "h)";
    }
}
