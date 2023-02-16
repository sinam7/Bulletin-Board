package com.sinam7.bulletinboard.web.member.dto;

import com.sinam7.bulletinboard.domain.member.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberFormDTO {

    @NotBlank private String memberId;
    @NotBlank private String password;
    @NotBlank private String name;

    public static Member buildMember(Long id, MemberFormDTO memberFormDTO) {
        return new Member(id, memberFormDTO.memberId, memberFormDTO.password, memberFormDTO.name);
    }
}
