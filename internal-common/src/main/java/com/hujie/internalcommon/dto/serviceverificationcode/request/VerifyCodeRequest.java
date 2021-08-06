package com.hujie.internalcommon.dto.serviceverificationcode.request;

import lombok.Data;

@Data
public class VerifyCodeRequest {

	private int identity;

	private String phoneNumber;

	private String code;

	@Override
	public String toString() {
		return "VerifyCodeRequest{" +
				"identity=" + identity +
				", phoneNumber='" + phoneNumber + '\'' +
				", code='" + code + '\'' +
				'}';
	}
}
