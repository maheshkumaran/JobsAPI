package com.api.job.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Skill_Master")
public class SkillMaster {
	
	    @Id
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
	    private Long skill_id;
	    @Column(name="skill_Description")
	    private String skillDescription;
	    
	    
		public Long getSkill_id() {
			return skill_id;
		}
		public void setSkill_id(Long skill_id) {
			this.skill_id = skill_id;
		}
		public String getSkillDescription() {
			return skillDescription;
		}
		public void setSkillDescription(String skillDescription) {
			this.skillDescription = skillDescription;
		}
	    
		
}
