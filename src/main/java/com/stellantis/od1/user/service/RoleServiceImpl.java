package com.stellantis.od1.user.service;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stellantis.od1.user.domain.PageConfigInfo;
import com.stellantis.od1.user.domain.RoleEntity;
import com.stellantis.od1.user.dto.Role.RoleMappingDefition;
import com.stellantis.od1.user.exception.ResourceNotFoundException;
import com.stellantis.od1.user.repository.RolesRepository;
import com.stellantis.od1.user.util.CommonUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RoleServiceImpl {

	@Autowired
	RolesRepository repository;

	public RoleEntity createRole(RoleMappingDefition roleReq) throws Exception {

		RoleEntity roleEntity = RoleMapper.convertRequestTodto(roleReq);
		if (repository.existsByRole(roleEntity.getRole())) {
			throw new ResourceNotFoundException("Role with name " + roleEntity.getRole() + ", already exists!");
		}
		roleEntity.setCreatedBy(roleReq.getCreatedBy());
		roleEntity.setCreatedAt(LocalDateTime.now());
		roleEntity.setUpdatedBy(roleReq.getUpdatedBy());
		roleEntity.setUpdatedAt(LocalDateTime.now());
		return repository.save(roleEntity);
	}

	public List<RoleEntity> listRoles() {
		return (List<RoleEntity>) repository.findAll();
	}

	public RoleEntity update(Long roleId, RoleEntity roleEntity) throws Exception {
		Optional<RoleEntity> optionalRole = repository.findById(roleId);
		if (optionalRole.isPresent()) {
			RoleEntity roleToUpdate = optionalRole.get();
			roleToUpdate.setId(roleEntity.getId());
			roleToUpdate.setRole(roleEntity.getRole());
			roleToUpdate.setTechnicalTarget(roleEntity.getTechnicalTarget());
			roleToUpdate.setUpdatedBy(roleEntity.getUpdatedBy());
			roleToUpdate.setUpdatedAt(LocalDateTime.now());
			roleToUpdate.setCreatedBy(roleEntity.getCreatedBy());
			List<PageConfigInfo> bundleList = new ArrayList<>();
			for (PageConfigInfo p : roleEntity.getBundle()) {
				PageConfigInfo pageConfigInfo = new PageConfigInfo();
				pageConfigInfo.setId(p.getId());
				pageConfigInfo.setRole(roleToUpdate);
				if (null != p.getPages()) {
					pageConfigInfo.setPages(p.getPages());
				}
				if (null != p.getPermissions()) {
					pageConfigInfo.setPermissions(p.getPermissions());
				}
				bundleList.add(pageConfigInfo);
			}
			roleToUpdate.setBundle(bundleList);
			return repository.save(roleToUpdate);
		} else {
			throw new ResourceNotFoundException("The role Id'" + roleId + "' cannot be found.");
		}
	}

	public void delete(String roleName) {
		RoleEntity optionalRole = repository.findByRole(roleName);
		if (null != optionalRole) {
			repository.deleteById(optionalRole.getId());
		} else {
			throw new ResourceNotFoundException("You can't delete '" + roleName + "', because it not exists.");
		}
	}

	public List<RoleEntity> listTechnicalTargetRoles(List<String> technicalTargetswithBrandCodeRegion) {
		
		log.info(MessageFormat.format("Technical targets in the request : {0}", technicalTargetswithBrandCodeRegion.toString()));
		List<String> technicalTargets = new ArrayList<>();
		technicalTargetswithBrandCodeRegion.forEach(t -> {
			if (t.contains(CommonUtils.SUPERADMIN)) {
				technicalTargets.add(t);
			} else if (t.contains(CommonUtils.HYPHEN_DELIMINATOR)) {
				String technicalTargetWithHyphen = t.substring(0, t.indexOf("-")+1) + CommonUtils.ASTERICK_CHAR;
				technicalTargets.add(technicalTargetWithHyphen);
			} else {
				String technicalTargetWithoutHyphen = t.concat(CommonUtils.ASTERICK_CHAR);
				technicalTargets.add(technicalTargetWithoutHyphen);
			}
		});
		log.info(MessageFormat.format("Technical targets for the DB call : {0}", technicalTargets.toString()));
		Optional<List<RoleEntity>> entity = repository.findByTechnicalTargets(technicalTargets);
		if (entity.isPresent()) {
			return entity.get();
		} else {
			throw new ResourceNotFoundException("Roles not found for ." + technicalTargets);
		}
	}

}
