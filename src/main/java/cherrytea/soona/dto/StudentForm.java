package cherrytea.soona.dto;

import cherrytea.soona.domain.School;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentForm {

    //학생 정보 수정할 때 쓰는 dto

    private UUID teacherId;

    @NotBlank
    private String stuName;

    @NotBlank
    private int stuGender;

    @NotBlank
    private int stuYear;

    @NotBlank
    @Pattern(regexp = "[1-5].")
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
    @NotBlank
    private Boolean activated;

}
