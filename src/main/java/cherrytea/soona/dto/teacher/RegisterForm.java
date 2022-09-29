package cherrytea.soona.dto.teacher;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterForm {

    // 회원가입 dto
    private String username;

    private String password;

    private String nickname;

}
