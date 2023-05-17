package com.stellantis.od1.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Pages {

	SEARCH("Search"), ADMINISTRATION("Administration"), WORKFLOW("Workflow");

	@Getter
	private String value;

}