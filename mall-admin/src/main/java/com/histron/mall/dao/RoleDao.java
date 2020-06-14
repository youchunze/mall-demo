package com.histron.mall.dao;

import com.histron.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author: hyl
 * @date: 2020/01/15
 **/
@Repository
public interface RoleDao extends JpaRepository<Role,String>, JpaSpecificationExecutor<Role> {
}
