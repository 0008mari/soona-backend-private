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
    public UUID addLecture(@RequestBody Lecture lecture){
        System.out.println(lecture);
        return lectureService.saveLecture(lecture);
    }

    @GetMapping("/lectures")
    public List<Lecture> getLectures() {
        return lectureService.findLectures();
    }

    @GetMapping("/lecture/{id}")
    public Lecture getOneLecture(@PathVariable("id") UUID id) {
        // System.out.println("------\n\n" + lecture);
        // 검증됨
        return lectureService.findById(id);
    }

    @PutMapping("/lecture/{id}")
    @ApiOperation(value = "수업 수정", notes = "현재 Content 수정만 지원")
    public void updateLecture(@PathVariable("id") UUID id,
                              @RequestBody Lecture lecture) {

        String content = lecture.getContent();
        lectureService.updateLecture(id, content);
        //System.out.println("lecture = \n" + lecture);
    }

    @DeleteMapping("/lecture/{id}")
    public void deleteLecture(@PathVariable("id") UUID id) {
        lectureService.deleteById(id);
    }

}
