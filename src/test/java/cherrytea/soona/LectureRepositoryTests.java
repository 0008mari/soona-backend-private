package cherrytea.soona;

import cherrytea.soona.domain.Lecture;
import cherrytea.soona.domain.Teacher;
import cherrytea.soona.dto.LectureForm;
import cherrytea.soona.repository.LectureRepository;
import cherrytea.soona.repository.TeacherRepository;
import cherrytea.soona.service.LectureService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class LectureRepositoryTests {

    @Autowired LectureService lectureService;
    @Autowired LectureRepository lectureRepository;
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired EntityManager em;

    @Test
    public void addLecture() throws Exception {
        //given
        LectureForm lectureForm = new LectureForm();
        lectureForm.setContent("오늘의 알찬 수학수업");
        lectureForm.setEvaluation(4);
        lectureForm.setTeacherId(teacherRepository.save(new Teacher()));
        Lecture lecture = lectureService.lectureFormToLecture(lectureForm);
        em.persist(lecture);

        //when
        UUID savedId = lecture.getId();

        //then
        Assertions.assertThat(lecture).isEqualTo(lectureRepository.findById(savedId));
     }

    @Test
    public void deleteLecture() throws Exception {
        //given
        LectureForm lectureForm = new LectureForm();
        lectureForm.setContent("오늘의 알찬 수학수업");
        lectureForm.setEvaluation(4);
        lectureForm.setTeacherId(UUID.fromString("1629daf4-846c-40e2-8684-ccea340e5e17"));
        lectureForm.setLecTime(60);
        lectureForm.setLecDate(LocalDateTime.now());

        UUID deleteId = lectureService.saveLecture(lectureForm);

        em.flush();
        em.clear();

        lectureRepository.deleteById(deleteId);
        Assertions.assertThat(lectureRepository.findById(deleteId)).isNull();

    }

}
