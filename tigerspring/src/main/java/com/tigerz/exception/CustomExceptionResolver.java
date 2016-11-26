package com.tigerz.exception;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.tigerz.controller.JsonView;

/**
 * 
 * CustomExceptionResolver
 * @Desc: 全局异常处理
 * @Company: TigerZ
 * @author Wang Jingci
 * @date 2016年11月24日 上午11:39:11
 */
public class CustomExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		// TODO Auto-generated method stub
//		CustomException customException = null;
//		if (ex instanceof CustomException) {
//			customException = (CustomException)ex;
//		}else{
//			customException = new CustomException("未知错误");
//		}
		
		System.out.println("tigersay:"+ex.getMessage());
		
		JsonView view = new JsonView();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("isOk", "no");
		modelAndView.addObject("errorMessage", ex.getMessage());
		modelAndView.setView(view);
		
		
		return modelAndView;
	}
	
}
