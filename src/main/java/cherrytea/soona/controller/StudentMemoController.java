package cherrytea.soona.controller;

import cherrytea.soona.dto.StudentMemoRequestDto;
import cherrytea.soona.service.StudentMemoService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class StudentMemoController {

    private final StudentMemoService studentMemoService;

    // C 메모생성
    @PostMapping("/student/{id}/memo")
    @ApiOperation(value = "학생에 대한 메모 추가", notes = "url로 학생 id 받음, http request body로 내용 받음. post/update 일시는 자동으로 추가됨.")
    public UUID addStudentMemo(@PathVariable("id") UUID studentId, @RequestBody StudentMemoRequestDto studentMemoRequestDto) {
        return studentMemoService.saveStudentMemo(studentId, studentMemoRequestDto);
    }

    // R 임시 api - 학생
    // @GetMapping("/student/{id}/memos")


    // U 메모수정
    @PutMapping("/studentMemo/{id}")
    public void updateStudentMemo(@PathVariable("id") UUID id,
                                  @RequestBody StudentMemoRequestDto studentMemoRequestDto) {
        studentMemoService.updateStudentMemo(id, studentMemoRequestDto);
    }

    // D 메모삭제
    @DeleteMapping("/studentMemo/{id}")
    public void deleteStudentMemo(@PathVariable("id") UUID id) {
        studentMemoService.deleteById(id);
    }

}
