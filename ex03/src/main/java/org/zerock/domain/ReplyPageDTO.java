package org.zerock.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReplyPageDTO {

	private int replyCnt; //목록마다 해당하는 댓글의 갯수 
	private List<ReplyVO> list;
}
