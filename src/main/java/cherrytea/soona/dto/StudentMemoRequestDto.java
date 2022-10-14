package cherrytea.soona.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentMemoRequestDto {

    private String content;

    // private LocalDateTime memoDate;
    // 날짜는 service에서 현재 날짜 자동으로 추가해줌
}
