package com.sinam7.bulletinboard.domain.member;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Member {

    private Long id;
    private String memberId;
    private String password;
    private String name;

}
