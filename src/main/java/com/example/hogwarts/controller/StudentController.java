package com.example.hogwarts.controller;


import com.example.hogwarts.model.Faculty;
import com.example.hogwarts.model.Student;
import com.example.hogwarts.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("hogwarts/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentInfo(@PathVariable Long id){
        Student student = studentService.findStudent(id);
        if(student == null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student){
        return studentService.createStudent(student);
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student student){
        Student student1 = studentService.editStudent(student);
        if(student1 == null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student1);
    }

    @DeleteMapping("{id}")
    public Student deleteStudent(@PathVariable Long id){
        return studentService.deleteStudent(id);
    }

    @GetMapping
    public ResponseEntity<Collection<Student>> getAllStudents(){
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("{age}")
    public ResponseEntity<List<Student>> getAllFaciltiesByColor(@PathVariable int age){
        return ResponseEntity.ok(studentService.getStudentsByAge(age));
    }
}
