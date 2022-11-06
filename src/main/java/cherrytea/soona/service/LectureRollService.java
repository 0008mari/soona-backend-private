package cherrytea.soona.service;

import cherrytea.soona.domain.Lecture;
import cherrytea.soona.domain.LectureRoll;
import cherrytea.soona.domain.Student;
import cherrytea.soona.dto.LectureForm;
import cherrytea.soona.repository.LectureRollRepository;
import cherrytea.soona.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LectureRollService {

    private final LectureRollRepository lectureRollRepository;
    private final StudentRepository studentRepository;

    @Transactional
    public Long saveLectureRoll(LectureRoll lectureRoll) {
        lectureRoll.setHomeworkKey(makeHomeworkKey());
        return lectureRollRepository.save(lectureRoll);
    }


    public List<Student> findStudentsByLectureId(UUID lectureId) {
        // input: 수업id, output: 수업듣는학생들list
        List<LectureRoll> lectureRollList = lectureRollRepository.findLectureRollsByLectureId(lectureId);
        // lectureRoll 속 id에서 실제 student 객체로 변환
        List<UUID> studentIdList = lectureRollList.stream()
                .map(LectureRoll::getStudentId)
                .collect(Collectors.toList());
        List<Student> studentList = studentIdList.stream()
                .map(studentRepository::findById)
                .collect(Collectors.toList());
        return studentList;
    }

    public List<UUID> findStudentsIdByLectureId(UUID lectureId) {
        // input: 수업id, output: 수업듣는학생들list
        List<LectureRoll> lectureRollList = lectureRollRepository.findLectureRollsByLectureId(lectureId);
        // lectureRoll 속 id에서 실제 student 객체로 변환
        List<UUID> studentIdList = lectureRollList.stream()
                .map(LectureRoll::getStudentId)
                .collect(Collectors.toList());
        return studentIdList;
    }

    public List<String> findStudentsNameByLectureId(UUID lectureId) {
        // input: 수업id, output: 수업듣는학생들list
        List<LectureRoll> lectureRollList = lectureRollRepository.findLectureRollsByLectureId(lectureId);
        // lectureRoll 속 id에서 실제 student 객체로 변환
        List<UUID> studentIdList = lectureRollList.stream()
                .map(LectureRoll::getStudentId)
                .collect(Collectors.toList());
        List<Student> studentList = studentIdList.stream()
                .map(studentRepository::findById)
                .collect(Collectors.toList());
        List<String> studentNameList = studentList.stream()
                .map(Student::getStuName)
                .collect(Collectors.toList());
        return studentNameList;
    }

    @Transactional
    public void updateLectureRoll(UUID lectureId, List<UUID> newStudentList){
        // lectureId 기준으로
        // 딸려있는 lectureRoll 모두 삭제하고
        List<LectureRoll> lectureRollList = lectureRollRepository.findLectureRollsByLectureId(lectureId);
        for (LectureRoll lectureRoll : lectureRollList){
            lectureRollRepository.deleteById(lectureRoll.getId());
        }
        // 새로 생성
//        List<UUID> studentIdList = lectureRollList.stream()
//                .map(LectureRoll::getStudentId)
//                .collect(Collectors.toList());
        for (UUID studentId : newStudentList) {
            LectureRoll lectureRoll = new LectureRoll();
            lectureRoll.setLectureId(lectureId);
            lectureRoll.setStudentId(studentId);
            Long savedId2 = saveLectureRoll(lectureRoll);
        }
    }

    // 문자열 생성
    public String makeHomeworkKey() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i<=90 || i>= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

}
