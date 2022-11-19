package cherrytea.soona.service;

import cherrytea.soona.domain.Teacher;
import cherrytea.soona.dto.StudentForm;
import cherrytea.soona.domain.Student;
import cherrytea.soona.dto.StudentResponseDto;
import cherrytea.soona.repository.StudentRepository;
import cherrytea.soona.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    @Transactional
    public UUID saveStudent(StudentForm studentForm) {

        Student student = new Student();
        Teacher teacher = teacherRepository.findById(studentForm.getTeacherId());
        student.setTeacher(teacher);

        student.setStuName(studentForm.getStuName());
        student.setActivated(studentForm.getActivated());
        student.setStuTel(studentForm.getStuTel());
        student.setParTel(studentForm.getParTel());
        student.setStuGender(studentForm.getStuGender());
        student.setStuYear(studentForm.getStuYear());
        student.setPictureCode(studentForm.getPictureCode());
        if (studentForm.getStuAddr() != null) {
            student.setStuAddr(studentForm.getStuAddr());
        }
        if (studentForm.getSchool() != null) {
            student.setSchool(studentForm.getSchool());
        }

        return studentRepository.save(student);
    }

    @Transactional
    public List<Student> findStudents() {
        return studentRepository.findAll();
    }

    public Student findById(UUID id) {
        return studentRepository.findById(id);
    }

    @Transactional
    public void updateStudent(UUID id, StudentForm studentForm) {
        Student student = studentRepository.findById(id);

        // NotBlank
        student.setStuName(studentForm.getStuName());
        student.setActivated(studentForm.getActivated());
        student.setStuTel(studentForm.getStuTel());
        student.setParTel(studentForm.getParTel());
        student.setStuGender(studentForm.getStuGender());
        student.setStuYear(studentForm.getStuYear());
        student.setPictureCode(studentForm.getPictureCode());

        // 널체크
        if (studentForm.getStuAddr() != null) {
            student.setStuAddr(studentForm.getStuAddr());
        }
        if (studentForm.getSchool() != null) {
            student.setSchool(studentForm.getSchool());
        }

        studentRepository.save(student);
    }

    public void deleteById(UUID id) {
        studentRepository.deleteById(id);
    }
}

