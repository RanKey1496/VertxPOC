package com.jimbo;

import io.vertx.config.ConfigRetriever;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class App extends AbstractVerticle 
{
    @Override
    public void start(Future fut) {
    	ConfigRetriever retriever = ConfigRetriever.create(vertx);
    	retriever.getConfig(config -> {
    		if (config.failed()) {
    			fut.fail(config.cause());
    		} else {
    			vertx
	        		.createHttpServer()
	        		.requestHandler(r -> {
	        			r.response()
	        				.end("<h1>Hello there.... General Kenobi :v</h1>");
	        		})
	        		.listen(config.result().getInteger("HTTP_PORT", 8080), result -> {
	        			if (result.succeeded()) {
	        				fut.complete();
	        			} else {
	        				fut.fail(result.cause());
	        			}
	        		});
    		}
    	});
    }
}
