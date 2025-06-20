package com.simplane.mapper;

import com.simplane.domain.MemberVO;

public interface MemberMapper {

    public void create(MemberVO member); //회원 등록

    public int delete(Long memberid); //회원 삭제
}
