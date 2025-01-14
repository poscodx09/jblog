package jblog.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jblog.service.BlogService;
import jblog.vo.PostVo;

@Controller
@RequestMapping("/{userId:(?!assets).*}")
public class BlogController {
	
	private BlogService blogService;
	
	public BlogController(BlogService blogService) {
		this.blogService = blogService;
	}
	
	@RequestMapping({"", "/{path1}", "/{path1}/{path2}"})
	public String main(
			@PathVariable("userId") String userId,
			@PathVariable("path1") Optional<Long> path1,
			@PathVariable("path2") Optional<Long> path2)
	{
		Long categoryId = 0L;
		Long postId = 0L;
		
		if (path2.isPresent()) {
			postId = path2.get();
			categoryId = path1.get();
		} else if (path1.isPresent()) {
			categoryId = path1.get();
		}
		
		// 서비스에서 디폴트 세팅
		// categoryId == 0L -> default categoryId set
		// postId == 0L -> default postId set
		
		
		
		return "blog/main";
	}
	
//	@Auth
	@RequestMapping(value="/admin/default", method=RequestMethod.GET)
	public String admin() {
		return "blog/admin-default";
	}
	
//	@Auth
	@RequestMapping(value="/admin/write", method=RequestMethod.GET)
	public String write() {
		return "blog/admin-write";
	}
	
//	@Auth
	@RequestMapping(value="/admin/write", method=RequestMethod.POST)
	public String write(
			@PathVariable(value="userId") String userId,
			PostVo vo,
			Model model) {
		blogService.addPost(vo);
		return "redirect:/blog/" + userId + "/admin/write";
	}
	
//	@Auth
	@RequestMapping(value="/admin/category", method=RequestMethod.GET)
	public String category() {
		return "blog/admin-category";
	}
}
