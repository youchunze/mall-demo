package com.histron.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @team mackie Studio 
 * @Author QQ:15577969 
 * @Date 2020-06-10 09:35:28 
 */
@Entity
@Table ( name ="sys_user" )
@Getter
@Setter
public class User  implements Serializable {

	private static final long serialVersionUID =  7910322278292554492L;

	/**
	 * ID
	 */
	@Id
   	@Column(name = "id" )
	private String id;

	/**
	 * 手机号码
	 */
   	@Column(name = "mobile" )
	private String mobile;

	/**
	 * 用户名称
	 */
   	@Column(name = "username" )
	private String username;

	/**
	 * 用户密码
	 */
   	@Column(name = "password" )
	private String password;

	/**
	 * 启用状态 0是禁用，1是启用
	 */
   	@Column(name = "enable_state" )
	private Integer enableState;

//	/**
//	 * 角色
//	 */
//   	@Column(name = "role_ids" )
//	private String roleIds;

	/**
	 * 创建时间
	 */
   	@Column(name = "create_time" )
	private Date createTime;

	/**
	 * 租户ID
	 */
   	@Column(name = "tenant_id" )
	private String tenantId;

	/**
	 * 租户名称
	 */
   	@Column(name = "tenant_name" )
	private String tenantName;

    //立即从数据库中进行加载数据;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "sys_user_role",
			//JoinColumn当前对象再中间表的外键,referencedColumnName参照主表的主键名
			joinColumns = { @JoinColumn(name = "user_id" ,referencedColumnName = "id")},
			//对方再中间表的外键
			inverseJoinColumns ={@JoinColumn(name = "role_id", referencedColumnName = "id") })
	private Set<Role> roles = new HashSet<Role>();
}
