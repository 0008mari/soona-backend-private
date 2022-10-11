package cherrytea.soona.repository;

import cherrytea.soona.domain.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class StudentRepository {

    private final EntityManager em;

    public UUID save(Student student) {
        em.persist(student);
//        if (student.getId() == null) {
//            em.persist(student);
//        } else {
//            em.merge(student);
//        }
        return student.getId();
    }

    public Student findById(UUID id) {
        return em.find(Student.class, id);
    }

    public List<Student> findAll() {
        return em.createQuery("select m from Student m", Student.class)
                .getResultList();
    }

    @Transactional
    public void deleteById(UUID id) {
        // 조회
        Student student = findById(id);
        if (student == null) {
            return;
        }

        // em 에서 삭제.
        if (em.contains(student)) {
            em.remove(student);
        } else {
            em.merge(student);
            em.remove(student);
        }
    }
}
