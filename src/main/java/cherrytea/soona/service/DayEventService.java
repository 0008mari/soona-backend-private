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

    // 오픈 API 콜
    // 뭐가 있어야 되냐면 콜 하는 거랑 init 하는게 따로 있어야 한다. ...

}
