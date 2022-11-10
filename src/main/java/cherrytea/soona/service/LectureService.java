package cherrytea.soona.service;

import cherrytea.soona.domain.*;
import cherrytea.soona.dto.LectureForm;
import cherrytea.soona.dto.LectureWithStudentsRequestForm;
import cherrytea.soona.repository.EventRepository;
import cherrytea.soona.repository.LectureRepository;
import cherrytea.soona.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LectureService {

    @Autowired ModelMapper modelMapper;
    private final LectureRollService lectureRollService;
    private final DayEventService dayEventService;
    private final LectureRepository lectureRepository;
    private final EventRepository eventRepository;
    private final TeacherRepository teacherRepository;

    @Transactional
    public UUID saveLecture(LectureForm lectureForm) {

        Teacher teacher = teacherRepository.findById(lectureForm.getTeacherId());
        Lecture lecture = lectureFormToLecture(lectureForm);
        lecture.setTeacher(teacher);
        UUID savedId = lectureRepository.save(lecture);
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
        if (lectureRepository.findById(id).equals(null)){
            return;
        } else{
            Lecture lecture = lectureRepository.findById(id);
            lecture = lectureFormToLecture(lecture, lectureForm);
            lectureRepository.save(lecture);
            //lectureToNewEvent(lecture);
        }
    }

    @Transactional
    public void deleteById(UUID id){
        dayEventService.deleteEventByLecture(id);
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
        sb.append("[" + lecture.getSubCode() + "] ");
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

    public Lecture lectureFormToLecture(Lecture lecture, LectureForm lectureForm){

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

    @Transactional
    public void updateLectureWithStudents(UUID id, LectureWithStudentsRequestForm form) {
        LectureForm lectureForm = modelMapper.map(form, LectureForm.class);
        dayEventService.deleteEventByLecture(id); // dayevent 삭제
        updateLecture(id, lectureForm);
        // lectureRoll 수정
        lectureRollService.updateLectureRoll(id, form.getStudentList());
        lectureToNewEvent(findById(id)); // dayevent 다시만들기
    }

    @Transactional
    public UUID addLectureWithStudents(LectureWithStudentsRequestForm form) {
        // lectureForm 분리해서 전달
        UUID savedId = saveLecture(modelMapper.map(form, LectureForm.class));
        // students 에 대해 반복해서 lecture roll 생성
        for (UUID studentId : form.getStudentList()) {
            LectureRoll lectureRoll = new LectureRoll();
            lectureRoll.setLectureId(savedId);
            lectureRoll.setStudentId(studentId);
            Long savedId2 = lectureRollService.saveLectureRoll(lectureRoll);
        }
        Lecture savedLecture = findById(savedId);
        // lecture 저장 및 lec_roll 저장 먼저 되고 그 다음에 event 호출
        lectureToNewEvent(savedLecture);
        return savedId;
    }
}
