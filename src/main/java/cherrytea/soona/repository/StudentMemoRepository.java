package cherrytea.soona.repository;

import cherrytea.soona.domain.QStudentMemo;
import cherrytea.soona.domain.Student;
import cherrytea.soona.domain.StudentMemo;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class StudentMemoRepository {

    private final EntityManager em;

    public UUID save(StudentMemo studentMemo) {
        if (studentMemo.getId() == null) {
            em.persist(studentMemo);
        } else {
            em.merge(studentMemo);
        }
        return studentMemo.getId();
    }


    public List<StudentMemo> findByTeacherStudent(UUID teacherId, UUID studentId) {
        QStudentMemo memo = QStudentMemo.studentMemo;

        JPAQueryFactory query = new JPAQueryFactory(em);
        List<StudentMemo> result = query
                .selectFrom(memo)
                .where(memo.student.id.eq(studentId))
                .fetch();
        return result;
    }

    @Transactional
    public StudentMemo findById(UUID id) {
        return em.find(StudentMemo.class, id);
    }

    @Transactional
    public void deleteById(UUID id) {
        StudentMemo memo = findById(id);
        if (memo == null) {
            return;
        }

        if (em.contains(memo)) {
            em.remove(memo);
        } else {
            em.merge(memo);
            em.remove(memo);
        }
    }
}
