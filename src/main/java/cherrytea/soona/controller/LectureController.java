package cherrytea.soona.controller;

import cherrytea.soona.domain.Lecture;
import cherrytea.soona.dto.LectureForm;
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

    @PostMapping("/lecture")
    @ApiOperation(value = "수업 추가", notes = "swagger에서 테스트 시 입력 칸에서 id를 지워주셔야 정상 작동 됩니다.")
    public UUID addLecture(@RequestBody LectureForm lectureForm){
        return lectureService.saveLecture(lectureForm);
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

    @PutMapping("/lecture/{id}")
    @ApiOperation(value = "수업 수정", notes = "")
    public void updateLecture(@PathVariable("id") UUID id,
                              @RequestBody LectureForm lectureForm) {
        lectureService.updateLecture(id, lectureForm);
    }

    @DeleteMapping("/lecture/{id}")
    public void deleteLecture(@PathVariable("id") UUID id) {
        lectureService.deleteById(id);
    }

}
