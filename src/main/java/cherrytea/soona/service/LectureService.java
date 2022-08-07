package cherrytea.soona.service;

import cherrytea.soona.domain.Lecture;
import cherrytea.soona.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;

    @Transactional
    public UUID saveLecture(Lecture lecture) {
        return lectureRepository.save(lecture);
    }

    public List<Lecture> findLectures() {
        return lectureRepository.findAll();
    }

    public Lecture findById(UUID id) {
        return lectureRepository.findById(id);
    }

    @Transactional
    public void updateLecture(UUID id, String content){
        Lecture lecture = lectureRepository.findById(id);
        lecture.setContent(content);
    }

    public void deleteById(UUID id){
        lectureRepository.deleteById(id);
    }
}
