package com.stellantis.od1.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.stellantis.od1.user.domain.PageConfigInfo;
import com.stellantis.od1.user.domain.RoleEntity;
import com.stellantis.od1.user.dto.Role.PageConfigInfoDto;
import com.stellantis.od1.user.dto.Role.RoleMappingDefition;
import com.stellantis.od1.user.dto.Role.RolesResponse;

@Service
public class RoleMapper {

	private RoleMapper() {

	}

	public static RoleEntity convertRequestTodto(RoleMappingDefition roleReq) {
		RoleEntity role = new RoleEntity();
		role.setRole(roleReq.getRole());
		role.setTechnicalTarget(roleReq.getTechnicalTarget());
		List<PageConfigInfo> bundleList = new ArrayList<>();
		roleReq.getBundle().forEach(pageConfigInfoDto -> {
			PageConfigInfo pageConfigInfo = new PageConfigInfo();
			pageConfigInfo.setRole(role);
			if (null != pageConfigInfoDto.getPages()) {
				pageConfigInfo.setPages(pageConfigInfoDto.getPages());
			}
			if (null != pageConfigInfoDto.getPermissions()) {
				pageConfigInfo.setPermissions(pageConfigInfoDto.getPermissions());
			}
			bundleList.add(pageConfigInfo);
			role.setBundle(bundleList);
		});
		return role;
	}

	public static RolesResponse convertDtoToResponse(RoleEntity role) {
		RolesResponse resp = new RolesResponse();
		resp.setId(role.getId());
		resp.setRole(role.getRole());
		resp.setTechnicalTarget(role.getTechnicalTarget());
		resp.setCreatedAt(role.getCreatedAt().toString());
		resp.setUpdatedAt(role.getUpdatedAt().toString());
		List<PageConfigInfoDto> bundleListDto = new ArrayList<>();
		if (null != role.getBundle()) {
			role.getBundle().forEach(pageConfigInfo -> {
				PageConfigInfoDto pageConfigInfoDto = new PageConfigInfoDto();
				pageConfigInfoDto.setId(pageConfigInfo.getId());
				if (null != pageConfigInfo.getPages()) {
					pageConfigInfoDto.setPages(pageConfigInfo.getPages());
				}
				if (null != pageConfigInfo.getPermissions()) {
					List<String> permissonList = new ArrayList<>();
					pageConfigInfo.getPermissions().forEach(permissonListDto -> {
						permissonList.add(permissonListDto);
						pageConfigInfoDto.setPermissions(permissonList);
					});
				}
				bundleListDto.add(pageConfigInfoDto);
			});
			resp.setBundle(bundleListDto);
		}
		return resp;
	}

}
