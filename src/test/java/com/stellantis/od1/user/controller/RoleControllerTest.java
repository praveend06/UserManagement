package com.stellantis.od1.user.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.stellantis.od1.user.UsermanagementApplication;
import com.stellantis.od1.user.repository.RolesRepository;
import com.stellantis.od1.user.testdata.ProvisionTestData;

@SpringBootTest(classes = UsermanagementApplication.class)
@AutoConfigureMockMvc
@Profile("test")
@ActiveProfiles("test")
class RoleControllerTest {

	  @Autowired
	  private MockMvc mockMvc;
	  
	  @MockBean 
	  private RolesRepository repository;

	  @Test
	  void registerUserTest_200() throws Exception {
	  when(repository.save(any())).thenReturn(ProvisionTestData.TEST_USER_REGISTRATION_ENTITY);
	  String content = ProvisionTestData.USER_REGISTRATION_REQUEST;
	  mockMvc.perform(MockMvcRequestBuilders.post(ProvisionTestData. USER_REGISTRATION_URI)
	  .accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.
	  APPLICATION_JSON_VALUE). content(content)).andExpect(status().is(200)); }

	  
	  @Test 
	  void registerUserTest_ExistsByRole() throws Exception {
	  when(repository.existsByRole(any())).thenReturn(true);
	  String content = ProvisionTestData.USER_REGISTRATION_REQUEST;
	  mockMvc.perform(MockMvcRequestBuilders.post(ProvisionTestData. USER_REGISTRATION_URI)
	  .accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.
	  APPLICATION_JSON_VALUE).content(content)).andExpect(status().isNotFound());
	  }
	  
	  @Test 
	  void registerUserTest_500() throws Exception {
	  when(repository.save(any())).thenReturn(ProvisionTestData.USER_REGISTRATION_REQUEST);
	  String content = ProvisionTestData.USER_REGISTRATION_REQUEST;
	  mockMvc.perform(MockMvcRequestBuilders.post(ProvisionTestData. USER_REGISTRATION_URI)
	  .accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.
	  APPLICATION_JSON_VALUE) .content(content)).andExpect(status().is(500));
	  }
	  
	  @Test 
	  void registerUserTest_404() throws Exception {
	  when(repository.save(any())).thenReturn(ProvisionTestData.USER_REGISTRATION_REQUEST);
	  String content = ProvisionTestData.USER_REGISTRATION_REQUEST;
	  mockMvc.perform(MockMvcRequestBuilders.post("/create")
	  .accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.
	  APPLICATION_JSON_VALUE) .content(content)).andExpect(status().is(404));
	  }
	  
	  @Test 
	  void registerUserTest_400() throws Exception {
	  String content = ProvisionTestData.USER_REGISTRATION_FAKE_REQUEST;
	  mockMvc.perform(MockMvcRequestBuilders.post(ProvisionTestData.USER_REGISTRATION_URI)
	  .accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.
	  APPLICATION_JSON_VALUE) .content(content)).andExpect(status().is(400));
	  }
	  
	  @Test 
	  void listUserTest() throws Exception {
	  mockMvc.perform(MockMvcRequestBuilders.get("/role/list")
	  .accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.
	  APPLICATION_JSON_VALUE)).andExpect(status().is(200));
	  }
	  
	  @Test 
	  void listUserTest_404() throws Exception {
	  mockMvc.perform(MockMvcRequestBuilders.get("/list")
	  .accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.
	  APPLICATION_JSON_VALUE)).andExpect(status().is(404));
	  }
	  
	  @Test 
	  void deleteUserTest_204() throws Exception {
      when(repository.findByRole(any())).thenReturn(ProvisionTestData.TEST_USER_REGISTRATION_ENTITY);
	  mockMvc.perform(MockMvcRequestBuilders.delete("/role/{role_name}", "SUPERADMIN"))
	  .andExpect(status().is(204));
	  }
	 
	  @Test 
	  void deleteUserTest_ResourceNotFound() throws Exception {
	  when(repository.findByRole(any())).thenReturn(any());
	  mockMvc.perform(MockMvcRequestBuilders.delete("/role/{role_name}", "SUPERADMIN"))
	  .andExpect(status().is(404));
	  }
	  
	  @Test 
	  void updateUserTest_404() throws Exception {
      String content = ProvisionTestData.USER_REGISTRATION_REQUEST_UPDATED;
	  mockMvc.perform(MockMvcRequestBuilders.put("/role/{role_id}", 1)
	  .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON).content(content))
	  .andExpect(status().is(404));
	  }
	  
	  @Test 
	  void updateUserTest_500() throws Exception {
      when(repository.findById(any())).thenReturn(ProvisionTestData.TEST_USER_REGISTRATION_ENTITY_UPDATED);
      String content = ProvisionTestData.USER_REGISTRATION_REQUEST_UPDATED;
	  mockMvc.perform(MockMvcRequestBuilders.put("/role/{role_id}", 1)
	  .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON).content(content))
	  .andExpect(status().is(500));
	  }

	@Test
	void listByTechnicalTargetTest() throws Exception {
	when(repository.findByTechnicalTargets(anyList())).thenReturn(Optional.of(ProvisionTestData.mockRoleEntityList()));
	mockMvc.perform(MockMvcRequestBuilders.get("/role/{technical_target}","T1")
	.accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.
	APPLICATION_JSON_VALUE)).andExpect(status().is(200));
	}
}
