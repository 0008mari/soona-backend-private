package cherrytea.soona.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Controller
public class healthyController {

    // response 200 반환
    @GetMapping("/healthy")
    public ResponseEntity HealthyMessage() {
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/time")
    public ResponseEntity TimeMessage() {
        return new ResponseEntity<>("지금 몇시?\n" + LocalDateTime.now(), HttpStatus.OK);
    }
}
