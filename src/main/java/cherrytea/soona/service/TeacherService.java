package cherrytea.soona.service;

import cherrytea.soona.domain.Teacher;
import cherrytea.soona.dto.teacher.LoginForm;
import cherrytea.soona.dto.teacher.RegisterForm;
import cherrytea.soona.dto.teacher.TeacherForm;
import cherrytea.soona.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeacherService {
    
    private final TeacherRepository teacherRepository;

    @Transactional
    public UUID registerTeacher(RegisterForm registerForm) {
        Teacher teacher = new Teacher();
        teacher.setNickname(registerForm.getNickname());
        teacher.setPassword(registerForm.getPassword());
        teacher.setUsername(registerForm.getUsername());
        return teacherRepository.save(teacher);
    }

    public UUID getIdByLogin(LoginForm loginForm) {

        // username, pw 일치하면
        // id 리턴
        List<Teacher> foundTeacher = teacherRepository.findByUsername(loginForm.getUsername());
        if (foundTeacher == null) {
            return null;
        } else {
            if (foundTeacher.get(0).getPassword().equals(loginForm.getPassword())) {
                System.out.println("로그인 성공 ");
                return foundTeacher.get(0).getId();
            } else {
                return null;
            }
        }
    }

    @Transactional
    public void updateTeacher(TeacherForm teacherForm) {

        Teacher teacher = teacherRepository.findById(teacherForm.getId());
        teacher.setNickname(teacherForm.getNickname());

    }
}
