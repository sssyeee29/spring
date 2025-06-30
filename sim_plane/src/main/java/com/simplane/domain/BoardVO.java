package com.simplane.domain;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@Builder // Data랑 builder를 해서 mapper테스트에서 builder 사용가능
@AllArgsConstructor
@NoArgsConstructor
//게시판
public class BoardVO {

    private Long boardid;
    private String writer;
    private String title;
    private String content;
    private String imagePath;
    private Date regDate;

}
