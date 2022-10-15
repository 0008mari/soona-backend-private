package cherrytea.soona.controller;


import cherrytea.soona.domain.Student;
import cherrytea.soona.domain.StudentMemo;
import cherrytea.soona.dto.StudentForm;
import cherrytea.soona.dto.StudentMemoResponseDto;
import cherrytea.soona.dto.StudentResponseDto;
import cherrytea.soona.service.StudentService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
//import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class StudentController {

    @Autowired
    ModelMapper modelMapper;
    private final StudentService studentService;

    @PostMapping("/student")
    @ApiOperation(value = "학생 추가", notes = "swagger에서 테스트 시 입력 칸에서 id를 지워주셔야 정상 작동 됩니다. 또한 학교 id만 집어 넣어도 이름이 자동 등록 됩니다.")
    public UUID addStudent(@Valid @RequestBody StudentForm studentForm) {
        //System.out.println(student);
        return studentService.saveStudent(studentForm);
    }

    @GetMapping("/students")
    public List<StudentResponseDto> getStudents() {

        List<Student> studentList = studentService.findStudents();

        return studentList.stream().map(student -> modelMapper.map(student, StudentResponseDto.class)).collect(Collectors.toList());
    }

    @GetMapping("/student/{id}")
    public StudentResponseDto getStudentById(@PathVariable("id") UUID id) {
        return modelMapper.map(studentService.findById(id), StudentResponseDto.class);
    }

    @PutMapping("/student/{id}")
    @ApiOperation(value = "학생 수정", notes = "필수 항목: activated, stuName, stuGender, stuYear, pictureCode, stuTel, parTel / School의 경우 schoolId 기준으로 자동 수정됨 - id만 입력 권장")
    public void updateStudent(@PathVariable("id") UUID id,
                              @Valid @RequestBody StudentForm studentForm) {
        studentService.updateStudent(id, studentForm);
    }

    @DeleteMapping("/student/{id}")
    public void deleteStudent(@PathVariable("id") UUID id) {
        studentService.deleteById(id);
    }

    @GetMapping("/student/{id}/memos")
    public List<StudentMemoResponseDto> getStudentMemoById(@PathVariable("id") UUID id) {

        List<StudentMemo> memoList = studentService.findById(id).getMemos();
        return memoList.stream().map(studentMemo -> modelMapper.map(studentMemo, StudentMemoResponseDto.class)).collect(Collectors.toList());
    }

}
