package cherrytea.soona;

import cherrytea.soona.domain.Lecture;
import cherrytea.soona.repository.LectureRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LectureRepositoryTest {

    @Autowired
    LectureRepository lectureRepository;

    @Test
    @Transactional
    @Rollback(value = true)
    public void testLecture() throws Exception {
        //given
        Lecture lecture = new Lecture();
        lecture.setEvaluation("10점");


        //when
        UUID savedId = lectureRepository.save(lecture);
        
        //then
        Lecture findLecture = lectureRepository.find(savedId);
        Assertions.assertThat(findLecture.getId()).isEqualTo(lecture.getId());
        Assertions.assertThat(findLecture.getEvaluation()).isEqualTo(lecture.getEvaluation());

    }
}
