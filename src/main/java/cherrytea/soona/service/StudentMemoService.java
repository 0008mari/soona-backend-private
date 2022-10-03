package cherrytea.soona.service;

import cherrytea.soona.domain.Student;
import cherrytea.soona.dto.StudentMemoForm;
import cherrytea.soona.domain.StudentMemo;
import cherrytea.soona.repository.StudentMemoRepository;
import cherrytea.soona.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentMemoService {

    private final StudentRepository studentRepository;
    private final StudentMemoRepository studentMemoRepository;

    @Transactional
    public UUID saveStudentMemo(UUID studentId, StudentMemoForm studentMemoForm) {
        StudentMemo studentMemo = new StudentMemo();
        Student student = studentRepository.findById(studentId);
        studentMemo.setStudent(student);
        studentMemo.setMemoDate(LocalDateTime.now());
        studentMemo.setContent(studentMemoForm.getContent());
        return studentMemoRepository.save(studentMemo);
    }

    public List<StudentMemo> findByTeacherStudent(UUID teacherId, UUID studentId) {
        return studentMemoRepository.findByTeacherStudent(teacherId, studentId);
    }

    public void deleteById(UUID id) {
        studentMemoRepository.deleteById(id);
    }

    public void updateStudentMemo(UUID id, StudentMemoForm studentMemoForm) {
        StudentMemo memo = studentMemoRepository.findById(id);
        memo.setMemoDate(LocalDateTime.now());
        memo.setContent(studentMemoForm.getContent());
        studentMemoRepository.save(memo);
    }
}
