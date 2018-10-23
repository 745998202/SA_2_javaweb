package com.example.demo.demo.Bean;

public class StudentBean {
    private String sno;
    private String sname;
    private String grade;
    public StudentBean(String sno,String sname,String grade){
        this.sno = sno;
        this.sname = sname;
        this.grade = grade;
    }
    public void setSno(String sno){
        this.sno = sno;
    }
    public String getSno(){
        return sno;
    }
    public void setSname(String sname){
        this.sname = sname;
    }
    public String getSname(){
        return sname;
    }
    public void setGrade(String grade){
        this.grade = grade;
    }
    public String getGrade(){
        return grade;
    }
}
