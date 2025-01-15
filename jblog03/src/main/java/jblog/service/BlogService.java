package jblog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jblog.repository.BlogRepository;
import jblog.vo.CategoryVo;
import jblog.vo.PostVo;

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

	public List<CategoryVo> selectCategories(String blogId) {
		return blogRepository.selectCategories(blogId);
	}
	
	public List<PostVo> findPostsByCategoryId(Long categoryId) {
		return blogRepository.findPostsByCategoryId(categoryId);
	}
	
	public PostVo findPostById(Long postId) {
		return blogRepository.findPostById(postId);
	}
	

}
