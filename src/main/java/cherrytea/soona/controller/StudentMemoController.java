package cherrytea.soona.controller;

import cherrytea.soona.domain.StudentMemo;
import cherrytea.soona.dto.StudentMemoForm;
import cherrytea.soona.service.StudentMemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class StudentMemoController {

    private final StudentMemoService studentMemoService;

    // C 메모생성
    @PostMapping("/studentMemo")
    public UUID addStudentMemo(@RequestBody StudentMemo studentMemo) {
        return studentMemoService.saveStudentMemo(studentMemo);
    }

    // R 메모불러오기
    // {학생, 선생님 } 조합으로 -> 목록불러오기
    @GetMapping("/studentMemo")
    public List<StudentMemo> getStudentMemos(@RequestParam("teacher") UUID teacherId, @RequestParam("student") UUID studentId) {
        return studentMemoService.findByTeacherStudent(teacherId, studentId);
    }

    // U 메모수정
    @PutMapping("/studentMemo/{id}")
    public void updateStudentMemo(@PathVariable("id") UUID id,
                                  @RequestBody StudentMemoForm studentMemoForm) {
        studentMemoService.updateStudentMemo(id, studentMemoForm);
    }

    // D 메모삭제
    @DeleteMapping("/studentMemo/{id}")
    public void deleteStudentMemo(@PathVariable("id") UUID id) {
        studentMemoService.deleteById(id);
    }

}
