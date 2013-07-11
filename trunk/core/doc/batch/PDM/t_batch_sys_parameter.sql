prompt Importing table t_batch_sys_parameter...
set feedback off
set define off
insert into t_batch_sys_parameter (BATCH_SYS_PARAMETER_OID, BATCH_PARAMETER, PARAMETER_VALUE, DESCRIPTION, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE)
values (1, 'max_threads', '5', '最大线程数', 'sysdb', to_date('22-12-2009', 'dd-mm-yyyy'), null, null);

insert into t_batch_sys_parameter (BATCH_SYS_PARAMETER_OID, BATCH_PARAMETER, PARAMETER_VALUE, DESCRIPTION, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE)
values (3, 'taskRunMultiple', '10', '任务运行倍数', 'sysdb', to_date('18-01-2010', 'dd-mm-yyyy'), null, null);

insert into t_batch_sys_parameter (BATCH_SYS_PARAMETER_OID, BATCH_PARAMETER, PARAMETER_VALUE, DESCRIPTION, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE)
values (4, 'threadCycleTime', '5000', '线程池轮循周期（秒）', 'sysdb', to_date('18-01-2010', 'dd-mm-yyyy'), null, null);

insert into t_batch_sys_parameter (BATCH_SYS_PARAMETER_OID, BATCH_PARAMETER, PARAMETER_VALUE, DESCRIPTION, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE)
values (2, 'defaultThreadAssignNum', '5', '缺省的工作线程分配数', 'sysdb', to_date('22-12-2009', 'dd-mm-yyyy'), null, null);

prompt Done.
