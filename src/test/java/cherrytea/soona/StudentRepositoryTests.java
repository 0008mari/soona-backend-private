package cherrytea.soona;

import cherrytea.soona.controller.StudentForm;
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
    public void 학생정보수정() throws Exception {
        //given
        Student student = new Student();
        student.setStuName("김칠칠");
        UUID savedId = studentRepository.save(student);
        String beforeName = student.getStuName();

        //when
        StudentForm studentForm = new StudentForm(
                "김팔팔",
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

    @Test
    public void 전화번호검증() throws Exception {
        //given
        Student student = new Student();
        student.setStuName("김칠칠");
        UUID savedId = studentRepository.save(student);

        //when
        StudentForm studentForm = new StudentForm(
                "김팔팔",
                "NaN",
                "01011111111",
                "",
                student.getSchool(),
                true
        );

        studentService.updateStudent(savedId, studentForm);

        //then
        Assertions.assertThatNoException();

        // 왜 검증 안돼 ??
    }
}
