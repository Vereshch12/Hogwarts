package com.example.hogwarts.service;

import com.example.hogwarts.model.Student;
import com.example.hogwarts.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent (Student student){
        return studentRepository.save(student);
    }

    public Student findStudent(long id){
        return studentRepository.findById(id).get();
    }

    public Student editStudent(Student student){
        return studentRepository.save(student);
    }

    public void deleteStudent(long id){
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAllStudents (){
        return studentRepository.findAll();
    }

    public List<Student> getStudentsByAge (int age){
        Collection<Student> allStudents = studentRepository.findAll();
        return allStudents.stream()
                .filter(s -> s.getAge() == age)
                .collect(Collectors.toList());
    }
}
