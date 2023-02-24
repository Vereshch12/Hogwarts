package com.example.hogwarts.service;

import com.example.hogwarts.model.Student;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final HashMap<Long, Student> allStudents = new HashMap<>();
    private long lastId = 0;

    public Student createStudent (Student student){
        student.setId(++lastId);
        allStudents.put(student.getId(), student);
        return student;
    }

    public Student findStudent(long id){
        return allStudents.get(id);
    }

    public Student editStudent(Student student){
        if (allStudents.containsKey(student.getId())){
            allStudents.put(student.getId(), student);
            return student;
        }
        return null;
    }

    public Student deleteStudent(long id){
        return allStudents.remove(id);
    }

    public Collection<Student> getAllStudents (){
        return allStudents.values();
    }

    public List<Student> getStudentsByAge (int age){
        return allStudents.values().stream()
                .filter(s -> s.getAge() == age)
                .collect(Collectors.toList());
    }
}
