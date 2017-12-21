package com.naren.webcrawler;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naren.webcrawler.core.SharedService;
import com.naren.webcrawler.core.WebCrawler;

@Configuration
@RestController
@ComponentScan({ "com.naren.webcrawler", "com.naren.webcrawler.*" })
@SpringBootApplication
public class WebcrawlerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebcrawlerApplication.class, args);
	}

	@Autowired
	WebCrawler webCrawler;

	@Autowired
	SharedService sharedService;

	@RequestMapping("/{protocol}/{url}")
	public Map<String, Set<String>> crawl(@PathVariable String protocol, @PathVariable String url)
			throws IOException, URISyntaxException {

		URL domainUrl = new URL(protocol + "://" + url);
		sharedService.result.clear();
		sharedService.domain = domainUrl;
		return webCrawler.crawl(domainUrl.toString());
	}
}
