package cherrytea.soona.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter @Setter
@Table(name = "stu_memo")
public class StudentMemo {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(name = "memo_id", columnDefinition = "binary(16)")
    @JsonProperty("id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "stu_Id")
    private Student student;

    public void setStudent(Student student) {
        if (this.student != null) {
            this.student.getMemos().remove(this);
        }
        this.student = student;
        if (!student.getMemos().contains(this)) {
            // 무한루프 방지를 위해 체크
            student.addMemo(this);
        }
    }

//    @Column(name="stu_Id")
//    private UUID studentId;

    @Column(name = "memo_cont", columnDefinition = "TEXT")
    private String content;

    private LocalDateTime memoDate;

}
