package com.example.hogwarts;

import com.example.hogwarts.controller.StudentController;
import com.example.hogwarts.model.Faculty;
import com.example.hogwarts.model.Student;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class StudentTest {
    @LocalServerPort
    private int port;

    @Autowired
    StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    void contextLoads(){
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    void createStudentTest()  {
        Student student = new Student();
        student.setId(1L);
        student.setName("mfn");
        student.setAge(13);

        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/students", student, String.class))
                .isNotNull();
    }

    @Test
    void getAllTest() {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students", String.class))
                .isNotNull();
    }

    @Test
    void editStudentTest() {
        Student student = new Student();
        student.setId(1L);
        student.setName("harry");
        student.setAge(12);

        restTemplate.put("http://localhost:" + port + "/students", student, String.class);

        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students/1",  String.class)).isNotEqualTo(student);
    }

    @Test
    void deleteStudentTest() throws Exception {
        Student student = new Student();
        student.setId(1L);
        student.setName("1");
        student.setAge(12);

        restTemplate.put("http://localhost:" + port + "/students", student, String.class);
        restTemplate.delete("http://localhost:" + port + "/students/1", student, String.class);

        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students/1",  String.class)).isNull();
    }

    @Test
    void GetStudentTest() {
        int one = 21;
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/" + one, Student.class))
                .isNotNull();
    }

    @Test
    void findByAgeTest() {
        long age = 15;
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students/filter/" + age,  String.class)).isNotNull();
    }

    @Test
    void getStudentByAgeBetweenTest() throws Exception {
        long age1 = 10;
        long age2 = 15;
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students/ageBetween/" + age1 + age2,  String.class))
                .isNotNull();
    }

    @Test
    void getFacultyTest() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setId(2L);
        faculty.setName("1");
        faculty.setColor("12");
        restTemplate.put("http://localhost:" + port + "/faculties", faculty, String.class);

        Student student = new Student();
        student.setId(1L);
        student.setName("1");
        student.setAge(15);
        restTemplate.put("http://localhost:" + port + "/students", student, String.class);

        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students/faculty/1",  String.class))
                .isNotNull();

    }
}
