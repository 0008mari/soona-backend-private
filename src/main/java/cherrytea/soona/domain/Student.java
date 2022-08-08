package cherrytea.soona.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter @Setter
public class Student {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(name = "stu_id", columnDefinition = "binary(16)")
    @JsonProperty("id")
    @ApiModelProperty(hidden = true)
    private UUID id;

    private String stuName;

    private String stuTel;

    private String parTel;

    private String stuAddr;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "sch_id")
    private School school;

}
