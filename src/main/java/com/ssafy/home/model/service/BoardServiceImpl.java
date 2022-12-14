package com.ssafy.home.model.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.home.model.dto.BoardDto;
import com.ssafy.home.model.repo.BoardRepo;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private SqlSession sqlSession;

	
	@Override
	public boolean writeArticle(BoardDto boardDto) throws Exception {
		
		if(boardDto.getSubject() == null || boardDto.getContent() == null) {
			throw new Exception();
		}
		return sqlSession.getMapper(BoardRepo.class).writeArticle(boardDto) == 1;
	}

	@Override
	public List<BoardDto> listArticle() throws SQLException {

		return sqlSession.getMapper(BoardRepo.class).listArticle();
	}

	@Override
	public BoardDto getArticle(int articleno) throws Exception {
		return sqlSession.getMapper(BoardRepo.class).getArticle(articleno);
	}
	
	@Override
	public void updateHit(int articleno) throws Exception {
		sqlSession.getMapper(BoardRepo.class).updateHit(articleno);
	}

	@Override
	@Transactional
	public boolean modifyArticle(BoardDto boardDto) throws Exception {
		return sqlSession.getMapper(BoardRepo.class).modifyArticle(boardDto) == 1;
	}

	@Override
	@Transactional
	public boolean deleteArticle(int articleno) throws Exception {
		return sqlSession.getMapper(BoardRepo.class).deleteArticle(articleno) == 1;
	}

	@Override
	public boolean deleteArticleByUserId(String userId) throws Exception{
		
		return sqlSession.getMapper(BoardRepo.class).deleteArticleByUserId(userId) == 1;
	}
}