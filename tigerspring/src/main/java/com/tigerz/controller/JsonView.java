package com.tigerz.controller;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

import com.google.gson.Gson;

public class JsonView extends AbstractView {
	@Override
	protected void renderMergedOutputModel(Map<String, Object> arg0,
			HttpServletRequest arg1, HttpServletResponse arg2) throws Exception {
		arg2.setContentType("text/json; charset=UTF-8");
		PrintWriter out = arg2.getWriter();
		Gson jb = new Gson(); 
        out.print(jb.toJson(arg0));  
	}

}
