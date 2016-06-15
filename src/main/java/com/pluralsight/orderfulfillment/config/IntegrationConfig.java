package com.pluralsight.orderfulfillment.config;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.javaconfig.CamelConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class IntegrationConfig extends CamelConfiguration {
	
	@Inject
	Environment enviornment;
	
	@Override
	public List<RouteBuilder> routes() {
		List<RouteBuilder> routeList = 
				new ArrayList<RouteBuilder>();
		routeList.add(new RouteBuilder(){

			@Override
			public void configure() throws Exception {
				from(
						"file://"
						+ enviornment.getProperty("order.fulfillment.center.1.outbound.folder")
						+ "?noop=true")
				.to("file://"
						+ enviornment.getProperty("order.fulfillment.center.1.outbound.folder")
						+ "/test");
				
			}
			
			
		});
		return routeList;
	}

}
