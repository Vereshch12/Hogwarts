package com.example.hogwarts.service;

import com.example.hogwarts.model.Faculty;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    private final HashMap<Long, Faculty> allFaclties = new HashMap<>();
    private long lastId = 0;

    public Faculty createFaculty (Faculty faculty){
        faculty.setId(++lastId);
        allFaclties.put(faculty.getId(), faculty);
        return faculty;
    }

    public Faculty findFaculty(long id){
        return allFaclties.get(id);
    }

    public Faculty editFaculty(Faculty faculty){
        if (allFaclties.containsKey(faculty.getId())){
            allFaclties.put(faculty.getId(), faculty);
            return faculty;
        }
        return null;
    }

    public Faculty deleteFaculty(long id){
        return allFaclties.remove(id);
    }

    public Collection<Faculty> getAllFaculties (){
        return allFaclties.values();
    }

    public List<Faculty> getFacultiessByColor (String color){
        return allFaclties.values().stream()
                .filter(f -> f.getColor() == color)
                .collect(Collectors.toList());
    }
}
