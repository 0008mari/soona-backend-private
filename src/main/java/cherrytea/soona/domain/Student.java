package cherrytea.soona.domain;

import cherrytea.soona.dto.StudentResponseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter @Setter
public class Student {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(name = "stu_id", columnDefinition = "binary(16)")
    @JsonProperty("id")
    //@ApiModelProperty(hidden = true)
    private UUID id;

    @NotBlank
    private String stuName;

    @ApiModelProperty(example = "3")
    private int stuGender;

    // 학년
    @ApiModelProperty(example = "3")
    private int stuYear;

    @Column(name = "stu_pic")
    @ApiModelProperty(example = "1")
    private int pictureCode;

    private String stuTel;

    private String parTel;

    private String stuAddr;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "sch_id", nullable = false)
    private School school;

    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
    private List<StudentMemo> memos = new ArrayList<StudentMemo>();

    public void addMemo(StudentMemo memo) {
        this.memos.add(memo);
        if (memo.getStudent() != this) {
            memo.setStudent(this);
        }
    }

    @Column(name = "act_chk")
    private Boolean activated;

    @ManyToOne
    @JoinColumn(name = "tchr_id")
    private Teacher teacher;

    public void setTeacher(Teacher teacher) {
        if (this.teacher != null) {
            this.teacher.getStudents().remove(this);
        }
        this.teacher = teacher;
        if (!teacher.getStudents().contains(this)) {
            teacher.addStudent(this);
        }
    }

}
