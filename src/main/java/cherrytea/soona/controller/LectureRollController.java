package cherrytea.soona.controller;

import cherrytea.soona.domain.Lecture;
import cherrytea.soona.domain.LectureRoll;
import cherrytea.soona.domain.Student;
import cherrytea.soona.dto.LectureRollForm;
import cherrytea.soona.service.LectureRollService;
import cherrytea.soona.service.LectureService;
import cherrytea.soona.service.StudentService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class LectureRollController {

    @Autowired
    ModelMapper modelMapper;
    private final LectureRollService lectureRollService;

    public Long addLectureRoll(LectureRollForm lectureRollForm) {
        ModelMapper modelMapper = new ModelMapper();
        LectureRoll lectureRoll = modelMapper.map(lectureRollForm, LectureRoll.class);
        return lectureRollService.saveLectureRoll(lectureRoll);
    }

    @GetMapping("/student/{id}/lectureTime")
    @ApiOperation(value = "해당 학생에게 가장 가까운 수업 남은 날짜", notes = "id는 학생의 id, 미래의 수업이 없으면 -1 반환.")
    public Integer getStudentLectureTimeById(@PathVariable("id") UUID id){
        return lectureRollService.getStudentLectureTimeById(id);
    }


}
