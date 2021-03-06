package com.jimbo;

import java.net.ServerSocket;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;

@RunWith(VertxUnitRunner.class)
public class AppTest {

	private Vertx vertx;
	private int port = 8081;
	
	@Before
	public void setUp(TestContext context) {
		try {
			vertx = Vertx.vertx();
			ServerSocket socket = new ServerSocket(0);
			port = socket.getLocalPort();
			socket.close();
			DeploymentOptions options = new DeploymentOptions()
					.setConfig(new JsonObject()
							.put("HTTP_PORT", port)
							.put("url", "jdbc:hsqldb:mem:test?shutdown=true")
							.put("driver_class", "org.hsqldb.jdbcDriver"));
			vertx.deployVerticle(App.class.getName(), options, context.asyncAssertSuccess());
		} catch (Exception e) {
			System.out.println("Error testing: " + e.getMessage());
		}
	}
	
	@After
	public void tearDown(TestContext context) {
		vertx.close(context.asyncAssertSuccess());
	}
	
	@Test
	public void testMyApp(TestContext context) {
		final Async async = context.async();
		
		vertx.createHttpClient().getNow(port, "localhost", "/", response -> {
			response.handler(body -> {
				context.assertTrue(body.toString().contains("Hello there"));
				async.complete();
			});
		});
	}
	
}
