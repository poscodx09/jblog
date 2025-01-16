package jblog.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import jblog.vo.BlogVo;
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
		return sqlSession.selectList("blog.selectCategories", blogId);
	}

	// 카테고리 내 게시글 조회
	public List<PostVo> findPostsByCategoryId(Long categoryId) {
		return sqlSession.selectList("blog.findPostsByCategoryId", categoryId);
	}

	// 게시글 조회
	public PostVo findPostById(Long postId) {
		return sqlSession.selectOne("blog.findPostById", postId);
	}

	public BlogVo findBlogById(String blogId) {
		return sqlSession.selectOne("blog.findBlogById", blogId);
	}

	public void updateBlog(BlogVo blogVo) {
		System.out.println("blog3" + blogVo);
		sqlSession.update("blog.updateBlog", blogVo);
	}
}
