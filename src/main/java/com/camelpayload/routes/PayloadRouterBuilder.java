package com.camelpayload.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.camelpayload.dto.Payload;
import com.camelpayload.dto.PayloadListResponseDTO;

@Component
public class PayloadRouterBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		restConfiguration()
        .component("servlet")
        .bindingMode(RestBindingMode.auto)
        .host("localhost").port(8081);
		
		rest()
        	.path("/integration/payloads")

        .get()
            .route().routeId("findAll")
            .outputType(String.class)
            .to("rest:get:/payloads?bridgeEndpoint=true")
        .endRest()
		
		.get("/{id}")
		.route().routeId("findById")
		.outputType(String.class)
		.to("rest:get:/payloads/{id}?bridgeEndpoint=true")
		.endRest();
	
	}

}
