package cherrytea.soona.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class School {

    @Id
    @Column(name = "sch_id")
    @JsonProperty("schoolId")
    @ApiModelProperty(example = "7010057")
    private String schoolId;

    @Column(name = "sch_name")
    private String name;
}
