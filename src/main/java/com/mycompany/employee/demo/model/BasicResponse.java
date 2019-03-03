package com.mycompany.employee.demo.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BasicResponse {
	
	private Object data;
	private String status;
}
