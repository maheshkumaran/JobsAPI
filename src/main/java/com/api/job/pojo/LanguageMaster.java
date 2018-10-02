package com.api.job.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Language_Master")
public class LanguageMaster {
	
	 @Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long language_id;
	 @Column(name="language_Description")
	private String language_Description;
	
	public String getLanguage_Description() {
		return language_Description;
	}
	public void setLanguage_Description(String language_Description) {
		this.language_Description = language_Description;
	}
	public Long getLanguage_id() {
		return language_id;
	}
	public void setLanguage_id(Long language_id) {
		this.language_id = language_id;
	}
	

}
