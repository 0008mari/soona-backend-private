package cherrytea.soona.dto.teacher;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherForm {

    // 선생님 회원정보 수정 폼
    // 닉네임만 가능

    @NotBlank
    private UUID id;

    @NotBlank(message = "닉네임을 입력해주세요.")
    @Size(min=2, max=50, message = "닉네임 길이가 적절하지 않습니다.")
    private String nickname;

}
