package com.zerock.w2.domain;

import lombok.*;

@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberVO {
    private String mid;
    private String mpw;
    private String mname;
    private String uuid;

}
