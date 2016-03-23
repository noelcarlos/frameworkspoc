package com.allianz.drdc24.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.allianz.drdc24.services.FileRepository;

@Controller
public class DummyPiwikImplementation {
	@Autowired FileRepository fileRepository;
	
	@RequestMapping(value = "/piwik.js", method = RequestMethod.GET)
	@ResponseBody String javaScript(HttpServletRequest request) {
		return fileRepository.getJavaScriptSource();
	}
	
	@RequestMapping(value = "/piwik.php", method = RequestMethod.GET)
	@ResponseBody String logEntry(HttpServletRequest request) {
//		System.out.println("URL:" + request.getParameter("url"));
//		System.out.println("res:" + request.getParameter("res"));
//		System.out.println("idsite:" + request.getParameter("idsite"));
		return "{\"success\":\"true\"}";
	}
	
	/*
	@RequestMapping(value = "/async-piwik.php", method = RequestMethod.GET)
	@ResponseBody Callable<String> asyncEntry(HttpServletRequest request) {
//		System.out.println("URL:" + request.getParameter("url"));
//		System.out.println("res:" + request.getParameter("res"));
//		System.out.println("idsite:" + request.getParameter("idsite"));
		Callable<String> asyncTask = new Callable<String>() {
		      @Override
		      public String call() throws Exception {
		  		return "{\"success\":\"true\"}";
		      }
		};
		return asyncTask;
	}*/
}
