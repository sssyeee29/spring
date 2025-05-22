package org.sample.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardVO {
    private long pno;             // 상품 번호
    private String title;         // 게시글 제목
    private String description;   // 상품 설명
    private long price;           // 가격
    private String product;       // 기기명
    private String condition;     // 상태
    private String imgPath;       // 이미지 경로 (✅ camelCase 통일)
    private String nickname;      // 작성자 닉네임
    private String pwd;           // 비밀번호
    private String status;        // 판매 상태
    private Date regDate;         // 등록일
    private String brand;         // 브랜드명
	public void setUserId(String userId) {
		// TODO Auto-generated method stub
		
	}
}
