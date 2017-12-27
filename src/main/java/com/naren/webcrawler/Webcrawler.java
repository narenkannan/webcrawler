package com.naren.webcrawler;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@EnableWebMvc
@Configuration
@RestController
@SpringBootApplication
@Scope("prototype")
public class Webcrawler extends WebMvcConfigurerAdapter implements Filter {

	@Autowired
	Seed seed;

	private static final org.apache.log4j.Logger logger = LogManager.getLogger(Webcrawler.class);

	public static void main(String[] args) {
		SpringApplication.run(Webcrawler.class, args);
	}

	@RequestMapping("/")
	public String crawl() throws IOException, URISyntaxException {
		return "success";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Map<String, Set<String>> crawl(@RequestBody String url) throws IOException, URISyntaxException {
		URL domainUrl = new URL(url);
		return seed.crawl(domainUrl.toString());
	}

	@RequestMapping("/{protocol}/{url}")
	public Map<String, Set<String>> crawl(@PathVariable String protocol, @PathVariable String url)
			throws IOException, URISyntaxException {
		URL domainUrl = new URL(protocol + "://" + url);
		return seed.crawl(domainUrl.toString());
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		GsonHttpMessageConverter msgConverter = new GsonHttpMessageConverter();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		msgConverter.setGson(gson);
		converters.add(msgConverter);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "*");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "authorization, content-type, xsrf-token");

		if ("OPTIONS".equals(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			chain.doFilter(req, res);
		}
	}

}
