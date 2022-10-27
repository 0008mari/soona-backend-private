package cherrytea.soona.service;

import cherrytea.soona.domain.Student;
import cherrytea.soona.domain.Teacher;
import cherrytea.soona.dto.LectureForm;
import cherrytea.soona.domain.Event;
import cherrytea.soona.domain.Lecture;
import cherrytea.soona.repository.EventRepository;
import cherrytea.soona.repository.LectureRepository;
import cherrytea.soona.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;
    private final EventRepository eventRepository;
    private final TeacherRepository teacherRepository;
    private final LectureRollService lectureRollService;

    @Transactional
    public UUID saveLecture(LectureForm lectureForm) {

        Teacher teacher = teacherRepository.findById(lectureForm.getTeacherId());
        Lecture lecture = lectureFormToLecture(lectureForm);
        lecture.setTeacher(teacher);

        UUID savedId = lectureRepository.save(lecture);
        // lectureToNewEvent(lecture);
        return savedId;
    }

    public List<Lecture> findLectures() {
        return lectureRepository.findAll();
    }

    public Lecture findById(UUID id) {
        return lectureRepository.findById(id);
    }

    @Transactional
    public void updateLecture(UUID id, LectureForm lectureForm){
        Teacher teacher = teacherRepository.findById(lectureForm.getTeacherId());
        Lecture lecture = lectureFormToLecture(lectureForm);
        lecture.setId(id);
        lecture.setTeacher(teacher);
        lectureRepository.save(lecture);
        lectureToNewEvent(lecture);
    }

    public void deleteById(UUID id){
        lectureRepository.deleteById(id);
    }

    // 비즈니스 로직
    @Transactional
    public void lectureToNewEvent(Lecture lecture) {
        // save
        Event event = new Event();
        event.setType(0);
        event.setTeacherId(lecture.getTeacher().getId());
        event.setLectureId(lecture.getId());
        event.setStartDate(lecture.getLecDate());

        List<Student> students = lectureRollService.findStudentsByLectureId(lecture.getId());

        StringBuilder sb = new StringBuilder();
        List<String> nameList = students.stream().map(Student::getStuName).collect(Collectors.toList());
        sb.append(String.join(", ", nameList)).append(" 수업");
        event.setEventName(sb.toString());

        int lectureDuration = lecture.getLecTime();
        event.setEndDate(lecture.getLecDate().plusHours(lectureDuration));

        eventRepository.save(event);
    }

    public Lecture lectureFormToLecture(LectureForm lectureForm){

        Lecture lecture = new Lecture();
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

        return lecture;
    }

}
