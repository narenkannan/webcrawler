package com.naren.webcrawler.core;

import java.net.URL;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class SharedService {
	public URL domain;

	public URL getDomain() {
		return domain;
	}

	public Map<String, Set<String>> result = new ConcurrentHashMap<>();

	public Map<String, Set<String>> get() {
		return result;
	}

}
