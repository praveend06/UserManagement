package com.stellantis.od1.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Permissions {

	READ("read"), ADD("add"), MODIFY("modify"), DELETE("delete");

	@Getter
	private String value;

}
