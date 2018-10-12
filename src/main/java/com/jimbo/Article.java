package com.jimbo;

import java.util.concurrent.atomic.AtomicInteger;

import io.vertx.core.json.JsonObject;

public class Article {

	private static final AtomicInteger COUNTER = new AtomicInteger();
	private long id = -1;
	private String title;
	private String url;
	
	public Article(String title, String url) {
		this.id = COUNTER.getAndIncrement();
		this.title = title;
		this.url = url;
	}
	
	public Article(long id, String title, String url) {
		this.id = id;
		this.title = title;
		this.url = url;
	}
	
	public Article() {
		this.id = COUNTER.getAndIncrement();
	}
	
	public Article(JsonObject json) {
		this(json.getInteger("id", -1),
			json.getString("title"),
			json.getString("url"));
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}	
	
	
}
