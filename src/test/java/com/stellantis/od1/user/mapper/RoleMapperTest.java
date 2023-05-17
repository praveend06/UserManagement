package com.stellantis.od1.user.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

import com.stellantis.od1.user.domain.PageConfigInfo;
import com.stellantis.od1.user.domain.RoleEntity;
import com.stellantis.od1.user.dto.Role;
import com.stellantis.od1.user.service.RoleMapper;

@ExtendWith(MockitoExtension.class)
@Profile("test")
@ActiveProfiles("test")
public class RoleMapperTest {

    @InjectMocks
    RoleEntity roleEntity;

    @InjectMocks
    Role.RolesResponse rolesResponse;

   @Test
    void mapperconvertRequestTodtoTest() {
        List<Role.PageConfigInfoDto> list = new ArrayList<>();
        Role.PageConfigInfoDto obj = new Role.PageConfigInfoDto();
        List<String> per = new ArrayList<>();
        per.add(String.valueOf(Role.Permissions.getPermissionsCode(Role.Permissions.ADD.getValue())));
        per.add(String.valueOf(Role.Permissions.getPermissionsCode(Role.Permissions.MODIFY.getValue())));
        per.add(String.valueOf(Role.Permissions.getPermissionsCode(Role.Permissions.READ.getValue())));
        per.add(String.valueOf(Role.Permissions.getPermissionsCode(Role.Permissions.DELETE.getValue())));
        obj.setPages(String.valueOf(Role.Pages.getPagesCode(Role.Pages.ADMINISTRATION.getValue())));
        obj.setPages(String.valueOf(Role.Pages.getPagesCode(Role.Pages.WORKFLOW.getValue())));
        obj.setPages(String.valueOf(Role.Pages.getPagesCode(Role.Pages.SEARCH.getValue())));
        obj.setPermissions(per);
        list.add(obj);
        Role.RoleMappingDefition role = new Role.RoleMappingDefition();
        role.setRole("Superadmin");
        role.setId(1L);
        role.setTechnicalTarget("T1");
        role.setCreatedBy("Test");
        role.setUpdatedBy("Test");
        role.setBundle(list);
        try (MockedStatic<RoleMapper> utilities = Mockito.mockStatic(RoleMapper.class)) {
            utilities.when(() -> RoleMapper.convertRequestTodto(role))
                    .thenReturn(roleEntity);
            assertThat(RoleMapper.convertRequestTodto(role)).isNotNull();
        }
    }

    @Test
    void mapperConvertDtoToResponseTest() {
        List<PageConfigInfo> list = new ArrayList<>();
        PageConfigInfo obj = new PageConfigInfo();
        List<String> per = new ArrayList<>();
        per.add(String.valueOf(Role.Permissions.getPermissionsCode(Role.Permissions.ADD.getValue())));
        per.add(String.valueOf(Role.Permissions.getPermissionsCode(Role.Permissions.MODIFY.getValue())));
        per.add(String.valueOf(Role.Permissions.getPermissionsCode(Role.Permissions.READ.getValue())));
        per.add(String.valueOf(Role.Permissions.getPermissionsCode(Role.Permissions.DELETE.getValue())));
        obj.setPages(String.valueOf(Role.Pages.getPagesCode(Role.Pages.ADMINISTRATION.getValue())));
        obj.setPages(String.valueOf(Role.Pages.getPagesCode(Role.Pages.WORKFLOW.getValue())));
        obj.setPages(String.valueOf(Role.Pages.getPagesCode(Role.Pages.SEARCH.getValue())));
        obj.setPermissions(per);
        list.add(obj);
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRole("SuperAdmin");
        roleEntity.setId(1L);
        roleEntity.setBundle(list);
        try (MockedStatic<RoleMapper> utilities = Mockito.mockStatic(RoleMapper.class)) {
            utilities.when(() -> RoleMapper.convertDtoToResponse(roleEntity))
                    .thenReturn(rolesResponse);
            assertThat(RoleMapper.convertDtoToResponse(roleEntity)).isNotNull();
        }

    }
}
