package com.histron.mall.service;

import com.histron.common.utils.IdWorker;
import com.histron.model.entity.Permission;
import com.histron.mall.dao.PermissionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: hyl
 * @date: 2020/01/09
 **/
@Service
@Transactional
public class PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 保存权限
     */
    public void save(Map<String,Object> map) throws Exception {
        //设置主键
        String id = idWorker.nextId() + "";
        //通过map构造权限对象
        Permission perm = new Permission();
        perm.setCode("123");
        perm.setDescription("sasdasd");

        //保存权限
        permissionDao.save(perm);
    }

    /**
     * 更新
     */
    public void update(Map<String,Object> map) throws Exception {

    }


    /**
     * 根据id查询权限
     */
    public Map findById(String id)  {

        return new HashMap();
    }


    /**
     * 查询全部用户列表
     * type     :   查询全部权限列表
     *          0 : 菜单 + 按钮(权限点)    1 ： 菜单  2 : 按钮(权限点) 3 ： API接口
     * enVisible :
     *          0 ： 查询SaaS平台的最高权限   1 ： 查询企业的权限
     */
    public List<Permission> findAll(Map<String,Object> map){

        //查询条件
        Specification<Permission> spec = new Specification<Permission>() {

            /**
             * 动态拼接查询条件
             * @param root
             * @param query
             * @param cb
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Permission> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();

                //根据父id查询
                if (!StringUtils.isEmpty(map.get("pid"))){
                     list.add(cb.equal(root.get("pid").as(String.class) , (String)map.get("pid")));
                }

                //根据enVisible查询
                if (!StringUtils.isEmpty(map.get("enVisible"))){
                    list.add(cb.equal(root.get("enVisible").as(String.class) , map.get("enVisible")));
                }

                //根据类型type进行查询
                if (!StringUtils.isEmpty(map.get("type"))){
                    String type = (String) map.get("type");
                    CriteriaBuilder.In<Object> in = cb.in(root.get("type"));

                    if ("0".equals(type)){
                        in.value(1).value(2);
                    }else {
                        in.value(Integer.parseInt(type));
                    }
                    list.add(in);
                }
                return cb.and(list.toArray(new Predicate[list.size()]));
            }
        };

        return permissionDao.findAll(spec);
    }

    /**
     * 根据id删除权限
     */
    public void deleteById(String id)  {
        //通过传递的权限id查询权限
        Permission permission = permissionDao.findById(id).get();
        permissionDao.delete(permission);
        //根据类型构造不同的资源
        int type = permission.getType();

    }

    /**
     * 查询所有企业可以看到的menu
     */
    public List<Permission> getMenus() {
        return null;
    }
}

