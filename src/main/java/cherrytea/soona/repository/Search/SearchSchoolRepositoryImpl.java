//package cherrytea.soona.repository.Search;
//
//import cherrytea.soona.domain.QSchool;
//import cherrytea.soona.domain.School;
//import com.querydsl.jpa.JPQLQuery;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
//
//import javax.persistence.EntityManager;
//import java.util.ArrayList;
//import java.util.List;
//
//public class SearchSchoolRepositoryImpl extends QuerydslRepositorySupport implements SearchSchoolRepository {
//
//    public SearchSchoolRepositoryImpl() {
//        super(School.class);
//    }
//
//    @Autowired
//    EntityManager em;
//
//    @Override
//    public List<School> searchSchool(String name){
//        QSchool school = QSchool.school;
//
//        JPAQueryFactory query = new JPAQueryFactory(em);
//        List<School> result = query
//                .selectFrom(school)
//                .where(school.name.contains(name))
//                .fetch();
//
//        return result;
//    }
//}


//이런 공통화된 interface에 기능을 넣는 방식은 나중에라도 특정 엔티티(위 예제에서는 Member 엔티티)등을 조회하여 여러 api 에서 사용할 때만 사용하면된다. 보통 실무에서 겪는 일반적인 특정 api에 종속된 Repository의 경우에는 그냥 @Repository 클래스를 만들고 거기에서 JPAQueryFactory, EntityManager를 주입받아서 사용하면 된다.
