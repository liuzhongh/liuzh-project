prompt Importing table t_batch_sys_parameter...
set feedback off
set define off
insert into t_batch_sys_parameter (BATCH_SYS_PARAMETER_OID, BATCH_PARAMETER, PARAMETER_VALUE, DESCRIPTION, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE)
values (1, 'max_threads', '5', '����߳���', 'sysdb', to_date('22-12-2009', 'dd-mm-yyyy'), null, null);

insert into t_batch_sys_parameter (BATCH_SYS_PARAMETER_OID, BATCH_PARAMETER, PARAMETER_VALUE, DESCRIPTION, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE)
values (3, 'taskRunMultiple', '10', '�������б���', 'sysdb', to_date('18-01-2010', 'dd-mm-yyyy'), null, null);

insert into t_batch_sys_parameter (BATCH_SYS_PARAMETER_OID, BATCH_PARAMETER, PARAMETER_VALUE, DESCRIPTION, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE)
values (4, 'threadCycleTime', '5000', '�̳߳���ѭ���ڣ��룩', 'sysdb', to_date('18-01-2010', 'dd-mm-yyyy'), null, null);

insert into t_batch_sys_parameter (BATCH_SYS_PARAMETER_OID, BATCH_PARAMETER, PARAMETER_VALUE, DESCRIPTION, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE)
values (2, 'defaultThreadAssignNum', '5', 'ȱʡ�Ĺ����̷߳�����', 'sysdb', to_date('22-12-2009', 'dd-mm-yyyy'), null, null);

prompt Done.
