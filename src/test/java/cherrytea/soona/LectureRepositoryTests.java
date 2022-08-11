package cherrytea.soona;

import cherrytea.soona.domain.Lecture;
import cherrytea.soona.repository.LectureRepository;
import cherrytea.soona.service.LectureService;
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
public class LectureRepositoryTests {

    @Autowired LectureService lectureService;
    @Autowired LectureRepository lectureRepository;
    @Autowired EntityManager em;

    @Test
    public void addLecture() throws Exception {
        //given
        Lecture lecture = new Lecture();
        lecture.setEvaluation("999점");

        //when
        UUID savedId = lectureService.saveLecture(lecture);

        //then
        Assertions.assertThat(lecture).isEqualTo(lectureRepository.findById(savedId));
     }

    @Test
    public void deleteLecture() throws Exception {
        //given
        Lecture lecture = new Lecture();
        lecture.setContent("오마에와 모 신데이루 .");
        lecture.setEvaluation("10점");
        UUID deleteId = lectureService.saveLecture(lecture);

        em.flush();
        em.clear();

        lectureRepository.deleteById(deleteId);
        Assertions.assertThat(lectureRepository.findById(deleteId)).isNull();

    }

}
