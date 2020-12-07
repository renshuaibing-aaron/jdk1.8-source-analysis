Oracle 中union的用法


  ```sql
 declare 
 cursor cur_test is select widgetid from t_widget; 
 begin 
 for c in cur_test loop 
 insert into t_tag (tagid,widgetid,userid,tagname,tagdate) values(s_tag_tagid.nextval,c.widgetid,'15895829126','nanjing',sysdate); 
 end loop; 
 commit; 
 end; 
```


```sql
DECLARE CURSOR cur_test IS SELECT
	A .PER_ID
FROM
	T_PER_PERINFO A
WHERE
	A .PER_ID NOT IN (
		SELECT
			b.PER_ID
		FROM
			T_PER_MATERIAL b
		WHERE
			b.TEST_NAME = 'wwww'
	)
AND (A .PER_TYPE = 6 OR A .PER_TYPE = 1) ;

BEGIN
	FOR c IN cur_test loop 
INSERT INTO T_PER_MATERIAL (
		TEST_ID,
		PER_ID,
		TEST_NAME,
		TEST_URL,
		TEST_TYPE,
		ONOROFF,
		STATUS,
		SM_STATUS,
		TEST_QR,
		TEST_COPY,
		YS_FLAG,
		TEST_PREVIEW_URL,
		YS_REMARK,
		TPERALTE_TYPE,
		ASWC_PRINT,
		ASWC_DOWN,
		TEST_QR_FLAG
	)
VALUES
	(
		SEQ_MATERIAL.nextval,
		c.PER_ID,
		'88',
		NULL,
		'7',
		'2',
		'0',
		'0',
		NULL,
		NULL,
		'0',
		NULL,
		NULL,
		NULL,
		NULL,
		NULL,
		'0');
END loop;
END ;
```
