package com.sinam7.bulletinboard.web.member;

import com.sinam7.bulletinboard.domain.member.Member;
import com.sinam7.bulletinboard.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    // 회원 목록
    @GetMapping
    public String memberList() {
        return "members/memberList";
    }

    // 회원 가입 폼 호출
    @GetMapping("/register")
    public String registerMemberForm(@ModelAttribute Member member) {
        return "members/registerMemberForm";
    }

    // 회원 가입 처리
    @PostMapping("/register")
    public String registerMember(@ModelAttribute Member member) {
        memberRepository.save(member);
        return "redirect:/members";
    }
}
