package com.example.demo.demo.Bean;

public class Student_CourseBean {
    private String sno;
    private String cno;
    private String score;
    public Student_CourseBean(String sno,String cno,String score){
        this.sno = sno;
        this.cno = cno;
        this.score = score;
    }
    public void setSno(String sno){
        this.sno = sno;
    }
    public String getSno(){
        return sno;
    }
    public void setCno(String cno){
        this.cno = cno;
    }
    public String getCno(){
        return cno;
    }
    public void setScore(String score){
        this.score = score;
    }
    public String getScore(){
        return score;
    }
}
