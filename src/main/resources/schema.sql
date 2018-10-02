create table Country_Master
(
   country_id integer not null auto_increment primary key,
   country_Description varchar(255) not null
   
);
create table Job_Category_Master
(
   job_category_id integer not null auto_increment primary key,
   job_Category_Description varchar(255) not null
);
create table Job_Type_Master
(
   job_Type_id integer not null auto_increment primary key,
   job_Type_Description varchar(255) not null
  
);
create table Language_Master
(
   language_id integer not null auto_increment primary key,
   language_Description varchar(255) not null
  
);
create table Skill_Master
(
   skill_id integer not null auto_increment primary key,
   skill_Description varchar(255) not null
   
);

create table Job_Details(
job_id integer not null auto_increment primary key,
country_id integer not null,
job_type_id integer not null,
job_category_id integer not null,
min_Pay_Scale integer not null,
max_Pay_Scale integer not null,
min_Experience integer not null,
max_Experience integer not null);

create table Job_Skills_Master(
skill_id integer not null auto_increment primary key,
job_id integer not null,
job_skill_id integer not null
);

create table Job_Language_Master(
language_id integer not null auto_increment primary key,
job_id integer not null,
job_language_id integer not null
);

ALTER TABLE Job_Details
ADD FOREIGN KEY (country_id) REFERENCES Country_Master (country_id); 
ALTER TABLE Job_Details
ADD FOREIGN KEY (job_type_id) REFERENCES Job_Type_Master (job_Type_id); 
ALTER TABLE Job_Details
ADD FOREIGN KEY (job_category_id) REFERENCES Job_Category_Master (job_category_id); 
ALTER TABLE Job_Skills_Master
ADD FOREIGN KEY (job_id) REFERENCES Job_Details (job_id);
ALTER TABLE Job_Skills_Master
ADD FOREIGN KEY (job_skill_id) REFERENCES Skill_Master (skill_id);

ALTER TABLE Job_Language_Master
ADD FOREIGN KEY (job_id) REFERENCES Job_Details (job_id); 

ALTER TABLE Job_Language_Master
ADD FOREIGN KEY (job_language_id) REFERENCES Language_Master (language_id); 