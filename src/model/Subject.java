package model;

import java.util.ArrayList;
import java.util.List;

public class Subject{
    private final String name;
    private final List<Chapter> chapters = new ArrayList<>();

    public Subject(String name){ this.name = name; }

    public String getName(){ 
        return name; 
    }

    public List<Chapter> getChapters(){ 
        return chapters; 
    }

    public void addChapter(Chapter c){ 
        chapters.add(c); 
    }

    @Override
    public String toString(){
        return name + " (" + chapters.size() + " chapters)";
    }
}
