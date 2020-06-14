package com.histron.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
/**
 * @team mackie Studio
 * @Author QQ:15577969
 * @Date 2020-06-10 09:35:28
 */
@Entity
@Table ( name ="sys_user_role" )
@Getter
@Setter
public class UserRole  implements Serializable {

	private static final long serialVersionUID =  459418356556450596L;

	/**
	 * id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	/**
	 * 角色id
	 */
   	@Column(name = "role_id" )
	private String roleId;

	/**
	 * 用户id
	 */
   	@Column(name = "user_id" )
	private String userId;

}
