package cherrytea.soona.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class healthyController {

    // response 200 반환
    @GetMapping("/healthy")
    public ResponseEntity HealthyMessage() {
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

}
