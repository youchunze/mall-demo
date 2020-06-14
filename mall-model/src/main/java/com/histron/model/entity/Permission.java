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
 * @Date 2020-06-10 09:35:28
 */
@Entity
@Table ( name ="sys_permission" )
@Getter
@Setter
public class Permission  implements Serializable {

	private static final long serialVersionUID =  5333737505502823717L;

	/**
	 * ID
	 */
	@Id
   	@Column(name = "id" )
	private String id;

	/**
	 * 权限描述
	 */
   	@Column(name = "description" )
	private String description;

	/**
	 * 权限名称
	 */
   	@Column(name = "name" )
	private String name;

	/**
	 * 权限类型
	 */
   	@Column(name = "type" )
	private Integer type;

	/**
	 * 权限编码
	 */
   	@Column(name = "code" )
	private String code;

   	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="sys_role_permission",
			joinColumns={@JoinColumn(name="permission_id",referencedColumnName="id")},
			inverseJoinColumns={@JoinColumn(name="role_id",referencedColumnName="id")})
	private Set<Permission> permissions = new HashSet<Permission>(0);//角色与模块  多对多

}
