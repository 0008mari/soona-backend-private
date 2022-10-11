package cherrytea.soona.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "event")
@Getter @Setter
@Table(name = "dayevent")
public class Event {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid", strategy = "uuid2")
    @Column(name = "event_id", columnDefinition = "binary(16)")
    private UUID id;

    // 0 = 수업, 1 = 학사일정
    @Column(name = "event_type")
    private Integer type;

    @Column(name = "teacher_id", columnDefinition = "binary(16)")
    private UUID teacherId;

    @Column(name = "lecture_id")
    private UUID lectureId;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String eventName;

    private UUID studentId;

}
