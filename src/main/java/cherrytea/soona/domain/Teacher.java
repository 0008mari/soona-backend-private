package cherrytea.soona.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter @Setter
public class Teacher {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(name = "tchr_id", columnDefinition = "binary(16)")
    private UUID id;

    @Column(name = "username", length = 50)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "tchr_name")
    private String nickname;

    @Column(name = "activated", columnDefinition = "tinyint(1)")
    private Boolean activated;

    // lecture 조회용 - 양방향
    // table 에 영향 x
    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
    private List<Lecture> lectures = new ArrayList<Lecture>();

    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
    private List<Student> students = new ArrayList<Student>();

    public void addLecture(Lecture lecture) {
        this.lectures.add(lecture);
        if (lecture.getTeacher() != this) {
            lecture.setTeacher(this);
        }
    }

    public void addStudent(Student student) {
        this.students.add(student);
        if (student.getTeacher() != this) {
            student.setTeacher(this);
        }
    }
}
