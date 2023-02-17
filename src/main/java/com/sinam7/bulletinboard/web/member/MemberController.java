package com.sinam7.bulletinboard.web.member;

import com.sinam7.bulletinboard.domain.member.dto.MemberFormDTO;
import com.sinam7.bulletinboard.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원 목록
    @GetMapping("")
    public String memberList(Model model)
    {
        log.info("memberList() call");
        model.addAttribute("members", memberService.findAll());
        return "members/memberList";
    }

    // 회원 가입 폼 호출
    @GetMapping("/add")
    public String addMemberForm(@ModelAttribute("member") MemberFormDTO memberFormDTO) {
        log.info("addMemberForm() call - GET");
        return "members/addMemberForm";
    }

    // 회원 가입 처리
    @PostMapping("/add")
    public String addMember(@Validated @ModelAttribute("member") MemberFormDTO memberFormDTO, BindingResult bindingResult) {
        log.info("addMember() call - POST");

        // 도메인 Validation 처리 -> @Validated + BindingResult로 처리

        // 서비스 Validation throw 처리
        List<FieldError> hasFieldErrors = memberService.register(memberFormDTO);
        if (bindingResult.hasErrors() || !hasFieldErrors.isEmpty()) {
            for (FieldError fieldError : hasFieldErrors) {
                bindingResult.addError(fieldError);
            }

            log.error("addMember() ex = {}", bindingResult);
            return "members/addMemberForm";
        }

        return "redirect:/members";
    }

    @GetMapping("/edit/{id}")
    public String editMemberForm(@PathVariable("id") Long id, Model model) {
        log.info("editMemberForm() call - GET");
        model.addAttribute("member", memberService.findById(id));
        return "members/editMemberForm";
    }

    @PostMapping("/edit/{id}")
    public String editMember(@PathVariable("id") Long id,
                             @Validated @ModelAttribute("member") MemberFormDTO memberFormDTO, BindingResult bindingResult) {
        log.info("editMemberForm() call - POST");

        if (bindingResult.hasErrors()) {
            log.error("addMember() ex = {}", bindingResult);
            return "members/editMemberForm";
        }

        memberService.updateMember(id, memberFormDTO);
        return "redirect:/members";
    }
}
