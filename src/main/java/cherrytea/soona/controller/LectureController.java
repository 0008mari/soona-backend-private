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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LectureController {

    private final LectureService lectureService;



    @GetMapping(value = "/lectures")
    public List<Lecture> getLectures() {

        //HttpHeaders headers = new HttpHeaders();
        //headers.set("headername", "headervalue");

        List<Lecture> lectures = lectureService.findLectures();

        return lectures;
        //return new ResponseEntity<>(lectures, headers, HttpStatus.valueOf(200));
    }
}
