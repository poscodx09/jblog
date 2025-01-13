package jblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jblog.service.BlogService;
import jblog.vo.PostVo;

@Controller
@RequestMapping("/blog")
public class BlogController {
	
	private BlogService blogService;
	
	public BlogController(BlogService blogService) {
		this.blogService = blogService;
	}
	
	@RequestMapping(value="/{userId}", method=RequestMethod.GET)
	public String main() {
		return "blog/main";
	}
	
	@RequestMapping(value="/{userId}/admin/default", method=RequestMethod.GET)
	public String admin() {
		return "blog/admin-default";
	}
	
	@RequestMapping(value="/{userId}/admin/write", method=RequestMethod.GET)
	public String write() {
		return "blog/admin-write";
	}
	
	@RequestMapping(value="/{userId}/admin/write", method=RequestMethod.POST)
	public String write(
			@PathVariable(value="userId") String userId,
			PostVo vo,
			Model model) {
		blogService.addPost(vo);
		return "redirect:/blog/" + userId + "/admin/write";
	}
	
	@RequestMapping(value="/{userId}/admin/category", method=RequestMethod.GET)
	public String category() {
		return "blog/admin-category";
	}
}
