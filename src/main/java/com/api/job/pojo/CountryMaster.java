package com.api.job.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Country_Master")
public class CountryMaster {
	
	    @Id
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
	    private Long country_id;
	    @Column(name="country_Description")
	    private String countryDescription;
	    
		public Long getCountry_id() {
			return country_id;
		}
		public void setCountry_id(Long country_id) {
			this.country_id = country_id;
		}
		public String getCountryDescription() {
			return countryDescription;
		}
		public void setCountryDescription(String countryDescription) {
			this.countryDescription = countryDescription;
		}
	    
	    
		

}
