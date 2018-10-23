package com.example.demo.demo.Bean;

public class ClassAvgBean {
    private String grade;
    private String score;
    public void setGrade(String grade){
        this.grade = grade;
    }
    public String getGrade(){
        return grade;
    }
    public void setScore(String score){
        this.score = score;
    }
    public String getScore(){
        return score;
    }
    public ClassAvgBean(String grade,String score){
        this.grade = grade;
        this.score = score;
    }
}
