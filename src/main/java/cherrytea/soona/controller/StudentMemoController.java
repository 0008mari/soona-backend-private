package cherrytea.soona.controller;

import cherrytea.soona.domain.StudentMemo;
import cherrytea.soona.dto.StudentMemoForm;
import cherrytea.soona.service.StudentMemoService;
import cherrytea.soona.service.StudentService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class StudentMemoController {

    private final StudentMemoService studentMemoService;
    private final StudentService studentService;

    // C 메모생성
    @PostMapping("/student/{id}/memo")
    @ApiOperation(value = "학생에 대한 메모 추가", notes = "url로 학생 id 받음, http request body로 내용 받음. post/update 일시는 자동으로 추가됨.")
    public UUID addStudentMemo(@PathVariable("id") UUID studentId, @RequestBody StudentMemoForm studentMemoForm) {
        return studentMemoService.saveStudentMemo(studentId, studentMemoForm);
    }

    // R 메모불러오기
    // {학생, 선생님 } 조합으로 -> 목록불러오기
    @GetMapping("/student/{id}/memos")
    public List<StudentMemo> getStudentMemoById(@PathVariable("id") UUID id) {
        return studentService.findById(id).getMemos();
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
