package cherrytea.soona.controller;

import cherrytea.soona.domain.Lecture;
import cherrytea.soona.domain.Student;
import cherrytea.soona.dto.LectureForm;
import cherrytea.soona.dto.StudentResponseDto;
import cherrytea.soona.dto.teacher.LoginForm;
import cherrytea.soona.dto.teacher.RegisterForm;
import cherrytea.soona.dto.teacher.TeacherForm;
import cherrytea.soona.dto.teacher.WithdrawalForm;
import cherrytea.soona.service.TeacherService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class TeacherController {

    @Autowired
    ModelMapper modelMapper;

    // 로그인 easy.ver
    private final TeacherService teacherService;

    @PostMapping("/register")
    @ApiOperation(value = "회원가입")
    public UUID registerTeacher(@RequestBody RegisterForm registerForm) throws Exception {
        return teacherService.registerTeacher(registerForm);
    }

    @PostMapping("/login")
    @ApiOperation(value = "로그인. 성공 시 UUID 반환 / 실패 시 null 반환")
    public UUID loginTeacher(@RequestBody LoginForm loginForm) throws Exception {
        return teacherService.getIdByLogin(loginForm);
    }

    @PutMapping("/teacher")
    @ApiOperation(value = "회원정보 수정, 닉네임만 변경 가능")
    public void updateTeacher(@RequestBody TeacherForm teacherForm) {
        teacherService.updateTeacher(teacherForm);
    }

    @GetMapping("/teacher/{id}/lectures")
    @ApiOperation(value = "특정 선생님에게 속한 강의 목록")
    public List<LectureForm> getLecturesByTeacherId(@PathVariable("id") UUID id) {
        List<Lecture> lectureList =  teacherService.findByIdGetLecture(id).getLectures();
        List<LectureForm> lectureFormList = lectureList.stream().map(lecture -> modelMapper.map(lecture, LectureForm.class)).collect(Collectors.toList());
        return lectureFormList;
    }

    @GetMapping("/teacher/{id}/students")
    @ApiOperation(value = "특정 선생님에게 속한 학생 목록")
    public List<StudentResponseDto> getStudentsByTeacherId(@PathVariable("id") UUID id) {

        List<Student> studentList = teacherService.findByIdGetStudent(id).getStudents();

        List<StudentResponseDto> resultList = studentList.stream().map(student -> modelMapper.map(student, StudentResponseDto.class)).collect(Collectors.toList());
        return resultList;
    }

    @GetMapping("/teacher/valid/{id}")
    @ApiOperation(value = "선생님 아이디 검증")
    public Boolean isValidTeacherId(@PathVariable("id") UUID id) {
        return teacherService.isValidTeacherId(id);
    }

    @PostMapping("/teacher/withdrawal")
    @ApiOperation(value = "회원 탈퇴 / uuid, 비밀번호 필요")
    public Boolean withdrawTeacher(@RequestBody WithdrawalForm withdrawalForm) throws Exception {
        return teacherService.withdrawTeacher(withdrawalForm);
    }
}
