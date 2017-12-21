/**
 * 
 */
package com.naren.webcrawler.core;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.validator.routines.UrlValidator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Seed {

	private String baseUrl;

	@Autowired
	SharedService sharedService;

	Document document;

	private Set<String> links = new HashSet<>();

	public Set<String> readAssets() {
		return document.select("[src]").stream().map(Element::text).collect(Collectors.toSet());
	}

	public Set<String> readLinks() {
		links = document.select("a[href]").stream().filter(e -> new UrlValidator().isValid(e.attr("href")))
				.map(e -> relativeUrlCheck(e.attr("href"))).collect(Collectors.toSet());
		return links;
	}

	private String relativeUrlCheck(String url) {
		return url.startsWith("/") ? baseUrl + url : url;
	}

	private boolean validateSameDomain(String url) {
		try {
			return new URL(url).getHost().substring(beginIndex).equalsIgnoreCase(new URL(sharedService.getDomain().toString()).getHost());
		} catch (MalformedURLException e) {
			System.out.println(url + " - " + e.getMessage());
			return false;
		}
	}

	private void frontTier(String url) {
		if (!sharedService.result.containsKey(url) && validateSameDomain(url))
			crawlFront(url);
	}

	private Set<String> crawl(String url) throws IOException {
		baseUrl = url;
		try {
			if (!sharedService.result.containsKey(url)) {
				document = Jsoup.connect(url).get();
				Set<String> resources = new HashSet<>();
				resources.addAll(readAssets());
				resources.addAll(readLinks());
				sharedService.result.put(url, resources);
			}
		} catch (IOException e) {
			System.out.println(url + " - " + e.getMessage());
			sharedService.result.put(url, new HashSet<>());
		}
		return links;
	}

	SharedService crawlFront(String url) {
		try {
			crawl(url).stream().forEach(link -> this.frontTier(link));
		} catch (IOException e) {
			System.out.println(url + " - " + e.getMessage());
			sharedService.result.put(url, null);
		}
		return sharedService;
	}

	Map<String, Set<String>> get() {
		return sharedService.result;
	}

}
