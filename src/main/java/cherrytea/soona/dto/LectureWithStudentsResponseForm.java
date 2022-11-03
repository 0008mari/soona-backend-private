package cherrytea.soona.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LectureWithStudentsResponseForm {

    private UUID teacherId;
    private UUID lectureId;

    private String subCode;
    private LocalDateTime lecDate;
    private Integer lecTime;
    private Integer type;
    private String content;
    private Integer evaluation;
    private String homework;
    private String lecMemo;

    private List<UUID> studentList;
    private List<String> studentNameList;

}

