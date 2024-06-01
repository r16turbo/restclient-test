package com.example.demo;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.*;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.http.client.JettyClientHttpRequestFactory;
import org.springframework.http.client.ReactorNettyClientRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;

@WireMockTest
@SpringBootTest
class DemoApplicationTests {

	@Test
	void httpComponentsTest(WireMockRuntimeInfo wmri) {
		stubFor(get("/foo/bar").willReturn(aResponse().withStatus(200)));

		final RestClient restClient = RestClient.builder()
				.requestFactory(new HttpComponentsClientHttpRequestFactory())
				.build();

		assertNull(
				restClient.get().uri(URI.create(wmri.getHttpBaseUrl()).resolve("/foo/bar"))
						.retrieve()
						.body(String.class));
	}

	@Test
	void jettyTest(WireMockRuntimeInfo wmri) {
		stubFor(get("/foo/bar").willReturn(aResponse().withStatus(200)));

		final RestClient restClient = RestClient.builder()
				.requestFactory(new JettyClientHttpRequestFactory())
				.build();

		assertNull(
				restClient.get().uri(URI.create(wmri.getHttpBaseUrl()).resolve("/foo/bar"))
						.retrieve()
						.body(String.class));
	}

	@Test
	void jdkTest(WireMockRuntimeInfo wmri) {
		stubFor(get("/foo/bar").willReturn(aResponse().withStatus(200)));

		RestClient restClient = RestClient.builder()
				.requestFactory(new JdkClientHttpRequestFactory())
				.build();

		assertNull(
				restClient.get().uri(URI.create(wmri.getHttpBaseUrl()).resolve("/foo/bar"))
						.retrieve()
						.body(String.class));
	}

	@Test
	void simpleTest(WireMockRuntimeInfo wmri) {
		stubFor(get("/foo/bar").willReturn(aResponse().withStatus(200)));

		final RestClient restClient = RestClient.builder()
				.requestFactory(new SimpleClientHttpRequestFactory())
				.build();

		assertNull(
				restClient.get().uri(URI.create(wmri.getHttpBaseUrl()).resolve("/foo/bar"))
						.retrieve()
						.body(String.class));
	}

	@Test
	void reactorNettyTest(WireMockRuntimeInfo wmri) {
		stubFor(get("/foo/bar").willReturn(aResponse().withStatus(200)));

		final RestClient restClient = RestClient.builder()
				.requestFactory(new ReactorNettyClientRequestFactory())
				.build();

		assertNull(
				restClient.get().uri(URI.create(wmri.getHttpBaseUrl()).resolve("/foo/bar"))
						.retrieve()
						.body(String.class));
	}

}
