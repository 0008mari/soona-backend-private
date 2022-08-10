package cherrytea.soona.repository;

import cherrytea.soona.domain.QSchool;
import cherrytea.soona.domain.School;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SchoolRepository {

    private final EntityManager em;

    public List<School> findSchoolsByName(String name) {

        QSchool school = QSchool.school;

        JPAQueryFactory query = new JPAQueryFactory(em);
        List<School> result = query
                .selectFrom(school)
                .where(school.name.contains(name))
                .fetch();

        return result;
    }

}
