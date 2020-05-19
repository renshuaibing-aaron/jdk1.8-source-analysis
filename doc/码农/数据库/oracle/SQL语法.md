Oracle 中union的用法


  ```sql
 declare 
???????? cursor cur_test is select widgetid from t_widget; 
???? begin 
???????? for c in cur_test loop 
?????????? insert into t_tag (tagid,widgetid,userid,tagname,tagdate) values(s_tag_tagid.nextval,c.widgetid,'15895829126','nanjing',sysdate); 
???????? end loop; 
???? commit; 
???? end; 
```


```sql
DECLARE CURSOR cur_test IS SELECT
	A .EMP_ID
FROM
	T_EMP_EMPINFO A
WHERE
	A .EMP_ID NOT IN (
		SELECT
			b.EMP_ID
		FROM
			T_EMP_MATERIAL b
		WHERE
			b.EMT_NAME = '员工手册同意页'
	)
AND (A .EMP_TYPE = 6 OR A .EMP_TYPE = 1) ;

BEGIN
	FOR c IN cur_test loop 
INSERT INTO T_EMP_MATERIAL (
		EMT_ID,
		EMP_ID,
		EMT_NAME,
		EMT_URL,
		EMT_TYPE,
		ONOROFF,
		STATUS,
		SM_STATUS,
		EMT_QR,
		EMT_COPY,
		YS_FLAG,
		EMT_PREVIEW_URL,
		YS_REMARK,
		TEMPALTE_TYPE,
		DEFAULT_PRINT,
		DEFAULT_DOWN,
		EMT_QR_FLAG
	)
VALUES
	(
		SEQ_MATERIAL.nextval,
		c.EMP_ID,
		'员工手册同意页',
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
