package com.api.job.pojo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchCriteria implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1745270186763785818L;
	private String key;
	private String operation;
	private Object value;
	
	
	@JsonProperty("languageDescription")
	private String languageDescription;
	
	@JsonProperty("skillDescription")
	private String skillDescription;
	
	private Integer minExperience;
	
	private Integer maxExperience;
	
	private Integer minPayScale;
	
	private Integer maxPayScale;
	
	public SearchCriteria() {
		
	}
	
	public String getLanguageDescription() {
		return languageDescription;
	}

	public void setLanguageDescription(String languageDescription) {
		this.languageDescription = languageDescription;
	}

	public String getSkillDescription() {
		return skillDescription;
	}

	public void setSkillDescription(String skillDescription) {
		this.skillDescription = skillDescription;
	}

	public Integer getMinExperience() {
		return minExperience;
	}

	public void setMinExperience(Integer minExperience) {
		this.minExperience = minExperience;
	}

	public Integer getMaxExperience() {
		return maxExperience;
	}

	public void setMaxExperience(Integer maxExperience) {
		this.maxExperience = maxExperience;
	}

	public Integer getMinPayScale() {
		return minPayScale;
	}

	public void setMinPayScale(Integer minPayScale) {
		this.minPayScale = minPayScale;
	}

	public Integer getMaxPayScale() {
		return maxPayScale;
	}

	public void setMaxPayScale(Integer maxPayScale) {
		this.maxPayScale = maxPayScale;
	}

	public SearchCriteria(String key,String operation,Object value) {
		this.key=key;
		this.operation=operation;
		this.value=value;
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	

}
