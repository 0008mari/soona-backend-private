package cherrytea.soona.service;

import cherrytea.soona.domain.Event;
import cherrytea.soona.domain.Lecture;
import cherrytea.soona.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DayEventService {
    private final EventRepository eventRepository;

    @Transactional
    public UUID saveEvent(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> findByTeacher(UUID teacherId) {
        return eventRepository.findByTeacher(teacherId);
    }

    public List<Event> findByLecture(UUID lectureId){
        return eventRepository.findByLecture(lectureId);
    }

    public void deleteEventByLecture(UUID lectureId){
        // lecture 에 딸린 이벤트 조회
        List<Event> events = findByLecture(lectureId);
        // 여러개 모두 삭제
        for (Event event : events) {
            eventRepository.deleteById(event.getId());
        }
    }

}
