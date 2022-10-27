package cherrytea.soona.dto;

import cherrytea.soona.domain.School;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentForm {

    //학생 정보 수정할 때 쓰는 dto

    private UUID teacherId;

    @NotBlank
    private String stuName;

    @NotNull
    private int stuGender;

    @NotNull
    private int stuYear;

//    @NotNull
//    @Range(min = 1, max = 5)
    private int pictureCode;

    @NotBlank
    @Pattern(regexp = "\\d{2,3}\\d{3,4}\\d{4}")
    private String stuTel;

    @NotBlank
    @Pattern(regexp = "\\d{2,3}\\d{3,4}\\d{4}")
    private String parTel;

    private String stuAddr;

    private School school;

    // 학생 상태
    @NotNull
    private Boolean activated;

}
