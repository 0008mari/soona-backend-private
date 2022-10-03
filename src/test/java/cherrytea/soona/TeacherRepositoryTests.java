package cherrytea.soona;

import cherrytea.soona.controller.TeacherController;
import cherrytea.soona.domain.Lecture;
import cherrytea.soona.domain.Teacher;
import cherrytea.soona.dto.teacher.RegisterForm;
import cherrytea.soona.service.LectureService;
import cherrytea.soona.service.TeacherService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TeacherRepositoryTests {


    @Autowired EntityManager em;

    @Autowired TeacherService teacherService;
    @Autowired LectureService lectureService;


    @Test
    public void getLectures() throws Exception {
        //given
        RegisterForm teacher = new RegisterForm("username", "password", "김영영");
        UUID savedTId = teacherService.registerTeacher(teacher);
        Teacher savedTeacher = teacherService.findById(savedTId);

        Lecture lecture = new Lecture();
        lecture.setTeacher(savedTeacher);
        lecture.setLecDate(LocalDateTime.now());
        UUID savedLectureId = lectureService.saveLecture(lecture);

        //when
        List<Lecture> foundLecture = savedTeacher.getLectures();
        System.out.println("foundLecture = " + foundLecture);

        //then
        Assertions.assertThat(foundLecture.get(0)).isEqualTo(savedLectureId);


         }

}
