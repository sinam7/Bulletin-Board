package com.sinam7.bulletinboard.web.member;

import com.sinam7.bulletinboard.domain.member.Member;
import com.sinam7.bulletinboard.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    // 회원 목록
    @GetMapping("")
    public String memberList(Model model)
    {
        log.info("memberList() call");
        model.addAttribute("members", memberRepository.findAll());
        return "members/memberList";
    }

    // 회원 가입 폼 호출
    @GetMapping("/add")
    public String addMemberForm(@ModelAttribute("member") Member member) {
        log.info("addMemberForm() call - GET");
        return "members/addMemberForm";
    }

    // 회원 가입 처리
    @PostMapping("/add")
    public String addMember(@ModelAttribute("member") Member member) {
        log.info("addMemberForm() call - POST");
        memberRepository.save(member);
        return "redirect:/members";
    }

    @GetMapping("/edit/{id}")
    public String editMemberForm(@PathVariable("id") Long id, Model model) {
        log.info("editMemberForm() call - GET");
        model.addAttribute("member", memberRepository.findById(id));
        return "members/editMemberForm";
    }

    @PostMapping("/edit/{id}")
    public String editMember(@PathVariable("id") Long id, @ModelAttribute Member member) {
        log.info("editMemberForm() call - POST");
        memberRepository.updateMember(id, member);
        return "redirect:/members";
    }
}
