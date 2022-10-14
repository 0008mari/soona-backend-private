package cherrytea.soona.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter @Setter
@Table
public class Lecture {

    @Id
    @GeneratedValue(generator= "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(name = "lec_id", columnDefinition = "binary(16)")
    private UUID id;

    // FK
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "tchr_id")
    private Teacher teacher;

    // FK
    private String subCode;

    // 수업 일시
    private LocalDateTime lecDate;

    //lecTime 수업 시간 (분 단위)
    private Integer lecTime;

    // lecType 수업 타입 - '보충', '정규'
    // 정규 0 보충 1
    //추후 enum 으로 수정하면 좋겠다
    @Column(name = "lec_type")
    private int type;

    //lec_CONT 수업 내용
    @Column(name = "lec_cont", columnDefinition = "TEXT")
    private String content;

    //lecEval 수업 평가 - (int 0-4 사이)
    @Column(name = "lec_eval")
    private int evaluation;

    //hw
    @Column(name = "hw", columnDefinition = "TEXT")
    private String homework;

    //lecMemo
    @Column(columnDefinition = "TEXT")
    private String lecMemo;

    public void setTeacher(Teacher teacher) {
        if (this.teacher != null) {
            this.teacher.getLectures().remove(this);
        }
        this.teacher = teacher;
        if (!teacher.getLectures().contains(this)) {
            teacher.addLecture(this);
        }
    }


}
