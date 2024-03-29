package com.camel.payload.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PayloadResponse {
	
	private Long id;
	private String formCode;
	private String fileName;
	private String content;

}
