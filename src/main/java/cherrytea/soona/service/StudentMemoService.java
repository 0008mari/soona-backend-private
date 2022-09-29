package cherrytea.soona.service;

import cherrytea.soona.dto.StudentMemoForm;
import cherrytea.soona.domain.StudentMemo;
import cherrytea.soona.repository.StudentMemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentMemoService {

    private final StudentMemoRepository studentMemoRepository;

    @Transactional
    public UUID saveStudentMemo(StudentMemo studentMemo) {
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
        memo.setMemoDate(studentMemoForm.getMemoDate());
        memo.setContent(studentMemoForm.getContent());
        studentMemoRepository.save(memo);
    }
}
