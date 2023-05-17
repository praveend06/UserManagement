package com.stellantis.od1.user.testdata;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.stellantis.od1.user.domain.PageConfigInfo;
import com.stellantis.od1.user.domain.Pages;
import com.stellantis.od1.user.domain.Permissions;
import com.stellantis.od1.user.domain.RoleEntity;

public class ProvisionTestData {

	public static final String USER_REGISTRATION_URI = "/role/create";
	public static final Optional<RoleEntity> TEST_USER_REGISTRATION_ENTITY_UPDATED = Optional.of(RoleEntity.builder()
			.role("Adminusers")
			.createdBy("User")
			.technicalTarget("OD1.DEV_DOCG_SAD")
			.updatedBy("User")
			.bundle(new ArrayList<PageConfigInfo>())
			.id(1L)
			.createdAt(LocalDateTime.now())
			.createdBy("User")
			.updatedAt(LocalDateTime.now())
			.build());
	
	public static final RoleEntity TEST_USER_REGISTRATION_ENTITY = RoleEntity.builder()
			.role("Adminusers")
			.createdAt(LocalDateTime.now())
			.createdBy("User")
			.technicalTarget("OD1.DEV_DOCG_SAD")
			.updatedAt(LocalDateTime.now())
			.updatedBy("User")
			.bundle(new ArrayList<PageConfigInfo>())
			.id(1L)
			.build();

	 public static RoleEntity getRoleEntity() {
		RoleEntity roleEntity = new RoleEntity();
		roleEntity.setRole("Admins");
		roleEntity.setUpdatedBy("User");
		roleEntity.setId(1L);
		roleEntity.setCreatedBy("User");
		roleEntity.setCreatedAt(LocalDateTime.now());
		roleEntity.setCreatedBy("test");
		roleEntity.setUpdatedBy("Test");
		roleEntity.setUpdatedAt(LocalDateTime.now());
		roleEntity.setTechnicalTarget("T1");
		List<PageConfigInfo> list = new ArrayList<>();
		PageConfigInfo obj = new PageConfigInfo();
		List<String> per = new ArrayList<>();
		per.add(Permissions.READ.getValue());
		per.add(Permissions.ADD.getValue());
		per.add(Permissions.MODIFY.getValue());
		per.add(Permissions.DELETE.getValue());
		obj.setPages(Pages.ADMINISTRATION.getValue());
		obj.setPages(Pages.WORKFLOW.getValue());
		obj.setPages(Pages.SEARCH.getValue());
		obj.setPermissions(per);
		list.add(obj);
		roleEntity.setBundle(list);
		return roleEntity;
	}
	 
	 public static RoleEntity getRoleEntityForSuperAdmin() {
		RoleEntity roleEntity = new RoleEntity();
		roleEntity.setRole("Admins");
		roleEntity.setUpdatedBy("User");
		roleEntity.setId(1L);
		roleEntity.setCreatedBy("User");
		roleEntity.setCreatedAt(LocalDateTime.now());
		roleEntity.setCreatedBy("test");
		roleEntity.setUpdatedBy("Test");
		roleEntity.setUpdatedAt(LocalDateTime.now());
		roleEntity.setTechnicalTarget("OD1.DEV_DOCG_SAD");
		List<PageConfigInfo> list = new ArrayList<>();
		PageConfigInfo obj = new PageConfigInfo();
		List<String> per = new ArrayList<>();
		per.add(Permissions.READ.getValue());
		per.add(Permissions.ADD.getValue());
		per.add(Permissions.MODIFY.getValue());
		per.add(Permissions.DELETE.getValue());
		obj.setPages(Pages.ADMINISTRATION.getValue());
		obj.setPages(Pages.WORKFLOW.getValue());
		obj.setPages(Pages.SEARCH.getValue());
		obj.setPermissions(per);
		list.add(obj);
		roleEntity.setBundle(list);
		return roleEntity;
	}
	 
	 public static RoleEntity getRoleEntityTechTargetWithRegionBrand() {
		RoleEntity roleEntity = new RoleEntity();
		roleEntity.setRole("Admins");
		roleEntity.setUpdatedBy("User");
		roleEntity.setId(1L);
		roleEntity.setCreatedBy("User");
		roleEntity.setCreatedAt(LocalDateTime.now());
		roleEntity.setCreatedBy("test");
		roleEntity.setUpdatedBy("Test");
		roleEntity.setUpdatedAt(LocalDateTime.now());
		roleEntity.setTechnicalTarget("OD1.DEV_DOCG_RTM-EMEA_ALL");
		List<PageConfigInfo> list = new ArrayList<>();
		PageConfigInfo obj = new PageConfigInfo();
		List<String> per = new ArrayList<>();
		per.add(Permissions.READ.getValue());
		per.add(Permissions.ADD.getValue());
		per.add(Permissions.MODIFY.getValue());
		per.add(Permissions.DELETE.getValue());
		obj.setPages(Pages.ADMINISTRATION.getValue());
		obj.setPages(Pages.WORKFLOW.getValue());
		obj.setPages(Pages.SEARCH.getValue());
		obj.setPermissions(per);
		list.add(obj);
		roleEntity.setBundle(list);
		return roleEntity;
	}
	 
	public static List<RoleEntity> mockRoleEntityListForSuperAdmin() {
		List<RoleEntity> roles = new ArrayList<>();
		RoleEntity roleEntity = getRoleEntityForSuperAdmin();
		roles.add(roleEntity);
		return roles;
	}
	
	public static List<RoleEntity> mockRoleEntityList_techTargetWithRegionBrand() {
		List<RoleEntity> roles = new ArrayList<>();
		RoleEntity roleEntity = getRoleEntityTechTargetWithRegionBrand();
		roles.add(roleEntity);
		return roles;
	}

	public static List<RoleEntity> mockRoleEntityList() {
		List<RoleEntity> roles = new ArrayList<>();
		RoleEntity roleEntity = getRoleEntity();
		roles.add(roleEntity);
		return roles;
	}
			
	public static final String USER_REGISTRATION_REQUEST = "{\"bundle\":[{\"pages\":\"ADMINISTRATION\",\"permissions\":[\"ADD\",\"MODIFY\",\"DELETE\",\"READ\"]},{\"pages\":\"WORKFLOW\",\"permissions\":[\"ADD\",\"MODIFY\",\"DELETE\",\"READ\"]},{\"pages\":\"SEARCH\",\"permissions\":[\"ADD\",\"MODIFY\",\"DELETE\",\"READ\"]}],\"role\":\"SUPERADMIN\",\"technicalTarget\":\"OD1.DEV_DOCG_SAD\"}";

	public static final String USER_REGISTRATION_REQUEST_UPDATED = "{\"bundle\":[{\"pages\":\"ADMINISTRATION\",\"permissions\":[\"ADD\",\"MODIFY\",\"DELETE\",\"READ\"]},{\"pages\":\"WORKFLOW\",\"permissions\":[\"ADD\",\"MODIFY\",\"DELETE\",\"READ\"]},{\"pages\":\"SEARCH\",\"permissions\":[\"ADD\",\"MODIFY\",\"DELETE\",\"READ\"]}],\"role\":\"SUPERADMIN\",\"technicalTarget\":\"OD1.DEV_DOCG_SAD\"}";
	
	public static final String USER_REGISTRATION_FAKE_REQUEST =  "{\"bundle\":[{\"pages\":\"ADMINISTRATION\",\"technicalTarget\":\"OD1.DEV_DOCG_SAD\"}";
}
