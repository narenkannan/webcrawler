package com.naren.webcrawler;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.naren.webcrawler.core.SharedService;
import com.naren.webcrawler.core.WebCrawler;

@EnableWebMvc
@Configuration
@RestController
@SpringBootApplication
@ComponentScan({ "com.naren.webcrawler", "com.naren.webcrawler.*" })
public class WebcrawlerApplication extends WebMvcConfigurerAdapter {

	@Autowired
	WebCrawler webCrawler;

	@Autowired
	SharedService sharedService;

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		GsonHttpMessageConverter msgConverter = new GsonHttpMessageConverter();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		msgConverter.setGson(gson);
		converters.add(msgConverter);
	}

	public static void main(String[] args) {
		SpringApplication.run(WebcrawlerApplication.class, args);
	}

	@RequestMapping("/{protocol}/{url}")
	public Map<String, Set<String>> crawl(@PathVariable String protocol, @PathVariable String url)
			throws IOException, URISyntaxException {
		URL domainUrl = new URL(protocol + "://" + url);
		sharedService.result.clear();
		sharedService.domain = domainUrl;
		return webCrawler.crawl(domainUrl.toString());
	}
}
