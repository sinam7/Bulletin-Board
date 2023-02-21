package com.sinam7.bulletinboard;

import com.sinam7.bulletinboard.domain.article.Article;
import com.sinam7.bulletinboard.domain.article.ArticleRepository;
import com.sinam7.bulletinboard.domain.member.Member;
import com.sinam7.bulletinboard.domain.member.MemberRepository;
import com.sinam7.bulletinboard.domain.member.dto.MemberFormDTO;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WebDataInit {

    private final MemberRepository memberRepository;
    private final ArticleRepository articleRepository;

    @PostConstruct
    public void init() {
        MemberFormDTO memberFormDTO = new MemberFormDTO("test", "test", "테스터");
        Member member = MemberFormDTO.buildMember(MemberRepository.addSequence(), memberFormDTO);

        memberRepository.save(member);

        Article article = new Article();
        article.setTitle("testTitle");
        article.setContent("testContent1\ntestContent2");
        article.setWriter(member);

        articleRepository.save(article);
    }
}
