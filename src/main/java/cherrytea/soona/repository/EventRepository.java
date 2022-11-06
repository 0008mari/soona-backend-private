package cherrytea.soona.repository;

import cherrytea.soona.domain.Event;
import cherrytea.soona.domain.QEvent;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class EventRepository {

    private final EntityManager em;

    public UUID save(Event event) {
        if (event.getId() == null) {
            em.persist(event);
        } else {
            em.merge(event);
        }
        return event.getId();
    }

    public List<Event> findByTeacher(UUID teacherId){
        QEvent event = QEvent.event;

        JPAQueryFactory query = new JPAQueryFactory(em);
        List<Event> result = query
                .selectFrom(event)
                .where(event.teacherId.eq(teacherId))
                .fetch();
        return result;
    }

    public List<Event> findByLecture(UUID lectureId) {
        QEvent event = QEvent.event;
        JPAQueryFactory query = new JPAQueryFactory(em);
        List<Event> result = query
                .selectFrom(event)
                .where(event.lectureId.eq(lectureId))
                .fetch();
        return result;
    }

    public Event findById(UUID id) {
        return em.find(Event.class, id);
    }

    @Transactional
    public void deleteById(UUID id) {
        Event event = findById(id);
        if (event == null) {
            return;
        }

        if (em.contains(event)) {
            em.remove(event);
        } else {
            em.merge(event);
            em.remove(event);
        }
    }

}
