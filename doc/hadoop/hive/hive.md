## hive QL



### 1. database 

```sql
-- create a database
CREATE DATABASE my_db;

-- show all databases
SHOW DATABASES;

-- drop a database
DROP DATABASE my_db;
```


### 2. table

```sql
--- creata a table
CREATE [EXTERNAL] TABLE my_table(id int, name String)
COMMENT 'my_table details'
ROW FORMAT DELIMITED
FIELDS TERMINATED BY '\t'
LINES TERMINATED BY '\n'
STORED AS TEXTFILE;

-- insert data... load data from hdfs
LOAD DATA INPATH '/input/sample.txt' 
OVERWRITE INTO TABLE my_table;

-- alter table
ALTER TABLE my_table RENAME TO my_tab;
ALTER TABLE my_table ADD COLUMNS(sex int);
ALTER TABLE my_table DROP COLUMNS sex;
-- columns_name new_columns_name new_type
ALTER TABLE my_table CHANGE id uid bigint;
ALTER TABLE my_table 

-- drop table 
DROP TABLE my_tab;

```



