package com.tigerz.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tigerz.exception.CustomException;
import com.tigerz.pojo.Student;
import com.tigerz.pojo.TournamentContent;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * DemoController
 * 
 * @Desc:
 * @Company: TigerZ
 * @author Wang Jingci
 * @date 2016年11月23日 下午8:23:40
 */
@Controller
public class DemoController {

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(HttpServletRequest request,
			HttpServletResponse response, Model model, HttpSession session) {

		return "demo";
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {
		return "test";
	}

	@RequestMapping(value = "/param", method = RequestMethod.GET)
	public String param(
			@RequestParam(value = "id", required = true, defaultValue = "1") Integer itemId) {
		return "test";
	}

	@RequestMapping(value = "/forward", method = RequestMethod.GET)
	public String go(HttpServletRequest request, HttpServletResponse response) {
		// request.getRequestDispatcher("test").forward(request, response);
		// response.sendRedirect("test");
		// return "redirect:/test"
		return "Redirect:test";
	}

	// 校验方法
	// 前面有@Validated,后面紧跟BindingResult bindingResult，成对出现
	@RequestMapping(value = "/validate")
	public String validate(@Validated TournamentContent tc,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			List<ObjectError> errors = bindingResult.getAllErrors();
			for (ObjectError objectError : errors) {
				System.out.println(objectError.getDefaultMessage());
			}
		}
		return "test";
	}

	// 测试当请求是Json的情况，比如ajax通过post传递json进来
	// 重要结论就是必须要用一个pojo类(必须要有空构造器)来承接这个json
	@RequestMapping(value = "/requestJson")
	public @ResponseBody Student requestJson(@RequestBody Student st) {
		System.out.println("==========================");
		return st;
	}

	// 通过下面的测试发现，如果传进来的是json格式，必须要用pojo类来接收,普通的变量是接收不到json信息的
	@RequestMapping(value = "/requestJson2")
	public @ResponseBody Map<String, Object> requestJson(String name,
			Integer age, Integer height) throws CustomException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("age", age);
		map.put("height", height);
		return map;
	}

	// 测试通过post以k-v的方式传入参数，可以自动灌入pojo对象
	@RequestMapping(value = "/responseJson")
	public @ResponseBody Student responseJson(Student st) {
		System.out.println("==========================");
		return st;
	}

	// 测试以上面的方法传入参数，完全可以以各个变量来绑定传参
	@RequestMapping(value = "/responseJson2")
	public @ResponseBody Student responseJson2(String name, int age, int height) {
		System.out.println("==========================");
		Student stu = new Student();
		stu.setAge(age);
		stu.setHeight(height);
		stu.setName(name);
		return stu;
	}

	// Restful接口中的路径绑定变量
	@RequestMapping(value = "/testrest/{action}/{id}")
	public @ResponseBody Student testRest(
			@PathVariable(value = "action") String action,
			@PathVariable(value = "id") int id) {
		return null;
	}
}
