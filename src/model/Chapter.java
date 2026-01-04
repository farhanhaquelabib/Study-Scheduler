package model;

public class Chapter{
    private final String title;
    private int defaultDays = 3;
    private Integer customDays = null;
    private int estimatedHoursPerDay = 2;

    public Chapter(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public int getDays(){
        return (customDays != null) ? customDays : defaultDays;
    }

    public void setCustomDays(int days){ 
        this.customDays = days; 
    }

    public int getEstimatedHoursPerDay(){ 
        return estimatedHoursPerDay; 
    }

    public void setEstimatedHoursPerDay(int hours){
        this.estimatedHoursPerDay = Math.max(1, hours);
    }

    public int getTotalWorkHours(){
        return getDays() * estimatedHoursPerDay;
    }

    @Override
    public String toString() {
        return title + " (" + getDays() + " days, " + getEstimatedHoursPerDay() + "h/day)";
    }
}
