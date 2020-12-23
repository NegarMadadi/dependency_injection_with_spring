package org.negar.service;

import org.negar.models.Student;

import java.util.List;

public interface StudentManagement {
    Student create();

    Student save(Student student);

    Student find(int id);

    boolean remove(int id);

    Student edit(Student student);

    List<Student> findAll();
}