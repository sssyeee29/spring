package com.simplane.mapper;

import com.simplane.domain.TestVO;

public interface TestMapper {

    public void create(TestVO test); //테스트 등록
    
    public int delete(Long testid); //테스트 삭제

}
