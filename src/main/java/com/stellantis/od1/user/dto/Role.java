package com.stellantis.od1.user.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.stellantis.od1.user.exception.ResourceNotFoundException;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Role {

	@Schema(name = "RoleMappingDefinition", description = "The Role mapping definition.")
	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class RoleMappingDefition {

		private Long id;
		@NotEmpty(message = "role must not be empty")
		@Pattern(regexp = "^[A-Za-z0-9]{5,15}$", message = "role must be a min 5 max 15 character")
		private String role;
		private String technicalTarget;
		private String createdBy;
		private String updatedBy;
		private List<PageConfigInfoDto> bundle;

	}

	@Schema(name = "PageConfig Info", description = "PageConfig Info")
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	public static class PageConfigInfoDto {

		private Long id;
		private String pages;
		private List<String> permissions;

	}

	@Schema(name = "RolesList", description = "List all roles from Database")
	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class RolesResponse {

		private Long id;
		private String role;
		private String technicalTarget;
		private String createdAt;
		private String updatedAt;
		private List<PageConfigInfoDto> bundle;

	}

	@AllArgsConstructor
	@NoArgsConstructor
	public enum Permissions {

		READ("read"), ADD("add"), MODIFY("modify"), DELETE("delete");

		@Getter
		private String value;

		@JsonCreator
		public static Permissions getPermissionsCode(String values) throws ResourceNotFoundException {
			for (Permissions permissions : Permissions.values()) {
				if (permissions.getValue().equalsIgnoreCase(values)) {
					return permissions;
				}
			}
			return null;
		}
	}

	@AllArgsConstructor
	public enum Pages {

		SEARCH("Search"), ADMINISTRATION("Administration"), WORKFLOW("Workflow");

		@Getter
		private String value;

		@JsonCreator
		public static Pages getPagesCode(String values) {
			for (Pages pages : Pages.values()) {
				if (pages.getValue().equalsIgnoreCase(values.toUpperCase())) {
					return pages;
				}
			}
			return null;
		}
	}
}
