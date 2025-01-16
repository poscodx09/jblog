package jblog.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jblog.service.BlogService;
import jblog.vo.BlogVo;

public class BlogInterceptor implements HandlerInterceptor {

	private LocaleResolver localeResolver;
	private BlogService blogService;
	
	public BlogInterceptor(LocaleResolver localeResolver, BlogService blogService) {
		this.localeResolver = localeResolver;
		this.blogService = blogService;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String requestURI = request.getRequestURI();
        String[] uriParts = requestURI.split("/"); 
        String blogId = (uriParts.length > 2) ? uriParts[2] : null;
        
		BlogVo blogVo = (BlogVo) request.getServletContext().getAttribute("blogVo");
		if (blogVo == null) {
			blogVo = blogService.getBlog(blogId);
			request.getServletContext().setAttribute("blogVo", blogVo);
		}
		
		request.setAttribute("blogVo", blogService.getBlog(blogId));
		
		return true;
	}

}
