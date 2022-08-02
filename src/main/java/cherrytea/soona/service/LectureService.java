package cherrytea.soona.service;

import cherrytea.soona.domain.Lecture;
import cherrytea.soona.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;

    public UUID addLecture(Lecture lecture) {
        return lectureRepository.save(lecture);
    }

    public List<Lecture> findLectures() {
        return lectureRepository.findAll();
    }
    public Lecture findOne(UUID lectureId) {
        return lectureRepository.findOne(lectureId);
    }
}
