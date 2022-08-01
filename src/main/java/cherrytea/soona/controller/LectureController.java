package cherrytea.soona.controller;

import cherrytea.soona.domain.Lecture;
import cherrytea.soona.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class LectureController {

    private final LectureRepository lectureRepository;

    @GetMapping(value = "/lectures", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getLectures() {

        HttpHeaders headers = new HttpHeaders();
        headers.set("headername", "headervalue");
        List<Lecture> lectures = lectureRepository.findAll();

        return new ResponseEntity<>(lectures, headers, HttpStatus.valueOf(200));
    }

}
