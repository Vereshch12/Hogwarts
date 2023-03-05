package com.example.hogwarts.service;

import com.example.hogwarts.model.Student;
import com.example.hogwarts.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Value("${avatars.dir.path}")
    private String avatarsDir;

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        return studentRepository.findById(id).get();
    }

    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }

    public Student findStudentByAge(Long ageAfter, Long ageBefore) {
        return studentRepository.findByAgeBetween(ageAfter, ageBefore);
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

}
