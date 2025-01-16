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

	// 카테고리 내 게시글 목록 조회
	public List<PostVo> findPostsByCategoryId(String userId, Long categoryId) {
		return sqlSession.selectList("blog.findPostsByCategoryId", Map.of("userId", userId, "categoryId", categoryId));
	}
	
	// 전체 게시글 목록 조회
	public List<PostVo> findAllposts(String userId) {
		return sqlSession.selectList("blog.findAllPosts", userId);
	}

	// 게시글 조회
	public PostVo findPostById(Long postId) {
		return sqlSession.selectOne("blog.findPostById", postId);
	}

	// 블로그 정보 조회
	public BlogVo findBlogById(String blogId) {
		return sqlSession.selectOne("blog.findBlogById", blogId);
	}

	// 블로그 정보 수정
	public void updateBlog(BlogVo blogVo) {
		sqlSession.update("blog.updateBlog", blogVo);
	}

	// 전체 글 중 첫번째 게시글 조회
	public PostVo findFirstPost(String userId) {
		return sqlSession.selectOne("blog.findFirstPost", userId);
	}

	// 카테고리에서 첫번째 게시글 조회
	public PostVo findFirstPostOfCategory(String userId, Long categoryId) {
		return sqlSession.selectOne("blog.findFirstPostOfCategory", Map.of("userId", userId, "categoryId", categoryId));
	}

	// 블로그 생성
	public void insertBlog(BlogVo blogVo) {
		sqlSession.insert("blog.insertBlog", blogVo);
	}

	public void deleteCategory(Long categoryId) {
		sqlSession.insert("blog.deleteCategoryById", categoryId);
		
	}

	public void deleteAllPostByCategoryId(String categoryId) {
		sqlSession.insert("blog.deletePostByCategoryId", categoryId);
		
	}

}
