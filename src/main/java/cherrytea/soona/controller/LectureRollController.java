package cherrytea.soona.controller;

import cherrytea.soona.domain.Lecture;
import cherrytea.soona.domain.LectureRoll;
import cherrytea.soona.domain.Student;
import cherrytea.soona.dto.LectureRollForm;
import cherrytea.soona.service.LectureRollService;
import cherrytea.soona.service.LectureService;
import cherrytea.soona.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

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
}
