package cherrytea.soona.dto.teacher;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WithdrawalForm {
    
    // 선생님 회원 탈퇴 정보 폼

    @NotBlank
    private UUID id;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
}
