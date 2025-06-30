package com.simplane.domain;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
//테스트
public class TestVO {

    private Long testid; //테스트 번호
    private String testname; //테스트 이름
}
