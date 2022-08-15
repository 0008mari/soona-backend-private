package cherrytea.soona.controller;

import cherrytea.soona.domain.Lecture;
import cherrytea.soona.service.LectureService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController // 리턴값에 자동으로 @ResponseBody 붙어서 HTTP json으로 나감.
@RequiredArgsConstructor
public class LectureController {

    private final LectureService lectureService;

    @PostMapping("/lecture")
    @ApiOperation(value = "수업 추가", notes = "swagger에서 테스트 시 입력 칸에서 id를 지워주셔야 정상 작동 됩니다.")
    public UUID addLecture(@RequestBody Lecture lecture){
        System.out.println(lecture);
        return lectureService.saveLecture(lecture);
    }

    @GetMapping("/lectures")
    public List<Lecture> getLectures() {
        return lectureService.findLectures();
    }

    @GetMapping("/lecture/{id}")
    public Lecture getLectureById(@PathVariable("id") UUID id) {
        return lectureService.findById(id);
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
