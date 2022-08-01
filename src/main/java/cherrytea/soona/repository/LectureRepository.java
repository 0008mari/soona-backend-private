package cherrytea.soona.repository;

import cherrytea.soona.domain.Lecture;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;

@Repository
public class LectureRepository {

    @PersistenceContext
    EntityManager em;

    public UUID save(Lecture lecture) {
        em.persist(lecture);
        return lecture.getId();
    }

    public Lecture find(UUID id) {
        return em.find(Lecture.class, id);
    }

    public List<Lecture> findAll() {
        return em.createQuery("select m from Lecture m", Lecture.class)
                .getResultList();
    }
}
