package com.histron.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @team mackie Studio 
 * @Author QQ:15577969 
 * @Date 2020-06-10 09:35:28 
 */
@Entity
@Table ( name ="sys_role" )
@Getter
@Setter
public class Role  implements Serializable {

	private static final long serialVersionUID =  188137548724718208L;

	/**
	 * 主键ID
	 */
	@Id
   	@Column(name = "id" )
	private String id;

	/**
	 * 角色名称
	 */
   	@Column(name = "name" )
	private String name;

	/**
	 * 说明
	 */
   	@Column(name = "description" )
	private String description;

	/**
	 * 租户id
	 */
   	@Column(name = "tenant_id" )
	private String tenantId;

	@JsonIgnore
	@ManyToMany(mappedBy="roles")  //不维护中间表
	private Set<User> users = new HashSet<User>(0);//角色与用户   多对多


	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="sys_role_permission",
			joinColumns={@JoinColumn(name="role_id",referencedColumnName="id")},
			inverseJoinColumns={@JoinColumn(name="permission_id",referencedColumnName="id")})
	private Set<Permission> permissions = new HashSet<Permission>(0);//角色与模块  多对多
}
