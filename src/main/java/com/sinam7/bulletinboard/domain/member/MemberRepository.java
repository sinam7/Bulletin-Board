package com.sinam7.bulletinboard.domain.member;

import com.sinam7.bulletinboard.domain.member.dto.MemberFormDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemberRepository {

    private static final Map<Long, Member> memberStore = new ConcurrentHashMap<>();
    private static Long sequence = 0L;

    public Long save(MemberFormDTO memberFormDTO) {
        Member member = MemberFormDTO.buildMember(++sequence, memberFormDTO);
        memberStore.put(member.getId(), member);

        return member.getId();
    }

    public Member findById(Long id) {
        return memberStore.get(id);
    }

    public Optional<Member> findByMemberId(String memberId) {
        return findAll().stream()
                .filter(member -> member.getMemberId().equals(memberId))
                .findFirst();
    }

    public void updateMember(Long id, MemberFormDTO memberFormDTO) {
        Member member = MemberFormDTO.buildMember(id, memberFormDTO);
        memberStore.put(id, member);
    }

    public void deleteMember(Member member) {
        Long id = member.getId();
        memberStore.remove(id);
    }

    public List<Member> findAll() {
        return new ArrayList<>(memberStore.values());
    }

}
