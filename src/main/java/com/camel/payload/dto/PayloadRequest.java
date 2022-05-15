package com.camel.payload.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PayloadRequest {
	
	private String formCode;
	private String fileName;
	private String content;
	
}
