package com.sinam7.bulletinboard.service.login;

import com.sinam7.bulletinboard.domain.member.Member;
import com.sinam7.bulletinboard.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;

    /**
     * login
     * @param memberId
     * @param password
     * @return Member - 로그인 성공
     * <br>    null - 로그인 실패
     */
    public Member login(String memberId, String password) {
        return memberRepository.findByMemberId(memberId)
                .filter(member -> member.getPassword().equals(password))
                .orElse(null);
    }
}
