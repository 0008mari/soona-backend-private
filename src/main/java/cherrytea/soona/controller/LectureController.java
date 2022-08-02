package cherrytea.soona.controller;

import cherrytea.soona.domain.Lecture;
import cherrytea.soona.repository.LectureRepository;
import cherrytea.soona.service.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController // 리턴값에 자동으로 @ResponseBody 붙어서 HTTP json으로 나감.
@RequiredArgsConstructor
public class LectureController {

    private final LectureService lectureService;

    @PostMapping("/lecture")
    public UUID addLecture(@RequestBody Lecture lecture){
        System.out.println(lecture);
        return lectureService.addLecture(lecture);
    }


    @GetMapping(value = "/lectures")
    public List<Lecture> getLectures() {

        //HttpHeaders headers = new HttpHeaders();
        //headers.set("headername", "headervalue");

        return lectureService.findLectures();
        //return new ResponseEntity<>(lectures, headers, HttpStatus.valueOf(200));
    }
}
