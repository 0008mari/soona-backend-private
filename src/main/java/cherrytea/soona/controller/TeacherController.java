package cherrytea.soona.controller;

import cherrytea.soona.domain.Lecture;
import cherrytea.soona.dto.teacher.LoginForm;
import cherrytea.soona.dto.teacher.RegisterForm;
import cherrytea.soona.dto.teacher.TeacherForm;
import cherrytea.soona.service.TeacherService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class TeacherController {

    // 로그인 easy.ver
    private final TeacherService teacherService;

    @PostMapping("/register")
    @ApiOperation(value = "회원가입")
    public UUID registerTeacher(@RequestBody RegisterForm registerForm) {
        return teacherService.registerTeacher(registerForm);
    }

    @PostMapping("/login")
    @ApiOperation(value = "로그인. 성공 시 UUID 반환 / 실패 시 null 반환")
    public UUID loginTeacher(@RequestBody LoginForm loginForm) {
        return teacherService.getIdByLogin(loginForm);
    }

    @PutMapping("/teacher")
    @ApiOperation(value = "회원정보 수정, 닉네임만 변경 가능")
    public void updateTeacher(@RequestBody TeacherForm teacherForm) {
        teacherService.updateTeacher(teacherForm);
    }

    @GetMapping("/teacher/{id}/lectures")
    @ApiOperation(value = "특정 선생님에게 속한 강의 목록")
    public List<Lecture> getLecturesByTeacherId(@PathVariable("id") UUID id) {
        return teacherService.findById(id).getLectures();
    }
}
