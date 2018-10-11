package com.jimbo;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class App extends AbstractVerticle 
{
    @Override
    public void start(Future fut) {
    	vertx
    		.createHttpServer()
    		.requestHandler(r -> {
    			r.response()
    				.end("<h1>Hello there.... General Kenobi :v</h1>");
    		})
    		.listen(config().getInteger("HTTP_PORT", 8080), result -> {
    			if (result.succeeded()) {
    				fut.complete();
    			} else {
    				fut.fail(result.cause());
    			}
    		});
    }
}
