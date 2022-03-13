package com.greatlearning.smsapp.service;

import com.greatlearning.smsapp.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentServiceimpl implements StudentService {

    private SessionFactory sessionFactory;
    private Session session;

    public Student findById(int id) {
        Transaction tx = session.beginTransaction();
        Student student = session.get(Student.class,id);

        tx.commit();
        return student;
    }
    public StudentServiceimpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.session = this.sessionFactory.openSession();
    }

    public void save(Student student) {
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(student);
        tx.commit();
    }
    public List<Student> findAll() {

        Transaction tx = session.beginTransaction();

        List<Student> students = session.createQuery("from Student",Student.class).list();
        tx.commit();
        return students;
    }

    public void deleteById(int id) {
        Transaction tx = session.beginTransaction();

        try {
            Student student = session.get(Student.class, id);
            session.delete(student);
        } finally {
            tx.commit();
        }

    }
}
