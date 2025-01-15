package jblog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import jblog.security.Auth;
import jblog.security.AuthUser;
import jblog.service.BlogService;
import jblog.vo.CategoryVo;
import jblog.vo.PostVo;
import jblog.vo.UserVo;

@Controller
@RequestMapping("/{userId:(?!assets).*}")
public class BlogController {
	
	private BlogService blogService;
	
	public BlogController(BlogService blogService) {
		this.blogService = blogService;
	}
	
	@RequestMapping({"", "/{path1}", "/{path1}/{path2}"})
	public String main(
			@AuthUser UserVo authUser,
			@PathVariable("userId") String userId,
			@PathVariable("path1") Optional<Long> path1,
			@PathVariable("path2") Optional<Long> path2,
			Model model)
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
		
		PostVo post = blogService.findPostById(postId);
		List<PostVo> postList = blogService.findPostsByCategoryId(categoryId);
		List<CategoryVo> categoryList = blogService.selectCategories(authUser.getId());
		
		model.addAttribute("post", post);
		model.addAttribute("postList", postList);
		model.addAttribute("categoryList", categoryList);
		
		
		return "blog/main";
	}
	
	@Auth
	@GetMapping("/admin")
	public String adminDefault() {
		return "blog/admin-default";
	}
	
	
	@Auth
	@GetMapping("/admin/write")
	public String adminWrite(
			@AuthUser UserVo authUser,
			Model model) {
		List<CategoryVo> list = blogService.selectCategories(authUser.getId());
		model.addAttribute("categoryList", list);
		return "blog/admin-write";
	}
	
	@Auth
	@PostMapping("/admin/write")
	public String write(
			@PathVariable(value="userId") String userId,
			@ModelAttribute @Valid PostVo postVo,
			Model model) {
		System.out.println(userId);
		System.out.println("post" + postVo);
		blogService.addPost(postVo);
		return "redirect:/" + userId + "/admin";
	}
	
	@Auth
	@GetMapping("/admin/category")
	public String category(@AuthUser UserVo authUser, Model model) {
		List<CategoryVo> list = blogService.selectCategories(authUser.getId());
		model.addAttribute("categoryList", list);
		return "blog/admin-category";
	}
	
	@Auth
	@PostMapping("/admin/category")
	public String category(
			@AuthUser UserVo authUser,
			@PathVariable(value="userId") String userId,
			@ModelAttribute @Valid CategoryVo categoryVo,
			BindingResult result,
			Model model) {
		
		if (result.hasErrors()) {
			model.addAllAttributes(result.getModel());
			return "blog/admin-category";
		}
		categoryVo.setBlogId(authUser.getId());
		blogService.addCategory(categoryVo);
		return "redirect:/" + userId + "/admin/category";
	}
}
