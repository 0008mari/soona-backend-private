package cherrytea.soona;

import cherrytea.soona.controller.LectureController;
import cherrytea.soona.domain.LectureRoll;
import cherrytea.soona.repository.LectureRollRepository;
import cherrytea.soona.repository.StudentRepository;
import cherrytea.soona.service.LectureRollService;
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
public class LectureRollRepositoryTests {

    @Autowired
    LectureRollService lectureRollService;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    LectureRollRepository lectureRollRepository;
    @Autowired
    EntityManager em;
    @Autowired
    LectureController lectureController;

    @Test
    public void createLecRoll() throws Exception {
        // given
        LectureRoll lectureRoll = new LectureRoll();
        lectureRoll.setLectureId(UUID.fromString("1629daf4-846c-40e2-8684-ccea340e5e17"));
        lectureRoll.setStudentId(UUID.fromString("a85dea11-fa76-463c-8be2-facd9759870a"));
        Long savedId = lectureRollService.saveLectureRoll(lectureRoll);

        // when
        
        //then
        System.out.println("lectureRoll = " + lectureRoll);
     }

//     @Test
//     public void 렉처롤생성2() throws Exception {
//         //given
//         LectureWithStudentsForm form = new LectureWithStudentsForm(
//                 UUID.fromString("1629daf4-846c-40e2-8684-ccea340e5e17"),
//                 "영어",
//                 LocalDateTime.now(),
//                 90,
//                 0,
//                 "수업내용",
//                 5,
//                 "숙제없음",
//                 "메모없음",
//                 List.of(UUID.fromString("480ffe3b-523a-4a1e-82ab-06897414fa5d"), UUID.fromString("3ba7e648-fa0f-4a23-86ab-d5b52c2c4973"))
//                 );
//         UUID savedId = lectureController.addLectureWithStudents(form);
//      }
}
