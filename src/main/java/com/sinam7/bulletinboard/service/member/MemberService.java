package com.sinam7.bulletinboard.service.member;

import com.sinam7.bulletinboard.domain.member.Member;
import com.sinam7.bulletinboard.domain.member.MemberRepository;
import com.sinam7.bulletinboard.domain.member.dto.MemberFormDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원 가입 폼 처리
     * @param form View에서 넘어온 양식
     * @return {@literal List<FieldError>} - 검증 실패
     * <br>    null - 검증 성공
     * @see FieldError
     */
    public List<FieldError> register(MemberFormDTO form) {
        List<FieldError> fieldErrors = validateMember(form, null);
        if (!fieldErrors.isEmpty()) return fieldErrors;

        Member member = MemberFormDTO.buildMember(MemberRepository.addSequence(), form);
        memberRepository.save(member);

        return null;
    }

    public Member findById(Long id) {
        return memberRepository.findById(id);
    }

    public Optional<Member> findByMemberId(String memberId) {
        return memberRepository.findByMemberId(memberId);
    }

    /**
     * 회원 정보 수정 폼 처리
     * @param form View에서 넘어온 양식
     * @return {@literal List<FieldError>} - 검증 실패
     * <br>    null - 검증 성공
     * @see FieldError
     */
    public List<FieldError> updateMember(Long id, MemberFormDTO form) {
        List<FieldError> fieldErrors = validateMember(form, id);
        if (!fieldErrors.isEmpty()) return fieldErrors;

        Member member = MemberFormDTO.buildMember(id, form);
        memberRepository.updateMember(id, member);
        return null;
    }

    public void deleteMember(Member member) {
        memberRepository.deleteMember(member);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    private List<FieldError> validateMember(MemberFormDTO form, Long idIfEditing) {
        ArrayList<FieldError> exceptions = new ArrayList<>();

        exceptions.add(validateMemberId(form.getMemberId(), idIfEditing));
        exceptions.add(validateNickname(form.getName(), idIfEditing));

        return exceptions.stream().filter(Objects::nonNull).toList();
    }

    private FieldError validateMemberId(String memberId, Long idIfEditing) {
        Optional<Member> found = memberRepository.findByMemberId(memberId);
        if (found.isPresent()) {
            // 본인 정보 수정 시 패스
            if (found.get().getId().equals(idIfEditing)) return null;

            FieldError error = new FieldError("member", "memberId", memberId, false, null,
                    null, "이미 사용중인 아이디입니다.");
            log.error("validation memberId [{}] failed: Already Exist", memberId);

            return error;
        }
        return null;
    }

    private FieldError validateNickname(String name, Long idIfEditing) {
        Optional<Member> found = memberRepository.findAll().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();

        if (found.isPresent()) {
            // 본인 정보 수정 시 패스
            if (found.get().getId().equals(idIfEditing)) return null;

            FieldError error = new FieldError("member", "name", name, false, null,
                    null, "이미 사용중인 닉네임입니다.");
            log.error("validation name [{}] failed: Already Exist", name);
            return error;
        }
        return null;
    }


}
