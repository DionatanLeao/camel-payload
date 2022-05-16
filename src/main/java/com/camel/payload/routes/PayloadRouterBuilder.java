package com.camel.payload.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.camel.payload.dto.PayloadRequest;
import com.camel.payload.service.TokenService;

@Component
public class PayloadRouterBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		restConfiguration()
	        .component("servlet")
	        .bindingMode(RestBindingMode.json)
	        .host("localhost").port(8081);
		
		rest()
        	.path("/integration/payloads")

        .get()
	        .bindingMode(RestBindingMode.json)
			.consumes(MediaType.APPLICATION_JSON_VALUE)
			.produces(MediaType.APPLICATION_JSON_VALUE)
            .route().routeId("findAll")
            .outputType(String.class)
        .to("rest:get:/payloads?bridgeEndpoint=true")
        
        .endRest()
		
		.get("/{id}")
			.bindingMode(RestBindingMode.json)
			.consumes(MediaType.APPLICATION_JSON_VALUE)
			.produces(MediaType.APPLICATION_JSON_VALUE)
			.route().routeId("findById")
			.outputType(String.class)
		.to("rest:get:/payloads/{id}?bridgeEndpoint=true")
		
		.endRest()		
		
		.post()
			.bindingMode(RestBindingMode.json)
			.consumes(MediaType.APPLICATION_JSON_VALUE)
			.produces(MediaType.APPLICATION_JSON_VALUE)
			.type(PayloadRequest.class)
			.route().routeId("save")
			.outputType(String.class)
			.process(new Processor() {

				@Override
				public void process(Exchange exchange) throws Exception {
					TokenService service = new TokenService();
					exchange.getMessage().setHeader("token", service.gerarToken());					
				}
            	
            })
		.to("rest:post:/payloads?bridgeEndpoint=true")
		
		.endRest()
		
		.put("/{id}")
			.bindingMode(RestBindingMode.json)
			.consumes(MediaType.APPLICATION_JSON_VALUE)
			.produces(MediaType.APPLICATION_JSON_VALUE)	
			.type(PayloadRequest.class)
			.route().routeId("update")
			.outputType(String.class)	
		.to("rest:put:/payloads/{id}?bridgeEndpoint=true")
		
		.endRest()

		.delete("/{id}")
			.bindingMode(RestBindingMode.json)
			.consumes(MediaType.APPLICATION_JSON_VALUE)
			.produces(MediaType.APPLICATION_JSON_VALUE)	
			.route().routeId("delete")
		.to("rest:delete:/payloads/{id}?bridgeEndpoint=true")
		
		.endRest();		
	
	}

}
