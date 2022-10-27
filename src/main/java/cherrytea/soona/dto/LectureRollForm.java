package cherrytea.soona.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter @Setter
public class LectureRollForm {
    UUID lectureId;
    UUID studentId;
}
