package jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import jblog.vo.CategoryVo;
import jblog.vo.PostVo;

@Repository
public class BlogRepository {
	private SqlSession sqlSession;
	
	public BlogRepository(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	// 게시글 작성
	public int insertPost(PostVo vo) {
		try {
			return sqlSession.insert("blog.insertPost", vo);	
		} catch(RuntimeException e) {
			System.out.println("error:" + e);
		}
		return 0;
	}
	
	// 카테고리 추가
	public int insertCategory(CategoryVo vo) {
		try {
			return sqlSession.insert("blog.insertCategory", vo);	
		} catch(RuntimeException e) {
			System.out.println("error:" + e);
		}
		return 0;
	}
	
	// 카테고리 목록 조회
	public List<CategoryVo> selectCategories(String blogId) {
		System.out.println("blogId" + blogId);
		return sqlSession.selectList("blog.selectCategories", blogId);
	}
}
