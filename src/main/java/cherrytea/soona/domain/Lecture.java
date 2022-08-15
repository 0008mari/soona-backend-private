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
    @JsonProperty("id")
    @ApiModelProperty(hidden = true)
    private UUID id;

    // FK
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "tchr_id")
    private Teacher teacher;

    // FK
    private String subCode;

    // 수업 일시
    private LocalDateTime lecDate;

    //lecTime 수업 시간 (분 단위)
    private int lecTime;

    //lecNum 회차 관련 정보.

    //lecType 수업 타입 - '보충', '정규'
    //추후 enum 으로 수정하면 좋겠다
    @Column(name = "lec_type")
    private String type;

    //lec_CONT 수업 내용
    @Column(name = "lec_cont", columnDefinition = "TEXT")
    private String content;

    //lecEval 수업 평가 - ex: "10점"
    @Column(name = "lec_eval")
    private String evaluation;

    //hw
    @Column(name = "hw", columnDefinition = "TEXT")
    private String homework;

    //lecMemo
    @Column(columnDefinition = "TEXT")
    private String lecMemo;



}
