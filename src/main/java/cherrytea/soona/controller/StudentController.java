package cherrytea.soona.controller;


import cherrytea.soona.domain.Student;
import cherrytea.soona.service.StudentService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
//import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/student")
    @ApiOperation(value = "학생 추가", notes = "swagger에서 테스트 시 입력 칸에서 id를 지워주셔야 정상 작동 됩니다. 또한 학교 id만 집어 넣어도 이름이 자동 등록 됩니다.")
    public UUID addStudent(@RequestBody Student student) {
        //System.out.println(student);
        return studentService.saveStudent(student);
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return studentService.findStudents();
    }

    @GetMapping("/student/{id}")
    public Student getStudentById(@PathVariable("id") UUID id) {
        return studentService.findById(id);
    }


    @PutMapping("/student/{id}")
    @ApiOperation(value = "학생 수정", notes = "필수 항목: activated, stuName, stuGender, stuYear, pictureCode, stuTel, parTel / School의 경우 schoolId 기준으로 자동 수정됨 - id만 입력 권장")
    public void updateStudent(@PathVariable("id") UUID id,
                              @RequestBody StudentForm studentForm) {
        studentService.updateStudent(id, studentForm);
    }

    @DeleteMapping("/student/{id}")
    public void deleteStudent(@PathVariable("id") UUID id) {
        studentService.deleteById(id);
    }
}
