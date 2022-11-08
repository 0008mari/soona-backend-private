package cherrytea.soona.service;

import cherrytea.soona.domain.QLecture;
import cherrytea.soona.domain.QStudent;
import cherrytea.soona.domain.Teacher;
import cherrytea.soona.dto.teacher.LoginForm;
import cherrytea.soona.dto.teacher.RegisterForm;
import cherrytea.soona.dto.teacher.TeacherForm;
import cherrytea.soona.dto.teacher.WithdrawalForm;
import cherrytea.soona.repository.TeacherRepository;
import cherrytea.soona.secure.SoonaSecure;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeacherService {
    
    private final TeacherRepository teacherRepository;

    @Transactional
    public UUID registerTeacher(RegisterForm registerForm) throws NoSuchAlgorithmException {
        SoonaSecure soonaSecure = new SoonaSecure();

        Teacher teacher = new Teacher();
        teacher.setNickname(registerForm.getNickname());
        teacher.setPassword(SoonaSecure.encrypt(registerForm.getPassword()));
        teacher.setUsername(registerForm.getUsername());
        teacher.setActivated(true);
        return teacherRepository.save(teacher);
    }

    public UUID getIdByLogin(LoginForm loginForm) throws NoSuchAlgorithmException {

        // username, pw 일치하면
        // id 리턴
        List<Teacher> foundTeacher = teacherRepository.findByUsername(loginForm.getUsername());
        if (foundTeacher.size() == 0) {
            return null;
        } else {
            if (foundTeacher.get(0).getActivated().equals(false)) {
                return null;
            } else{
                if (foundTeacher.get(0).getPassword().equals(SoonaSecure.encrypt(loginForm.getPassword()))) {
                    System.out.println("로그인 성공 ");
                    return foundTeacher.get(0).getId();
                } else {
                    return null;
                }
            }
        }
    }

    public Teacher findById(UUID uuid) {

        Teacher foundTeacher = teacherRepository.findById(uuid);
        if (foundTeacher.getActivated().equals(true)) {
            return foundTeacher;
        } else {
            return null;
        }
    }

    public Teacher findByIdGetStudent(UUID uuid) {
        Teacher foundTeacher = teacherRepository.findByIdByFetchJoinStudent(uuid);
        if (foundTeacher.getActivated().equals(true)) {
            return foundTeacher;
        } else {
            return null;
        }
    }

    public Teacher findByIdGetLecture(UUID uuid) {
        Teacher foundTeacher = teacherRepository.findByIdByFetchJoinLecture(uuid);
        if (foundTeacher.getActivated().equals(true)) {
            return foundTeacher;
        } else {
            return null;
        }
    }
    @Transactional
    public void updateTeacher(TeacherForm teacherForm) {

        Teacher teacher = teacherRepository.findById(teacherForm.getId());
        if (teacher.getActivated().equals(true)) {
            teacher.setNickname(teacherForm.getNickname());
        }
    }

    public Boolean isValidTeacherId(UUID id) {

        Teacher foundTeacher = teacherRepository.findById(id);
        if (foundTeacher == null) {
            return false;
        } else {
            if (foundTeacher.getActivated().equals(false)) {
                return false;
            }
            return true;
        }
    }

    @Transactional
    public Boolean withdrawTeacher(WithdrawalForm withdrawalForm) throws NoSuchAlgorithmException {

        Teacher foundTeacher = teacherRepository.findById(withdrawalForm.getId());
        if (foundTeacher == null) {
            return false;
        } else {
            // 비밀번호 검증
            if (foundTeacher.getPassword().equals(SoonaSecure.encrypt(withdrawalForm.getPassword()))) {
                foundTeacher.setActivated(false);
                return true;
            } else {
                return false;
            }
        }
    }

    public Boolean isValidUsername(String username) {
        List<Teacher> foundTeacher = teacherRepository.findByUsername(username);
        if (foundTeacher.size() == 0) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
}
