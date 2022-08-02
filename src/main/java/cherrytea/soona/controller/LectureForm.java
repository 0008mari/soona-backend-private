package cherrytea.soona.controller;

import cherrytea.soona.domain.Teacher;
import lombok.Getter;
import lombok.Setter;

import java.sql.Clob;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class LectureForm {

    private UUID id;

    private Teacher teacher;

    private String subCode;

    private LocalDateTime lecDate;

    private String type;

    private Clob content;

    private String evaluation;

    private Clob homework;

    private Clob lecMemo;
}

