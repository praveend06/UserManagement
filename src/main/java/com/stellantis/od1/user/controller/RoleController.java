package com.stellantis.od1.user.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stellantis.od1.user.domain.RoleEntity;
import com.stellantis.od1.user.dto.Role;
import com.stellantis.od1.user.dto.Role.RoleMappingDefition;
import com.stellantis.od1.user.dto.Role.RolesResponse;
import com.stellantis.od1.user.service.RoleMapper;
import com.stellantis.od1.user.service.RoleServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/role")
public class RoleController {

	@Autowired
	RoleServiceImpl srv;

	@Autowired
	RoleMapper mapper;

	@Autowired
	private ModelMapper modelMapper;

	@Operation(summary = "Creates a DOCG-role property.", description = "Creates a DOCG-role property.", responses = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
			@ApiResponse(responseCode = "404", description = "Not Found", content = @Content) })

	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RolesResponse> registerRole(@Valid @RequestBody RoleMappingDefition roleReq) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(RoleMapper.convertDtoToResponse(srv.createRole(roleReq)));
	}

	@Operation(summary = "List Roles.", description = "List Roles from data base.", responses = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
			@ApiResponse(responseCode = "404", description = "Not Found", content = @Content) })

	@GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RolesResponse>> listRoles() {
		return ResponseEntity.status(HttpStatus.OK).body(srv.listRoles().stream()
				.map(roles -> RoleMapper.convertDtoToResponse(roles)).collect(Collectors.toList()));
	}

	@Operation(summary = "Updates a DOCG-role property.", description = "Updates a DOCG-role property.", responses = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
			@ApiResponse(responseCode = "404", description = "Not Found", content = @Content) })

	@PutMapping(value = "/{role_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RoleMappingDefition> updateRole(@PathVariable(name = "role_id") Long roleId, @RequestBody RoleMappingDefition roleReq) throws Exception {
		RoleEntity roleEntity = modelMapper.map(roleReq, RoleEntity.class);
		RoleMappingDefition resonse = modelMapper.map(srv.update(roleId, roleEntity), RoleMappingDefition.class);
		return ResponseEntity.status(HttpStatus.OK).body(resonse);
	}

	@Operation(summary = "Deletes a DOCG-role property.", description = "Deletes a DOCG-role property.", responses = {
			@ApiResponse(responseCode = "204", description = "No Content", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
			@ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
			@ApiResponse(responseCode = "405", description = "Method not allowed", content = @Content),
			@ApiResponse(responseCode = "503", description = "Database/Service unavailable", content = @Content) })

	@DeleteMapping(value = "/{role_name}")
	public ResponseEntity<Void> deleteRole(@PathVariable(name = "role_name") String roleName) {
		srv.delete(roleName);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@Operation(summary = "List Roles for technical target.", description = "List Roles from data base.", responses = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
			@ApiResponse(responseCode = "404", description = "Not Found", content = @Content) })

	@GetMapping(value = "/{technical_target}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Role.RolesResponse>> listofTechnicalTargetRoles(@PathVariable(name = "technical_target") List<String> technicalTarget) {
		List<RoleEntity> roleEntities = srv.listTechnicalTargetRoles(technicalTarget);
		List<RolesResponse> dtos = roleEntities.stream().map(roles -> RoleMapper.convertDtoToResponse(roles))
				.collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(dtos);
	}

}
