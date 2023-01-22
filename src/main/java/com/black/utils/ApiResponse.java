package com.black.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApiResponse {
	private String msg;

	
	public ApiResponse(String msg){
		this.msg=msg;
	}

}
