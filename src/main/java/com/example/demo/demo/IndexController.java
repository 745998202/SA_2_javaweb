package com.example.demo.demo;

import com.example.demo.demo.Bean.ClassAvgBean;
import com.example.demo.demo.Bean.CourseBean;
import com.example.demo.demo.Bean.StudentBean;
import com.example.demo.demo.Bean.Student_CourseBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import javax.sql.RowSetInternal;
import java.awt.dnd.DragGestureEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {
    //内置方法，返回连接对象
    private static Connection getConn() {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/SC?serverTimezone=GMT%2B8";
        String username = "root";
        String password = "Yaomengjie2";
        Connection conn = null;
        try {
            Class.forName(driver); //classLoader,加载对应驱动
            conn =  DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
        public String index(ModelMap map){
           Student_CourseBean sc = new Student_CourseBean("0","0","0");
           map.put("sc",sc);
            return "/test";
        }

    @RequestMapping("/Student")
    public String Student(WebRequest webRequest){
        List<StudentBean> students = new ArrayList<>();
        Connection conn = getConn();
        String sql = "select * from student";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while(rs.next()){
                String sno = rs.getString("sno");
                String sname = rs.getString("sname");
                String grade = rs.getString("grade");
                StudentBean student = new StudentBean(sno,sname,grade);
                students.add(student);
            }
            pre.close();
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        //设置上下文为一个请求
        webRequest.setAttribute("students",students, RequestAttributes.SCOPE_REQUEST);
        return "/Student";
    }
    @RequestMapping("/Student_Course")
    public String Student_Course(WebRequest webRequest){
        List<Student_CourseBean> scs = new ArrayList<>();
        Connection conn = getConn();
        String sql = "select * from student_course order by sno";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while(rs.next()){
                String sno = rs.getString("sno");
                String cno = rs.getString("cno");
                String score = rs.getString("score");
                Student_CourseBean sc = new Student_CourseBean(sno,cno,score);
                scs.add(sc);
            }
            pre.close();
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        //设置上下文为一个请求
        webRequest.setAttribute("scs",scs, RequestAttributes.SCOPE_REQUEST);
        return "/Student_Course";
    }
    @RequestMapping("/Course")
    public String Course(WebRequest webRequest){
        List<CourseBean> courses = new ArrayList<>();
        Connection conn = getConn();
        String sql = "select * from course";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while(rs.next()){
                String cno = rs.getString("cno");
                String cname = rs.getString("cname");
                String teacher = rs.getString("teacher");
                CourseBean course = new CourseBean(cno,cname,teacher);
                courses.add(course);
            }
            pre.close();
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        //设置上下文为一个请求
        webRequest.setAttribute("courses",courses, RequestAttributes.SCOPE_REQUEST);
        return "/Course";
    }
    @RequestMapping("/result")
    public String result(@ModelAttribute Student_CourseBean sc,WebRequest webRequest){
        String sno = sc.getSno();
        String cno = sc.getCno();
        String sql = "select score from student_course where sno = '"+sno +"'and cno = '"+cno+"'";
        Connection conn = getConn();
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while(rs.next()){
                String score = rs.getString("score");
                sc.setScore(score);
            }
            pre.close();
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        webRequest.setAttribute("sc",sc,RequestAttributes.SCOPE_REQUEST);
        return "/result";
    }

    @RequestMapping("/AVG")
    public  String getAvg(WebRequest webRequest){
        List<ClassAvgBean> grades = new ArrayList<>();
        Connection conn = getConn();
        String sql = "call getAvg()";
        try {
            CallableStatement pre = conn.prepareCall(sql);
            ResultSet rs = pre.executeQuery();
            while(rs.next()){
                String grade = rs.getString("班级");
                String score = rs.getString("平均成绩");
                ClassAvgBean mygrade = new ClassAvgBean(grade,score);
                grades.add(mygrade);
            }
            pre.close();
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        //设置上下文为一个请求
        webRequest.setAttribute("grades",grades, RequestAttributes.SCOPE_REQUEST);
        return "/AVG";
    }

}
