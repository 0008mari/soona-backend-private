package cherrytea.soona.service;

import cherrytea.soona.controller.LectureForm;
import cherrytea.soona.domain.Event;
import cherrytea.soona.domain.Lecture;
import cherrytea.soona.repository.EventRepository;
import cherrytea.soona.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;
    private final EventRepository eventRepository;

    @Transactional
    public UUID saveLecture(Lecture lecture) {
        return lectureRepository.save(lecture);
    }

    public List<Lecture> findLectures() {
        return lectureRepository.findAll();
    }

    public Lecture findById(UUID id) {
        return lectureRepository.findById(id);
    }

    @Transactional
    public void updateLecture(UUID id, LectureForm lectureForm){
        Lecture lecture = lectureRepository.findById(id);

        if (lectureForm.getSubCode() != null) {
            lecture.setSubCode(lectureForm.getSubCode());
        }
        if (lectureForm.getLecDate() != null) {
            lecture.setLecDate(lectureForm.getLecDate());
        }
        if (lectureForm.getLecTime() != null) {
            lecture.setLecTime(lectureForm.getLecTime());
        }
        if (lectureForm.getType() != null) {
            lecture.setType(lectureForm.getType());
        }
        if (lectureForm.getContent() != null) {
            lecture.setContent(lectureForm.getContent());
        }
        if (lectureForm.getEvaluation() != null) {
            lecture.setEvaluation(lectureForm.getEvaluation());
        }
        if (lectureForm.getHomework() != null) {
            lecture.setHomework(lectureForm.getHomework());
        }
        if (lectureForm.getLecMemo() != null) {
            lecture.setLecMemo(lectureForm.getLecMemo());
        }

        lectureRepository.save(lecture);

    }

    public void deleteById(UUID id){
        lectureRepository.deleteById(id);
    }

    // 비즈니스 로직
    public void lectureToNewEvent(Lecture lecture) {
        // save
        Event event = new Event();
        event.setType(0);
        event.setTeacherId(lecture.getTeacher().getId());
        event.setLectureId(lecture.getId());
        event.setStartDate(lecture.getLecDate());
        int lectureDuration = lecture.getLecTime();
        event.setEndDate(lecture.getLecDate().plusHours(lectureDuration));

        eventRepository.save(event);
    }

    public void onLectureUpdate(Lecture lecture, Event event) {
        // 약간 더 고민이 필요
    }

}
