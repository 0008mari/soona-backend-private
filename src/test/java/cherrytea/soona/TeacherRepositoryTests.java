package cherrytea.soona;

import cherrytea.soona.controller.TeacherController;
import cherrytea.soona.domain.Lecture;
import cherrytea.soona.domain.Teacher;
import cherrytea.soona.dto.LectureForm;
import cherrytea.soona.dto.teacher.RegisterForm;
import cherrytea.soona.dto.teacher.WithdrawalForm;
import cherrytea.soona.repository.TeacherRepository;
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
    @Autowired
    TeacherRepository teacherRepository;


    @Test
    public void getLectures() throws Exception {
        //given
        RegisterForm teacher = new RegisterForm("username", "password", "김영영");
        UUID savedTId = teacherService.registerTeacher(teacher);
        Teacher savedTeacher = teacherService.findById(savedTId);

        LectureForm lectureForm = new LectureForm();
        lectureForm.setContent("오늘의 알찬 수학수업");
        lectureForm.setEvaluation(4);
        lectureForm.setTeacherId(savedTId);
        lectureForm.setLecTime(90);
        lectureForm.setLecDate(LocalDateTime.now());

        UUID savedLectureId = lectureService.saveLecture(lectureForm);

        //when
        List<Lecture> foundLecture = savedTeacher.getLectures();
        System.out.println("foundLecture = " + foundLecture);

        //then
        Assertions.assertThat(foundLecture.get(0).getId()).isEqualTo(savedLectureId);
    }

    @Test
    public void validTeacherTest() throws Exception {
        //given
        RegisterForm teacher = new RegisterForm("username", "password", "김영영");
        UUID savedTId = teacherService.registerTeacher(teacher);

        //when

        //then
        Assertions.assertThat(teacherService.isValidTeacherId(savedTId)).isTrue();
        Assertions.assertThat(teacherService.isValidTeacherId(UUID.randomUUID())).isFalse();

     }

    @Test
    public void 회원탈퇴() throws Exception {
        //given
        RegisterForm teacher = new RegisterForm("byebye", "1234", "곧탈퇴할사람");
        UUID savedTId = teacherService.registerTeacher(teacher);

        //when
            // UUID 존재하지 않는 경우
        Assertions.assertThat(teacherService.withdrawTeacher(new WithdrawalForm(UUID.randomUUID(), "1234"))).isFalse();
            // 비밀번호 틀린 경우
        Assertions.assertThat(teacherService.withdrawTeacher(new WithdrawalForm(savedTId, "."))).isFalse();

            // 탈퇴성공
        Assertions.assertThat(teacherService.withdrawTeacher(new WithdrawalForm(savedTId, "1234"))).isTrue();

        //then
        Assertions.assertThat(teacherService.isValidTeacherId(savedTId)).isFalse();
     }
}
