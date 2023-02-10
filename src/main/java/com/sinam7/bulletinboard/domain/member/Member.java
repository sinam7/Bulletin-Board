package com.sinam7.bulletinboard.domain.member;

import lombok.Data;

@Data
public class Member {

    private Long id;
    private String memberId;
    private String password;
    private String name;

}
