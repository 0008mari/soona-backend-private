package cherrytea.soona.dto;

import cherrytea.soona.domain.School;
import cherrytea.soona.domain.Student;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponseDto {

    // getStudents의 반환 Dto

    private UUID id;
    private String stuName;
    private int stuGender;
    private int stuYear;
    private int pictureCode;
    private String stuTel;
    private String parTel;
    private String stuAddr;
    private School school;
    private Boolean activated;


}
