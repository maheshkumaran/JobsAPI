package com.api.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.api.job.pojo.CountryMaster;
import com.api.job.pojo.JobCategoryMaster;
import com.api.job.pojo.JobDetails;
import com.api.job.pojo.JobDetailsCSVMapperVO;
import com.api.job.pojo.JobLanguageMaster;
import com.api.job.pojo.JobSkillsMaster;
import com.api.job.pojo.JobTypeMaster;
import com.api.job.pojo.LanguageMaster;
import com.api.job.pojo.SkillMaster;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;




@Component	
public class CSVReader {
	
	@Autowired
	private ResourceLoader resource;
	
	public  List<JobDetails> readJobSV() throws IOException{
		List<JobDetails> jobDetailsList=new ArrayList<JobDetails>();
		//Reader reader = Files.newBufferedReader(Paths.get("C:\\Users\\maheshj\\KafkaPOC\\jobs.csv"));
		Reader reader = new BufferedReader(new InputStreamReader(resource.getResource("classpath:jobs.csv").getInputStream()));
        ColumnPositionMappingStrategy<JobDetailsCSVMapperVO> strategy = new ColumnPositionMappingStrategy<JobDetailsCSVMapperVO>();
        strategy.setType(JobDetailsCSVMapperVO.class);
        String[] memberFieldsToBindTo = {"jobSkillId", "jobLanguageId",
        		"countryid", "jobTypeId","jobCategoryId",
        		"minPayScale","maxPayScale","minExperience","maxExperience"};
        strategy.setColumnMapping(memberFieldsToBindTo);

        CsvToBean<JobDetailsCSVMapperVO> csvToBean = new CsvToBeanBuilder<JobDetailsCSVMapperVO>(reader)
                .withMappingStrategy(strategy)
                .withSkipLines(1)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
        Iterator<JobDetailsCSVMapperVO> jobDetailsIterator = csvToBean.iterator();
        JobDetails jobDetails=null;
        while(jobDetailsIterator.hasNext()) {
        	jobDetails=new JobDetails();
        	JobDetailsCSVMapperVO jobDetailsCSVMapperVO=jobDetailsIterator.next();
            jobDetails.setMinPayScale(new Integer(jobDetailsCSVMapperVO.getMinPayScale()));
            jobDetails.setMaxPayScale(new Integer(jobDetailsCSVMapperVO.getMaxPayScale()));
            jobDetails.setMinExperience(new Integer(jobDetailsCSVMapperVO.getMinExperience()));
            jobDetails.setMaxExperience(new Integer(jobDetailsCSVMapperVO.getMaxExperience()));
            jobDetails.setCountryMaster(mapCountryMaster(jobDetailsCSVMapperVO.getCountryid()));
            jobDetails.setJobCategoryMaster(mapJobCategory(jobDetailsCSVMapperVO.getJobCategoryId()));
            jobDetails.setJobTypeMaster(mapJobTypeMaster(jobDetailsCSVMapperVO.getJobTypeId()));
            jobDetails.setJoblanguageSet(mapJobLanguageMaster(jobDetailsCSVMapperVO.getJobLanguageId(),jobDetails));
            jobDetails.setJobsSkills(mapJobSkillsMaster(jobDetailsCSVMapperVO.getJobSkillId(),jobDetails));
            jobDetailsList.add(jobDetails);
        }
       return jobDetailsList;
	}
	
	public static CountryMaster mapCountryMaster(String countryId) {
    		CountryMaster countryMaster=new CountryMaster();
    		countryMaster.setCountry_id(new Long(countryId));
    		return countryMaster;
	}
	
	public static JobCategoryMaster mapJobCategory(String jobCategoryId) {
		JobCategoryMaster jobCategoryMaster=new JobCategoryMaster();
		jobCategoryMaster.setJob_category_id(new Long(jobCategoryId));
		return jobCategoryMaster;
	}
    
	public static JobTypeMaster mapJobTypeMaster(String jobTypeId) {
		JobTypeMaster jobTypeMaster=new JobTypeMaster();
		jobTypeMaster.setJob_Type_id(new Long(jobTypeId));
		return jobTypeMaster;
	}
	
	public static Set<JobSkillsMaster> mapJobSkillsMaster(String jobSkillId,JobDetails jobDetails){
		Set<JobSkillsMaster> jobSkillsMastersSet=new HashSet<JobSkillsMaster>();
		JobSkillsMaster jobSkillsMaster=null;
		SkillMaster skillMaster=null;
		String[] delimitedJobskills=jobSkillId.split("#");
		for(String jobSkills:delimitedJobskills) {
			jobSkillsMaster=new JobSkillsMaster();
			skillMaster=new SkillMaster();
			skillMaster.setSkill_id(new Long(jobSkills));
			jobSkillsMaster.setSkillsMaster(skillMaster);
			jobSkillsMaster.setJobDetails(jobDetails);
			jobSkillsMastersSet.add(jobSkillsMaster);
		}
		return jobSkillsMastersSet;
	}
	
	public static Set<JobLanguageMaster> mapJobLanguageMaster(String jobLanguageId,JobDetails jobDetails){
		Set<JobLanguageMaster> jobLanguageMasters=new HashSet<JobLanguageMaster>();
		JobLanguageMaster jobLanguageMaster=null;
		LanguageMaster languageMaster=null;
		String[] delimitedLanguage=jobLanguageId.split("#");
		for(String jobLanguage:delimitedLanguage) {
			jobLanguageMaster=new JobLanguageMaster();
			languageMaster=new LanguageMaster();
			languageMaster.setLanguage_id(new Long(jobLanguage));
			jobLanguageMaster.setLanguageMaster(languageMaster);
			jobLanguageMaster.setJobDetails(jobDetails);
			jobLanguageMasters.add(jobLanguageMaster);
		}
		return jobLanguageMasters;
	}
}
