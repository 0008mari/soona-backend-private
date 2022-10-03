package cherrytea.soona.repository;

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

    public List<Teacher> findByUsername(String username) {
        QTeacher teacher = QTeacher.teacher;

        JPAQueryFactory query = new JPAQueryFactory(em);
        List<Teacher> result = query
                .selectFrom(teacher)
                .where(teacher.username.eq(username))
                .fetch();

        return result;
    }


}
