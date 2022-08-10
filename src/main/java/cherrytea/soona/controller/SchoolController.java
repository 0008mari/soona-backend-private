package cherrytea.soona.controller;

import cherrytea.soona.domain.School;
import cherrytea.soona.service.SchoolService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SchoolController {

    private final SchoolService schoolService;

    @GetMapping("/school/{name}")
    @ApiOperation(value = "학교 검색", notes = "검색 결과를 list로 반환")
    public List<School> getSchoolsByName(@PathVariable("name") String name) {
        return schoolService.findSchoolsByName(name);
    }
}
