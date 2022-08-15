package cherrytea.soona.controller;

import cherrytea.soona.domain.Teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LectureForm {

    // private Teacher teacher;
    // 선생님은 수정 가능하지 않음

    private String subCode;

    private LocalDateTime lecDate;

    private Integer lecTime;

    private String type;

    private String content;

    private String evaluation;

    private String homework;

    private String lecMemo;


}

