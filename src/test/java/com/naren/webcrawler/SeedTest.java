package com.naren.webcrawler;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SeedTest {

	Seed seed;
	Field baseUrl;
	String url = "https://webpagecrawler.herokuapp.com";
	Method relativeUrlCheck;
	Method validateSameDomain;

	@Before
	public void before() throws NoSuchFieldException, SecurityException, IllegalArgumentException,
			IllegalAccessException, NoSuchMethodException {
		seed = new Seed();
		baseUrl = Seed.class.getDeclaredField("baseUrl");
		relativeUrlCheck = Seed.class.getDeclaredMethod("relativeUrlCheck", String.class);
		relativeUrlCheck.setAccessible(true);
		validateSameDomain = Seed.class.getDeclaredMethod("validateSameDomain", String.class);
		validateSameDomain.setAccessible(true);

		baseUrl.setAccessible(true);
		baseUrl.set(seed, url);
	}

	@Test
	public void test_valid_relativeUrlCheck()
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String tempUrl = "/http/abc.com";
		String result = (String) relativeUrlCheck.invoke(seed, tempUrl);
		Assert.assertEquals(url + tempUrl, result);
	}

	@Test
	public void test_fullurl_relativeUrlCheck()
			throws IllegalArgumentException, InvocationTargetException, NoSuchFieldException, IllegalAccessException {
		String tempUrl = url + "/http/abc.com";
		String result = (String) relativeUrlCheck.invoke(seed, tempUrl);
		Assert.assertEquals(tempUrl, result);
	}

	@Test
	public void return_true_validateSameDomain()
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String tempUrl = url + "/http/abc.com";
		boolean result = (boolean) validateSameDomain.invoke(seed, tempUrl);
		Assert.assertTrue(result);
	}

	@Test
	public void return_false_validateSameDomain()
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String tempUrl = "http://abc.com/http/abc.com";
		boolean result = (boolean) validateSameDomain.invoke(seed, tempUrl);
		Assert.assertFalse(result);
	}

}
