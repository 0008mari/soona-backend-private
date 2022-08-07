package cherrytea.soona.repository;

import cherrytea.soona.domain.Lecture;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class LectureRepository {

    private final EntityManager em;

    public UUID save(Lecture lecture) {
        if (lecture.getId() == null){
            em.persist(lecture);
        } else {
            em.merge(lecture);
        }
        return lecture.getId();
    }

    public Lecture findById(UUID id) {
        return em.find(Lecture.class, id);
    }

    public List<Lecture> findAll() {
        return em.createQuery("select m from Lecture m", Lecture.class)
                .getResultList();
    }


    @Transactional
    public void deleteById(UUID id) {

        // 조회
        Lecture lecture = findById(id);

        // 널체크
        if (lecture == null) {
            return;
        }

        // em에서 삭제
        if (em.contains(lecture)) {
            em.remove(lecture);
        } else {
            em.merge(lecture);
            em.remove(lecture);
        }
    }

}
