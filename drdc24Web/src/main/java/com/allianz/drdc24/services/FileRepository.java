package com.allianz.drdc24.services;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;

@Controller
public class FileRepository {
	@Autowired ApplicationContext applicationContext;
	@Value("${piwik.source}") String PIWIK_SOURCE;
	
	@Cacheable(value="fileCacheRepository", key="{#root.methodName}")
	public String getJavaScriptSource() {
		Resource resource = applicationContext.getResource(PIWIK_SOURCE);
		//Resource resource = new ClassPathResource("piwik.js");
		try {
			System.out.println("Reading JS");
			return IOUtils.toString(resource.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}
}
