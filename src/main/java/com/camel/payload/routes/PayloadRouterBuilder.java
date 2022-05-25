package com.camel.payload.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.camel.payload.dto.PayloadRequest;
import com.camel.payload.service.TokenService;
import com.camel.payload.service.impl.TokenServiceImpl;

@Component
public class PayloadRouterBuilder extends RouteBuilder {

	private static final String APPLICATION_JSON_VALUE = MediaType.APPLICATION_JSON_VALUE;
	private static final int PORT = 8081;
	private static final String HOST = "localhost";
	private static final RestBindingMode BINDING_MODE = RestBindingMode.json;
	private static final String COMPONENT = "servlet";

	@Override
	public void configure() throws Exception {
		
		restConfiguration()
	        .component(COMPONENT)
	        .bindingMode(BINDING_MODE)
	        .host(HOST).port(PORT);
		
		rest()
        	.path("/integration/payloads")

        .get()
	        .bindingMode(BINDING_MODE)
			.consumes(APPLICATION_JSON_VALUE)
			.produces(APPLICATION_JSON_VALUE)
            .route().routeId("findAll")
            .outputType(String.class)
        .to("rest:get:/payloads?bridgeEndpoint=true")
        
        .endRest()
		
		.get("/{id}")
			.bindingMode(BINDING_MODE)
			.consumes(APPLICATION_JSON_VALUE)
			.produces(APPLICATION_JSON_VALUE)
			//.outType(PayloadResponse.class)
			.route().routeId("findById")
			.outputType(String.class)
		.to("rest:get:/payloads/{id}?bridgeEndpoint=true")
		
		.endRest()		
		
		.post()
			.bindingMode(BINDING_MODE)
			.consumes(APPLICATION_JSON_VALUE)
			.produces(APPLICATION_JSON_VALUE)
			.type(PayloadRequest.class)
			//.outType(PayloadResponse.class)
			.route().routeId("save")
			.outputType(String.class)
			.process(new Processor() {

				@Override
				public void process(Exchange exchange) throws Exception {
					TokenService service = new TokenServiceImpl();
					exchange.getMessage().setHeader("token", service.gerarToken());					
				}
            	
            })
		.to("rest:post:/payloads?bridgeEndpoint=true")
		
		.endRest()
		
		.put("/{id}")
			.bindingMode(BINDING_MODE)
			.consumes(APPLICATION_JSON_VALUE)
			.produces(APPLICATION_JSON_VALUE)	
			.type(PayloadRequest.class)
			//.outType(PayloadResponse.class)
			.route().routeId("update")
			.outputType(String.class)
			.process(new Processor() {

				@Override
				public void process(Exchange exchange) throws Exception {
					TokenService service = new TokenServiceImpl();
					exchange.getMessage().setHeader("token", service.gerarToken());					
				}
            	
            })
		.to("rest:put:/payloads/{id}?bridgeEndpoint=true")
		
		.endRest()

		.delete("/{id}")
			.bindingMode(BINDING_MODE)
			.consumes(APPLICATION_JSON_VALUE)
			.produces(APPLICATION_JSON_VALUE)	
			.route().routeId("delete")
			.process(new Processor() {

				@Override
				public void process(Exchange exchange) throws Exception {
					TokenService service = new TokenServiceImpl();
					exchange.getMessage().setHeader("token", service.gerarToken());					
				}
            	
            })
		.to("rest:delete:/payloads/{id}?bridgeEndpoint=true")
		
		.endRest();		
	
	}

}
