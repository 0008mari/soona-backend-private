package cherrytea.soona;

import cherrytea.soona.domain.QSchool;
import cherrytea.soona.domain.School;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class SearchSchoolTests {

    @Autowired
    EntityManager em;

    @Test
    public void searchSchoolTest() {
        QSchool school = QSchool.school;

        JPAQueryFactory query = new JPAQueryFactory(em);
        List<School> result = query
                .selectFrom(school)
                .where(school.name.contains("무학여자고등학교"))
                .fetch();

        for (School school1 : result) {
            System.out.println("school = " + school1.getName());
        }

        Assertions.assertThat(result).isNotNull();
    }
}
