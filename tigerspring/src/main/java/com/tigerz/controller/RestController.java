package com.tigerz.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.Gson;
import com.tigerz.pojo.StandardResponse;
import com.tigerz.pojo.TournamentContent;

@Controller
@RequestMapping("/rest")
public class RestController {

	//请求样例格式
	@RequestMapping("/getAddress")
	public @ResponseBody StandardResponse getAddress(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "content", required = true) String content) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("content", content);
		Gson jb = new Gson();
		String jsonStr = jb.toJson(map);
		StandardResponse sr = new StandardResponse();
		sr.setJsonData(jsonStr);
		return sr;
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public String get(@PathVariable("id") Integer id) {
		System.out.println("get" + id + 4);
		return "/demo";
	}
	

	@ResponseBody
	@RequestMapping("/json")
	public TournamentContent get() throws ServletException, IOException {
		TournamentContent u = new TournamentContent();
		u.setId(1);
		u.setName("jayjay");
		u.setAuthor("auther-li");
		return u;
	}

	@RequestMapping("/json2")
	public void json2(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write("{'name':'wjc'}");
	}

	@RequestMapping("/person")
	public String toPerson(String name, double age) {
		System.out.println(name + " " + age);
		return "/demo";
	}

}
