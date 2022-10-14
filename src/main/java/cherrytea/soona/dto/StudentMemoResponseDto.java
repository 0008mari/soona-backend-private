package cherrytea.soona.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentMemoResponseDto {

    private UUID id;

    private String Content;

    private LocalDateTime memoDate;

    // private LocalDateTime memoDate;
    // 날짜는 service에서 현재 날짜 자동으로 추가해줌
}
