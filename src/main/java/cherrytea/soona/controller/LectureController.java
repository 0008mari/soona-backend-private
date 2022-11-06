package cherrytea.soona.controller;

import cherrytea.soona.domain.Lecture;
import cherrytea.soona.domain.LectureRoll;
import cherrytea.soona.dto.LectureForm;
import cherrytea.soona.dto.LectureWithStudentsRequestForm;
import cherrytea.soona.dto.LectureWithStudentsResponseForm;
import cherrytea.soona.service.DayEventService;
import cherrytea.soona.service.LectureRollService;
import cherrytea.soona.service.LectureService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController // 리턴값에 자동으로 @ResponseBody 붙어서 HTTP json으로 나감.
@RequiredArgsConstructor
public class LectureController {

    @Autowired
    ModelMapper modelMapper;

    private final LectureService lectureService;
    private final LectureRollService lectureRollService;
    private final DayEventService dayEventService;

    @PostMapping("/lecture")
    @ApiOperation(value = "수업 추가", notes = "swagger에서 테스트 시 입력 칸에서 id를 지워주셔야 정상 작동 됩니다.")
    public UUID addLecture(@RequestBody LectureForm lectureForm){
        return lectureService.saveLecture(lectureForm);
    }

    @PostMapping("/lectureWithStudents")
    @ApiOperation(value = "수업 추가(학생과 함께하는 ver)", notes = "swagger에서 테스트 시 입력 칸에서 id를 지워주셔야 정상 작동 됩니다. 학생리스트는 [uuid, uuid, ...] 형태로 ")
    public UUID addLectureWithStudents(@RequestBody LectureWithStudentsRequestForm form){

        // lectureForm 분리해서 전달
        UUID savedId = lectureService.saveLecture(modelMapper.map(form, LectureForm.class));
        // students 에 대해 반복해서 lecture roll 생성
        for (UUID studentId : form.getStudentList()) {
            LectureRoll lectureRoll = new LectureRoll();
            lectureRoll.setLectureId(savedId);
            lectureRoll.setStudentId(studentId);
            Long savedId2 = lectureRollService.saveLectureRoll(lectureRoll);
        }
        Lecture savedLecture = lectureService.findById(savedId);
        // lecture 저장 및 lec_roll 저장 먼저 되고 그 다음에 event 호출
        lectureService.lectureToNewEvent(savedLecture);
        return savedId;
    }

    @GetMapping("/lectures")
    public List<LectureForm> getLectures() {
        List<Lecture> lectureList = lectureService.findLectures();
        return lectureList.stream().map(lecture -> modelMapper.map(lecture, LectureForm.class)).collect(Collectors.toList());
    }

    @GetMapping("/lecture/{id}")
    public LectureForm getLectureById(@PathVariable("id") UUID id) {
        return modelMapper.map(lectureService.findById(id), LectureForm.class);
    }

    @GetMapping("/lectureWithStudent/{id}")
    public LectureWithStudentsResponseForm getLectureWithStudentById(@PathVariable("id") UUID id) {
        LectureWithStudentsResponseForm form = modelMapper.map(lectureService.findById(id), LectureWithStudentsResponseForm.class);
        // 학생 설정
        List<UUID> studentIds = lectureRollService.findStudentsIdByLectureId(form.getLectureId());
        List<String> studentNames = lectureRollService.findStudentsNameByLectureId(form.getLectureId());
        form.setStudentList(studentIds);
        form.setStudentNameList(studentNames);
        return form;
    }

    @PutMapping("/lecture/{id}")
    @ApiOperation(value = "수업 수정")
    public void updateLecture(@PathVariable("id") UUID id,
                              @RequestBody LectureForm lectureForm) {
        lectureService.updateLecture(id, lectureForm);
    }

    @PutMapping("/lectureWithStudents/{id}")
    @ApiOperation(value = "수업수정 / 학생 UUID 리스트와 함께 - swagger를 믿지 마세요 그녀석 가짜 UUID를 제시함.")
    public void updateLectureWithStudents(@PathVariable("id") UUID id,
                                          @RequestBody LectureWithStudentsRequestForm form) {
        LectureForm lectureForm = modelMapper.map(form, LectureForm.class);
        dayEventService.deleteEventByLecture(id); // dayevent 삭제
        lectureService.updateLecture(id, lectureForm);
        // lectureRoll 수정
        lectureRollService.updateLectureRoll(id, form.getStudentList());
        lectureService.lectureToNewEvent(lectureService.findById(id)); // dayevent 다시만들기
    }

    @DeleteMapping("/lecture/{id}")
    public void deleteLecture(@PathVariable("id") UUID id) {
        lectureService.deleteById(id);
    }

}
