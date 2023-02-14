package com.sinam7.bulletinboard;

import com.sinam7.bulletinboard.domain.article.Article;
import com.sinam7.bulletinboard.domain.article.ArticleRepository;
import com.sinam7.bulletinboard.domain.member.Member;
import com.sinam7.bulletinboard.domain.member.MemberRepository;
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
        Member member = new Member();
        member.setMemberId("test");
        member.setPassword("test");
        member.setName("테스터");

        memberRepository.save(member);

        Article article = new Article();
        article.setTitle("testTitle");
        article.setContent("testContent1\ntestContent2");
        article.setWriter(member);

        articleRepository.save(article);
    }
}
