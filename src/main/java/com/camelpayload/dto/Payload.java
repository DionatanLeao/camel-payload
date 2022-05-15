package com.camelpayload.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payload {
	
	private Long id;
	private String formCode;
	private String fileName;
	private String content;
}
