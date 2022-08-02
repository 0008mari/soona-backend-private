package cherrytea.soona.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Clob;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Lecture {

    @Id
    @GeneratedValue
    @Column(name = "LEC_ID")
    private UUID id;

    // FK
    @ManyToOne
    @JoinColumn(name = "TCHR_ID")
    private Teacher teacher;

    // FK
    private String subCode;

    // 수업 일시
    private LocalDateTime lecDate;

    //lecTime 수업 시간 (몇시간했는지)

    //lecNum ? 문서에 안 써 있음.

    //lecType 수업 타입 - '보충', '정규'
    //추후 enum 으로 수정하면 좋겠다
    @Column(name = "LEC_TYPE")
    private String type;

    //lec_CONT 수업 내용
    @Column(name = "LEC_CONT", columnDefinition = "TEXT")
    private String content;

    //lecEval 수업 평가 - ex: "10점"
    @Column(name = "LEC_EVAL")
    private String evaluation;

    //hw
    @Column(name = "hw", columnDefinition = "TEXT")
    private String homework;

    //lecMemo
    @Column(columnDefinition = "TEXT")
    private String lecMemo;



}
