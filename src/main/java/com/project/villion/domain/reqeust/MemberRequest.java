package com.project.villion.domain.reqeust;

import com.project.villion.domain.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class MemberRequest {
    private String loginId;
    private String password;
    private String libraryName;
    private String email;
    private String location;
    private boolean libraryStatus; // 도서관 상태(운영/휴관)
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate signInDate;

//    입력 받은 값을 Member에 저장
    public Member ToEntity() {
        return Member.builder()
                .loginId(loginId)
                .password(password)
                .libraryName(libraryName)
                .email(email)
                .location(location)
                .libraryStatus(libraryStatus)
                .signInDate(signInDate)
                .build();
    }
}
