INSERT INTO userschema.ROLES (createdat, createdby, role, technicalTarget, updatedat, updatedby, id) 
VALUES (CURRENT_TIMESTAMP, 'UserName', 'SUPERADMIN', 'OD1.DEV_DOCG_SAD', CURRENT_TIMESTAMP, 'UserName', 0);

INSERT INTO userschema.pageconfiginfo (id,pages,role_id) 
VALUES (0,'ADMINISTRATION',0);

INSERT INTO userschema.role_permissions (pageconfiginfo_id,permissions)
VALUES (0,'ADD');

INSERT INTO userschema.role_permissions (pageconfiginfo_id,permissions)
VALUES (0,'READ');

INSERT INTO userschema.role_permissions (pageconfiginfo_id,permissions)
VALUES (0,'MODIFY');

INSERT INTO userschema.role_permissions (pageconfiginfo_id,permissions)
VALUES (0,'DELETE');