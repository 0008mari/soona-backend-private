package cherrytea.soona;

import cherrytea.soona.domain.*;
import cherrytea.soona.repository.StudentMemoRepository;
import cherrytea.soona.repository.StudentRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
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
public class StudentMemoTests {

    @Autowired
    EntityManager em;

    @Autowired
    StudentMemoRepository studentMemoRepository;

    @Autowired
    StudentRepository studentRepository;

    @Test
    public void getStudentMemoTest() {

        // given
        UUID id1 = UUID.fromString("ae9a2fde-7661-49d1-8c09-67667a7cc9b5");
        UUID id2 = UUID.fromString("3ba7e648-fa0f-4a23-86ab-d5b52c2c4973");

        StudentMemo memo = new StudentMemo();
        Student student = new Student();
        student.setStuName("김칠칠");

        memo.setStudent(student);

        memo.setTeacherId(id2);
        memo.setContent("메모내용 엘렐레");
        memo.setMemoDate(LocalDateTime.now());

        UUID savedStudentId = studentRepository.save(student);
        UUID savedID = studentMemoRepository.save(memo);

        QStudentMemo memo2 = QStudentMemo.studentMemo;
        JPAQueryFactory query = new JPAQueryFactory(em);

        // when
        List<StudentMemo> result = query
                .selectFrom(memo2)
                .where(memo2.student.id.eq(savedStudentId),memo2.teacherId.eq(id2))
                .fetch();

        // then
        for (StudentMemo memo3 : result) {
            System.out.println("memo = " + memo3.getContent());
        }

        System.out.println("result = " + result);
        Assertions.assertThat(result.get(0).getId()).isEqualTo(savedID);
    }
}
