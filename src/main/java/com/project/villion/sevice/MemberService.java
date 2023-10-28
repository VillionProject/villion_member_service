package com.project.villion.sevice;

import com.project.villion.common.RestError;
import com.project.villion.common.RestResult;
import com.project.villion.domain.entity.Member;
import com.project.villion.domain.reqeust.MemberRequest;
import com.project.villion.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

//   회원가입
    public void signUp(MemberRequest request) {
        memberRepository.save(request.ToEntity());
    }

//    로그인
    public ResponseEntity<RestResult<Object>> login(String loginId, String password, Member member) {
        Member findByLoginId = memberRepository.findByLoginId(member.getLoginId());

//        if (findByLoginId.getLoginId() != loginId || findByLoginId.getPassword() != password) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body(new RestResult<>("error", new RestError("NOT_FOUND", "ID 또는 PASSWORD가 일치하지 않습니다.")));
//        }
        return ResponseEntity.ok(new RestResult<>("success", "로그인되었습니다."));
    }

//    회원정보 업데이트
    public ResponseEntity<RestResult<Object>> update(String loginId, MemberRequest request) {
        Member member = memberRepository.findByLoginId(loginId);
        System.out.println(request);

        if(member == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new RestResult<>("error", new RestError("ID_NOT_FOUND", "ID가 일치하지 않습니다.")));
        }

        Member updateMember = Member.builder()
                .userId(member.getUserId())
                .loginId(request.getLoginId())
                .password(request.getPassword())
                .libraryName(request.getLibraryName())
                .email(request.getEmail())
                .location(request.getLocation())
                .libraryStatus(request.isLibraryStatus())
                .signInDate(member.getSignInDate())
                .build();

        memberRepository.save(updateMember);

        return ResponseEntity.ok(new RestResult<>("success","회원정보수정이 완료되었습니다."));
    }

//    회원탈퇴
    public ResponseEntity<RestResult<Object>> delete(String loginId) {
        Member member = memberRepository.findByLoginId(loginId);

        if(member == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new RestResult<>("error", new RestError("ID_NOT_FOUND", "ID가 일치하지 않습니다.")));
        }
        memberRepository.delete(member);

        return ResponseEntity.ok(new RestResult<>("success","회원탈퇴가 완료되었습니다."));
    }

}
