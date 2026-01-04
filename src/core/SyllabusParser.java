package core;

import model.Subject;
import model.Chapter;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class SyllabusParser{

    public List<Subject> parse(Path path) throws IOException{

        List<Subject> subjects = new ArrayList<>();
        Subject currentSubject = null;

        for(String line : Files.readAllLines(path)){

            line = line.trim();
            if(line.isEmpty())continue;

            if(line.endsWith(":")){
                String subjectName = line.substring(0, line.length() - 1);
                currentSubject = new Subject(subjectName);
                subjects.add(currentSubject);
                continue;
            }

            if (line.contains("(") && line.contains(")") && currentSubject != null) {
                int openIndex = line.indexOf("(");
                int closeIndex = line.lastIndexOf(")");
                String chapterName = line.substring(openIndex + 1, closeIndex);
                currentSubject.addChapter(new Chapter(chapterName));
            }
        }

        return subjects;
    }
}
