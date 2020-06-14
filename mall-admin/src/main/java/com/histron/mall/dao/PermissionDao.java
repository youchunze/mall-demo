package com.histron.mall.dao;



import com.histron.model.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
  * 权限数据访问接口
  */
@Repository
public interface PermissionDao extends JpaRepository<Permission, String>, JpaSpecificationExecutor<Permission> {


}