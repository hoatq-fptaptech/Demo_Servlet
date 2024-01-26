package com.student;

import entities.Student;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/student")
public class StudentControl extends HttpServlet {
    private SessionFactory sessionFactory;

    @Override
    public void init() throws ServletException {
        try {
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Query db
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<Student> ls = session.createQuery("FROM Student", Student.class).getResultList();
            // Kết thúc giao dịch
            session.getTransaction().commit();
            // end
            req.setAttribute("students",ls);
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("student/form.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dataToInsert = req.getParameter("email");

        // Tạo một entity và thiết lập giá trị
        Student entity = new Student();
        entity.setEmail(dataToInsert);

        try (Session session = sessionFactory.openSession()) {
            // Bắt đầu một giao dịch
            session.beginTransaction();
            // Chèn dữ liệu
            session.save(entity);
            // Kết thúc giao dịch
            session.getTransaction().commit();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String entityId = req.getParameter("entityId");
        String newData = req.getParameter("newData");
        try (Session session = sessionFactory.openSession()) {
            // Bắt đầu một giao dịch
            session.beginTransaction();
            // Lấy entity cần cập nhật từ cơ sở dữ liệu
            Student entityToUpdate = session.get(Student.class, Integer.parseInt(entityId));
            // Kiểm tra xem entity có tồn tại không
            if (entityToUpdate != null) {
                // Cập nhật dữ liệu mới
                entityToUpdate.setEmail(newData);
                // Cập nhật entity
                session.update(entityToUpdate);
            }
            // Kết thúc giao dịch
            session.getTransaction().commit();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String entityId = req.getParameter("entityId");
        try (Session session = sessionFactory.openSession()) {
            // Bắt đầu một giao dịch
            session.beginTransaction();
            Student delEntity = session.get(Student.class,Integer.parseInt(entityId));
            if(delEntity != null){
                session.delete(delEntity);
            }
            session.getTransaction().commit();
        }
    }
}
