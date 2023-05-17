package com.stellantis.od1.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

import com.stellantis.od1.user.domain.RoleEntity;
import com.stellantis.od1.user.repository.RolesRepository;
import com.stellantis.od1.user.testdata.ProvisionTestData;

@ExtendWith(MockitoExtension.class)
@Profile("test")
@ActiveProfiles("test")
public class RoleServiceTest {

    @Mock
    private RolesRepository repository;

    @InjectMocks
    private RoleServiceImpl roleService = new RoleServiceImpl();

   @Test
    void updateRoleTest() throws Exception {
        RoleEntity roleEntity = ProvisionTestData.getRoleEntity();
        roleEntity.setRole("SuperAdmin");
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(roleEntity));
        roleService.update(1L,roleEntity);
        assertEquals("SuperAdmin", roleEntity.getRole());
    }

    @Test
    void findAll() {
        Mockito.when(repository.findAll()).thenReturn(ProvisionTestData.mockRoleEntityList());
        List<RoleEntity> vehicleList = roleService.listRoles();
        Assertions.assertTrue(vehicleList.size() > 0);
    }

    @Test
    void findByTechTargetTest() {
        Mockito.when(repository.findByTechnicalTargets(anyList())).thenReturn(Optional.of(ProvisionTestData.mockRoleEntityList()));
        List<String> target = new ArrayList<>();
        target.add("T1");
        List<RoleEntity> vehicleList = roleService.listTechnicalTargetRoles(Collections.singletonList(target.get(0)));
        Assertions.assertTrue(vehicleList.size() > 0);
    }

    @Test
    void deleteTest() {
        RoleEntity roleEntity = ProvisionTestData.getRoleEntity();
        Mockito.when(repository.findByRole(anyString())).thenReturn(roleEntity);
        roleService.delete("Admins");
    }
    
    @Test
    void findByTechTargetTest_superAdmin() {
        Mockito.when(repository.findByTechnicalTargets(anyList())).thenReturn(Optional.of(ProvisionTestData.mockRoleEntityListForSuperAdmin()));
        List<String> target = new ArrayList<>();
        target.add("OD1.DEV_DOCG_SAD");
        List<RoleEntity> vehicleList = roleService.listTechnicalTargetRoles(Collections.singletonList(target.get(0)));
        Assertions.assertTrue(vehicleList.size() > 0);
    }
    
    @Test
    void findByTechTargetTest_techTargetWithRegionBrand() {
        Mockito.when(repository.findByTechnicalTargets(anyList())).thenReturn(Optional.of(ProvisionTestData.mockRoleEntityList_techTargetWithRegionBrand()));
        List<String> target = new ArrayList<>();
        target.add("OD1.DEV_DOCG_RTM-EMEA-ALL");
        List<RoleEntity> vehicleList = roleService.listTechnicalTargetRoles(Collections.singletonList(target.get(0)));
        Assertions.assertTrue(vehicleList.size() > 0);
    }
}
