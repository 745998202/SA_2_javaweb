package com.example.demo.demo.Bean;

public class CourseBean {
    private String cno;
    private String cname;
    private String teacher;
    public CourseBean(String cno,String cname,String teacher){
        this.cno = cno;
        this.cname = cname;
        this.teacher = teacher;
    }
    public void setCno(String cno){
        this.cno = cno;
    }
    public String getCno(){
        return cno;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
    public String getCname(){
        return cname;
    }
    public void setTeacher(String teacher){
        this.teacher = teacher;
    }
    public String getTeacher(){
        return teacher;
    }
}
