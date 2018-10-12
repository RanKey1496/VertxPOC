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
<<<<<<< HEAD
			ServerSocket socket = new ServerSocket(0);
			port = socket.getLocalPort();
			socket.close();
			vertx = Vertx.vertx();
			DeploymentOptions options = new DeploymentOptions().setConfig(new JsonObject().put("HTTP_PORT", port));
			vertx.deployVerticle(App.class.getName(), options, context.asyncAssertSuccess());
		} catch (Exception e) {
			System.out.println("Se jodió :v" + e.getMessage());
=======
			vertx = Vertx.vertx();
			ServerSocket socket = new ServerSocket(0);
			port = socket.getLocalPort();
			socket.close();
			DeploymentOptions options = new DeploymentOptions().setConfig(new JsonObject().put("HTTP_PORT", port));
			vertx.deployVerticle(App.class.getName(), options, context.asyncAssertSuccess());
		} catch (Exception e) {
			System.out.println("Error testing: " + e.getMessage());
>>>>>>> 81f486647d164a5651a860d691a4500cefe6585e
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
