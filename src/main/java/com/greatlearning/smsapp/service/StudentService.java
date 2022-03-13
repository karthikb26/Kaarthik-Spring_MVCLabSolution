package com.greatlearning.smsapp.service;

import com.greatlearning.smsapp.entity.Student;

import java.util.List;

public interface StudentService  {
    public List<Student> findAll();

    public Student findById(int id);

    public void save(Student theStudent); // save or update

    public void deleteById(int id);
}
