package com.simplane.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder // Data랑 builder를 해서 mapper테스트에서 builder 사용가능
@AllArgsConstructor
@NoArgsConstructor
//게시판
public class BoardVO {

    private Long boardid;
    private String title;
    private String content;
    private String imagePath;
    private Date regDate;

}
