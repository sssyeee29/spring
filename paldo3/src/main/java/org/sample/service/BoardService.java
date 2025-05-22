package org.sample.service;

import java.util.List;

import org.sample.domain.BoardVO;
import org.sample.domain.Criteria;
import org.sample.domain.ImgPathVO;
import org.sample.domain.PostDTO;
import org.sample.domain.ProductVO;
import org.sample.domain.UserVO;

public interface BoardService {
   
   public List<PostDTO> getPostList(Criteria cri);

    // 게시글 등록
   public void register(BoardVO board, UserVO user, ProductVO product, List<ImgPathVO> imgList);

    // 게시글 1건 조회 (기본키 boardid 사용)
    public BoardVO get(Long boardid);

    // 게시글 수정
    public boolean modify(BoardVO vo);

    // 게시글 삭제 (기본키 boardid 사용)
    public boolean remove(Long boardid);

    // 검색/페이징 포함 전체 리스트 조회
    public List<BoardVO> getList(Criteria cri);

    // 검색/조건 포함 전체 게시글 수 조회
    public int getTotal(Criteria cri);
    
    // 이미지 경로 등록(ImgPathVO 등록 메서드 추가)
    public void registerImgPath(ImgPathVO imgpath);

    // 상품 등록(ProductVO 등록 메서드 추가)
    public void registerProduct(ProductVO product);

    // 사용자 등록(UserVO 등록 메서드 추가)
    public void registerUser(UserVO user);
    
    // 각각의 VO에 대해 리스트 조회
    public List<ImgPathVO> getImgPaths(Long productid);
}  