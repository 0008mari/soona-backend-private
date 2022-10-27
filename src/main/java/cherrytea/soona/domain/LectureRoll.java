package cherrytea.soona.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "lec_roll")
public class LectureRoll {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "lec_roll_id")
    private Long id;

    // 굳이 엔티티까지 가져올 필요가 없음.
//    @ManyToOne(cascade = CascadeType.MERGE)
//    @JoinColumn(name = "lec_id", columnDefinition = "binary(16)", nullable = false)
//    private Lecture lecture;

    @Column(name = "lec_id", columnDefinition = "binary(16)")
    private UUID lectureId;

//    @ManyToOne(cascade = CascadeType.MERGE)
//    @JoinColumn(name = "stu_id", columnDefinition = "binary(16)", nullable = false)
//    private Student student;

    @Column(name = "stu_id", columnDefinition = "binary(16)")
    private UUID studentId;

    @Column(name = "lec_roll_hw")
    private String homeworkKey;

    @Override
    public String toString() {
        return "LectureRoll{" +
                "id=" + id +
                ", lectureId=" + lectureId +
                ", studentId=" + studentId +
                ", homeworkKey='" + homeworkKey + '\'' +
                '}';
    }
}
