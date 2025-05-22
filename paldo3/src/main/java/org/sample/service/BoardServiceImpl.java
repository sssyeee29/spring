package org.sample.service;

import java.util.ArrayList;
import java.util.List;

import org.sample.domain.BoardVO;
import org.sample.domain.Criteria;
import org.sample.domain.ImgPathVO;
import org.sample.domain.PostDTO;
import org.sample.domain.ProductVO;
import org.sample.domain.UserVO;
import org.sample.mapper.BoardMapper;
import org.sample.mapper.ImgPathMapper;
import org.sample.mapper.ProductMapper;
import org.sample.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Builder;

@Service
public class BoardServiceImpl implements BoardService {

   @Autowired
    private BoardMapper boardMapper;
   @Autowired
    private ImgPathMapper imgPathMapper;
   @Autowired
    private ProductMapper productMapper;
   @Autowired
    private UserMapper userMapper;

    // 의존성 주입 (Setter Injection)
    public void setBoardMapper(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    public void setImgPathMapper(ImgPathMapper imgPathMapper) {
        this.imgPathMapper = imgPathMapper;
    }

    public void setProductMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    // 게시글 등록
    @Transactional
    public void register(BoardVO board, UserVO user, ProductVO product, List<ImgPathVO> imgPaths) {
        userMapper.insertUser(user);  // 사용자 등록
        productMapper.insertProduct(product);  // 상품 등록

        board.setUserid(user.getUserid());
        board.setProductid(product.getProductid());
        boardMapper.insertBoard(board);  // 게시글 등록

        for (ImgPathVO img : imgPaths) {
            img.setProductid(product.getProductid());
            imgPathMapper.insertImgPath(img);  // 이미지 등록
        }
    }
  
    @Override
    public BoardVO get(Long boardid) {
        return boardMapper.readBoard(boardid);  // 게시글 조회
    }

    @Override
    public boolean modify(BoardVO vo) {
        return boardMapper.updateBoard(vo) > 0;  // 게시글 수정
    }

    @Transactional
    @Override
    public boolean remove(Long boardid) {
        // 1. 게시글 조회 (상품 ID, 사용자 ID 확인용)
        BoardVO board = boardMapper.readBoard(boardid);
        if (board == null) return false;

        Long productId = board.getProductid();
        Long userId = board.getUserid();

        // 2. 이미지 먼저 삭제
        imgPathMapper.deleteImgPath(productId);

        // 3. 상품 삭제
        productMapper.deleteProduct(productId);

        // 4. 게시글 삭제
        return boardMapper.deleteBoard(boardid) > 0;
    }

    @Override
    public List<BoardVO> getList(Criteria cri) {
        return boardMapper.getList(cri);  // 게시글 리스트 조회
    }

    @Override
    public int getTotal(Criteria cri) {
        return boardMapper.countTotal(cri);  // 전체 게시글 수 조회
    }

    @Override
    public void registerImgPath(ImgPathVO imgpath) {
        imgPathMapper.insertSelectKeyImgPath(imgpath);  // 이미지 경로 등록
    }

    @Override
    public void registerProduct(ProductVO product) {
        productMapper.insertProduct(product);  // 상품 등록
    }

    @Override
    public void registerUser(UserVO user) {
        userMapper.insertUser(user);  // 사용자 등록
    }

    @Override
    public List<ImgPathVO> getImgPaths(Long productid) {
        return imgPathMapper.readImgPath(productid);  // 이미지 경로 리스트 조회
    }

   @Override
   public List<PostDTO> getPostList(Criteria cri) {
      List<BoardVO> boards = boardMapper.getList(cri);
       List<PostDTO> posts = new ArrayList<>();

       for (BoardVO board : boards) {
           UserVO user = userMapper.readUser(board.getUserid());
           ProductVO product = productMapper.readProduct(board.getProductid());
           List<ImgPathVO> imgPaths = imgPathMapper.readImgPath(board.getProductid());

           PostDTO post = PostDTO.builder()
               .board(board)
               .user(user)
               .product(product)
               .imgPaths(imgPaths)
               .build();

           posts.add(post);
       }

       return posts;
   }
}