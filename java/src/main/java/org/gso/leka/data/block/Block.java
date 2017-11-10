package org.gso.leka.data.block;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.gso.leka.data.lesson.Lesson;

@Entity
@Table(name = "adm_block")
public class Block {
	
	@Id
	@Column(name="adm_st_id")
	private int ID;
	
	@Column(name="adm_st_name")
	private String name;
	
	@Temporal(TemporalType.TIME)
	@Column(name="adm_st_anfang")
	private Date start;
	
	@Temporal(TemporalType.TIME)
	@Column(name="adm_st_ende")
	private Date end;
	
	public static Block load(EntityManager manager, int ID) {
		return manager.find(Block.class, ID);
	}
	
	public static List<Block> loadAll(EntityManager manager, Date start, Date end, String name) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Block> query = builder.createQuery(Block.class);
		Root<Block> from = query.from(Block.class);
		List<Predicate> predicates = new ArrayList<>();
		if (start != null) {
			predicates.add(builder.ge(from.get("start"), start.getTime()));
		}
		if (end != null) {
			predicates.add(builder.le(from.get("end"), end.getTime()));
		}
		if (name != null) {
			predicates.add(builder.equal(from.get("name"), name));
		}

		Predicate where = builder.and(predicates.toArray(new Predicate[predicates.size()]));
		query = query.select(from).where(where);
		List<Block> result = manager.createQuery(query).getResultList();
		return result;
	}
}
