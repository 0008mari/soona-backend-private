package cherrytea.soona.domain;

import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
public class Teacher {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(name = "tchr_id", columnDefinition = "binary(16)")
    private UUID id;

    @Column(name = "tchr_name")
    private String name;

    // lecture 조회용 - 양방향
    // table 에 영향 x
    @OneToMany(mappedBy = "teacher")
    private List<Lecture> lectures = new ArrayList<>();
}
