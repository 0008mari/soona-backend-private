package cherrytea.soona.controller;


import cherrytea.soona.domain.Student;
import cherrytea.soona.service.StudentService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/student")
    @ApiOperation(value = "학생 추가", notes = "swagger에서 테스트 시 입력 칸에서 id를 지워주셔야 정상 작동 됩니다.")
    public UUID addStudent(@RequestBody Student student) {
        //System.out.println(student);
        return studentService.saveStudent(student);
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return studentService.findStudents();
    }

    @PutMapping("/student/{id}")
    @ApiOperation(value = "학생 수정", notes = "현재 stuTel 수정만 지원. 나머지는 무시됨")
    public void updateStudent(@PathVariable("id") UUID id,
                              @RequestBody Student student) {
        //String stuAddr = student.getStuAddr();
        String stuTel = student.getStuTel();
        studentService.updateStudent(id, stuTel);
    }

    @DeleteMapping("/student/{id}")
    public void deleteStudent(@PathVariable("id") UUID id) {
        studentService.deleteById(id);
    }
}
