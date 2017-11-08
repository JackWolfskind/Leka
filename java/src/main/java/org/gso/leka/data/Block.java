package org.gso.leka.data;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "adm_block")
public class Block {
	
	@Id
	@Column(name="adm_st_id")
	private int ID;
	
	@Column(name="adm_st_name")
	private String name;
	
	@Column(name="adm_st_anfang")
	private Date start;
	
	@Column(name="adm_st_ende")
	private Date end;
}
