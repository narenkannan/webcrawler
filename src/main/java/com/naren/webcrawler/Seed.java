/**
 * 
 */
package com.naren.webcrawler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.apache.commons.validator.routines.UrlValidator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

@Component
public class Seed {

	private String baseUrl;

	Document document;

	public Map<String, Set<String>> result = new ConcurrentHashMap<>();

	public Map<String, Set<String>> get() {
		return result;
	}

	private Set<String> links = new HashSet<>();

	public Set<String> readAssets() {
		return document.select("[src]").parallelStream().map(Element::text).collect(Collectors.toSet());
	}

	public Set<String> readLinks() {
		links = document.select("a[href]").parallelStream().filter(e -> new UrlValidator().isValid(e.attr("href")))
				.map(e -> relativeUrlCheck(e.attr("href"))).collect(Collectors.toSet());
		return links;
	}

	private String relativeUrlCheck(String url) {
		return url.startsWith("/") ? baseUrl + url : url;
	}

	private boolean validateSameDomain(String url) {
		try {
			return new URL(url).getHost().equalsIgnoreCase(new URL(baseUrl.toString()).getHost());
		} catch (MalformedURLException e) {
			System.out.println(url + " - " + e.getMessage());
			return false;
		}
	}

	private void frontTier(String url) {
		if (!result.containsKey(url) && validateSameDomain(url))
			crawl(url);
	}

	private Set<String> crawlFront(String url) throws IOException {
		try {
			if (!result.containsKey(url)) {
				document = Jsoup.connect(url).get();
				Set<String> resources = new HashSet<>();
				resources.addAll(readAssets());
				resources.addAll(readLinks());
				result.put(url, resources);
			}
		} catch (IOException e) {
			System.out.println(url + " - " + e.getMessage());
			result.put(url, new HashSet<>());
		}
		return links;
	}

	Map<String, Set<String>> crawl(String url) {
		baseUrl = url;
		try {
			crawlFront(url).parallelStream().forEach(link -> this.frontTier(link));
		} catch (IOException e) {
			System.out.println(url + " - " + e.getMessage());
			result.put(url, null);
		}
		return result;
	}

}
