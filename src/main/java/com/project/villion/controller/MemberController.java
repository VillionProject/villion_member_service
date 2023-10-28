package com.project.villion.controller;

import com.project.villion.common.RestResult;
import com.project.villion.domain.entity.Member;
import com.project.villion.domain.reqeust.MemberRequest;
import com.project.villion.sevice.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    public void signUp(@RequestBody MemberRequest request) {
        memberService.signUp(request);
    }



    @PostMapping("/login")
//    @ResponseStatus(HttpStatus.CREATED) // TODO ResponseEntity 써도 이거해야함??
    public ResponseEntity<RestResult<Object>> login(String loginId, String password, @RequestBody Member member) {
        return memberService.login(loginId, password, member);
    }

//    @GetMapping("/test")
//    public MemberTokenInfo test(@AuthenticationPrincipal MemberTokenInfo memberTokenInfo){
//        return memberTokenInfo;
//    }
//
//    @PostMapping("/join")
//    @ResponseStatus(HttpStatus.CREATED)
//    public AuthResponse join(
//            @AuthenticationPrincipal MemberTokenInfo memberTokenInfo,
//            @RequestBody JoinRequest joinRequest
//    ){
//        return memberService.join(memberTokenInfo, joinRequest);
//    }

    @PutMapping("/{loginId}")
    public ResponseEntity<RestResult<Object>> update(@PathVariable String loginId, @RequestBody MemberRequest request) {
        return memberService.update(loginId, request);
    }

    @DeleteMapping("/{loginId}")
    public ResponseEntity<RestResult<Object>> delete(@PathVariable String loginId) {
        return memberService.delete(loginId);
    }




}
