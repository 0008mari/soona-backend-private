package cherrytea.soona.repository;

import cherrytea.soona.domain.LectureRoll;
import cherrytea.soona.domain.QStudent;
import cherrytea.soona.domain.QLectureRoll;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class LectureRollRepository {

    private final EntityManager em;

    /*** CRUD ***/
    @Transactional
    public Long save(LectureRoll lectureRoll) {
        if (lectureRoll.getId() == null){
            em.persist(lectureRoll);
        } else {
            em.merge(lectureRoll);
        }
        return lectureRoll.getId();
    }

    public LectureRoll findById(Long id) {
        return em.find(LectureRoll.class, id);
    }

    public List<LectureRoll> findAll() {
        return em.createQuery("select m from LectureRoll m", LectureRoll.class)
                .getResultList();
    }

    @Transactional
    public void deleteById(Long id) {

        LectureRoll lectureRoll = findById(id);
        if (lectureRoll == null) {
            return;
        }
        if (em.contains(lectureRoll)) {
            em.remove(lectureRoll);
        } else {
            em.merge(lectureRoll);
            em.remove(lectureRoll);
        }
    }

    /* 학생에게 속한 렉처롤 모두 조회 */
    public List<LectureRoll> findLectureRollsByStudentId(UUID studentId) {

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QLectureRoll lectureRoll = QLectureRoll.lectureRoll;
        QStudent student = QStudent.student;

        return queryFactory.selectFrom(lectureRoll)
                .where(lectureRoll.studentId.eq(studentId))
                .fetch();
    }

    /* 수업에 속한 렉처롤 모두 조회 */
    public List<LectureRoll> findLectureRollsByLectureId(UUID lectureId) {

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QLectureRoll lectureRoll = QLectureRoll.lectureRoll;
        QStudent student = QStudent.student;

        return queryFactory.selectFrom(lectureRoll)
                .where(lectureRoll.lectureId.eq(lectureId))
                .fetch();
    }
}
