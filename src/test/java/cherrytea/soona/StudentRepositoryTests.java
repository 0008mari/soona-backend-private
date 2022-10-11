package cherrytea.soona;

import cherrytea.soona.dto.StudentForm;
import cherrytea.soona.domain.Student;
import cherrytea.soona.repository.StudentRepository;
import cherrytea.soona.service.StudentService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class StudentRepositoryTests {

    @Autowired
    StudentService studentService;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    EntityManager em;

    @Test
    public void getStudents() throws Exception {
        List<Student> st = studentService.findStudents();
        System.out.println("st = " + st);
    }


    @Test
    public void 학생정보수정() throws Exception {
        //given
        Student student = new Student();
        student.setStuName("김칠칠");
        UUID savedId = studentRepository.save(student);
        String beforeName = student.getStuName();

        //when
        StudentForm studentForm = new StudentForm(
                UUID.fromString("1629daf4-846c-40e2-8684-ccea340e5e17"),
                "김팔팔",
                1,
                1,
                1,
                "010-1111-1111",
                "010-1111-1111",
                "",
                student.getSchool(),
                true
        );
        studentService.updateStudent(savedId, studentForm);
        String afterName = student.getStuName();

        //then
        Assertions.assertThat(beforeName).isNotEqualTo(afterName);
    }

}
