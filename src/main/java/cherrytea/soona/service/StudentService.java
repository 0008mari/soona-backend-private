package cherrytea.soona.service;

import cherrytea.soona.domain.Student;
import cherrytea.soona.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    @Transactional
    public UUID saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> findStudents() {
        return studentRepository.findAll();
    }

    public Student findById(UUID id) {
        return studentRepository.findById(id);
    }

    @Transactional
    public void updateStudent(UUID id, String stuTel) {
        Student student = studentRepository.findById(id);
        student.setStuTel(stuTel);
    }

    public void deleteById(UUID id) {
        studentRepository.deleteById(id);
    }
}

