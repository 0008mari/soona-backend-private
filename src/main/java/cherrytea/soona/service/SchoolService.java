package cherrytea.soona.service;

import cherrytea.soona.domain.School;
import cherrytea.soona.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolService {

    private final SchoolRepository schoolRepository;

    public List<School> findSchoolsByName(String name) {
        return schoolRepository.findSchoolsByName(name);
    }
}
