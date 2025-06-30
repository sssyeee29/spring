package com.simplane.mapper;

import com.simplane.domain.AuthVO;
import com.simplane.domain.MemberVO;
import org.apache.ibatis.annotations.Param;

public interface MemberMapper {

    MemberVO readByUserid(@Param("userid") String userid);

    int insert(MemberVO member);

    int insertAuth(AuthVO auth);

    public void create(MemberVO member); //회원 등록

    public int delete(Long memberid); //회원 삭제
}
