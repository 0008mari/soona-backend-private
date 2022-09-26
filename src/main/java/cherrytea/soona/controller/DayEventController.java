package cherrytea.soona.controller;

import cherrytea.soona.domain.Event;
import cherrytea.soona.domain.Lecture;
import cherrytea.soona.repository.EventRepository;
import cherrytea.soona.service.DayEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class DayEventController {

    private final DayEventService dayEventService;

    @PostMapping("/dayEvent")
    public UUID saveEvent(Event event) {
        return dayEventService.saveEvent(event);
    }

    @GetMapping("/dayEvent")
    public List<Event> getEventByTeacher(@RequestParam("teacher") UUID teacherId) {
        return dayEventService.findByTeacher(teacherId);
    }


}
