package com.example.hogwarts.repositories;

import com.example.hogwarts.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    Faculty findByNameIgnoreCaseOrColorIgnoreCase(String name, String color);
}
