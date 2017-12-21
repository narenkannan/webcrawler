/**
 * 
 */
package com.naren.webcrawler.core;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author nk
 *
 */

@Component
@Scope("prototype")
public class WebCrawler {

	@Autowired
	Seed seed;

	private String baseUrl;

	private String domain;

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	Map<String, Seed> siteMap = new HashMap<String, Seed>();

	public Map<String, Set<String>> crawl(String url) throws URISyntaxException, IOException {
		baseUrl = url.toString();
		return seed.crawlFront(baseUrl).get();
	}
}