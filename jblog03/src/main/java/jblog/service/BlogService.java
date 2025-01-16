package jblog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jblog.repository.BlogRepository;
import jblog.vo.BlogVo;
import jblog.vo.CategoryVo;
import jblog.vo.PostVo;
import jblog.vo.UserVo;

@Service
public class BlogService {
	
	private BlogRepository blogRepository;
	
	public BlogService(BlogRepository blogRepository) {
		this.blogRepository = blogRepository;
	}
	
	public void addPost(PostVo postVo) {
		blogRepository.insertPost(postVo);
	}
	
	public void addCategory(CategoryVo categoryVo) {
		blogRepository.insertCategory(categoryVo);
	}
	
	public void addDefaultCategory(UserVo userVo) {
		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setBlogId(userVo.getId());
		categoryVo.setName("미분류");
		blogRepository.insertCategory(categoryVo);
	}

	public List<CategoryVo> selectCategories(String blogId) {
		return blogRepository.selectCategories(blogId);
	}
	
	public List<PostVo> findPostsByCategoryId(String userId, Long categoryId) {
		if (categoryId == 0) {
			return blogRepository.findAllposts(userId);
		}
		return blogRepository.findPostsByCategoryId(userId, categoryId);
	}
	
	public PostVo findPostById(String userId, Long postId, Long categoryId) {
		// 카테고리가 선택되지 않은 경우 전체 글 중 최신 글 조회
		if (categoryId == 0) {
			return blogRepository.findFirstPost(userId);
		}
		// 카테고리만 선택된 경우, 카테고리 내 첫번째 글 조회
		else if (postId == 0) {
			return blogRepository.findFirstPostOfCategory(userId, categoryId);
		}
		
		return blogRepository.findPostById(postId);
	}

	public BlogVo getBlog(String blogId) {
		return blogRepository.findBlogById(blogId);
	}

	public void updateBlog(BlogVo blogVo) {
		blogRepository.updateBlog(blogVo);
	}

	public void addBlog(String id) {
		BlogVo vo = new BlogVo();
		vo.setId(id);
		vo.setTitle(id + "의 블로그");
		vo.setProfile("/assets/upload-images/20250155839425.jpg");
		blogRepository.insertBlog(vo);
	}

	public void deleteCategory(Long categoryId) {
		blogRepository.deleteCategory(categoryId);
	}

	public void deleteAllPostByCategoryId(String categoryId) {
		blogRepository.deleteAllPostByCategoryId(categoryId);
		
	}

}
