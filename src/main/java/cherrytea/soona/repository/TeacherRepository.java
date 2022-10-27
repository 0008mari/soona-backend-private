package cherrytea.soona.repository;

import cherrytea.soona.domain.QLecture;
import cherrytea.soona.domain.QStudent;
import cherrytea.soona.domain.QTeacher;
import cherrytea.soona.domain.Teacher;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class TeacherRepository {

    private final EntityManager em;

    @Transactional
    public UUID save(Teacher teacher) {
        em.persist(teacher);
        if (teacher.getId() == null) {
            em.persist(teacher);
        } else {
            em.merge(teacher);
        }

        return teacher.getId();
    }

    public Teacher findById(UUID id) {
        return em.find(Teacher.class, id);
    }

    public Teacher findByIdByFetchJoinLecture(UUID id) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QTeacher teacher = QTeacher.teacher;
        QLecture lecture = QLecture.lecture;

        return queryFactory.selectFrom(teacher).where(teacher.id.eq(id))
                .leftJoin(teacher.lectures, lecture).fetchJoin().fetchOne();
    }

    public Teacher findByIdByFetchJoinStudent(UUID id) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QTeacher teacher = QTeacher.teacher;
        QStudent student = QStudent.student;

        return queryFactory.selectFrom(teacher).where(teacher.id.eq(id))
                .leftJoin(teacher.students, student).fetchJoin().fetchOne();
    }

    public Teacher findByIdByFetchJoin(UUID id) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QTeacher teacher = QTeacher.teacher;
        QLecture lecture = QLecture.lecture;
        QStudent student = QStudent.student;

        var q1 = queryFactory.selectFrom(teacher)
                .where(teacher.id.eq(id)).leftJoin(teacher.lectures, lecture).fetchJoin();
        var q2 = queryFactory.selectFrom(teacher)
                .where(teacher.id.eq(id)).leftJoin(teacher.students, student).fetchJoin();
        var a1 = q1.fetchOne();
        var a2 = q2.fetchOne();

        return new Teacher();
    }

    public List<Teacher> findByUsername(String username) {
        QTeacher teacher = QTeacher.teacher;

        JPAQueryFactory query = new JPAQueryFactory(em);
//        List<Teacher> result = query
//                .selectFrom(teacher)
//                .where(teacher.username.eq(username))
//                .fetch();
        var q = query
                .selectFrom(teacher)
                .where(teacher.username.eq(username));
        var qq = q.fetch();

        return qq;
    }


}
