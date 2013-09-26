<!-- 
 *
 * COPYRIGHT (C) 2012 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: ${user}
 * Created On: ${date}
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ************     ***********     *********************************************
 *
 **
 -->
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE struts PUBLIC
    "-//Apache Software Foundation//DTD Struts Configuration 2.0//EN"
    "http://struts.apache.org/dtds/struts-2.0.dtd">

<struts>
	<constant name="struts.ui.theme" value="simple" /> 
	<constant name="struts.custom.i18n.resources" value="messages" />
	<constant name="struts.i18n.encoding" value="utf-8" />
	<constant name="struts.devMode" value="true" />
	<constant name="struts.multipart.saveDir" value="/upload" />
	<constant name="struts.multipart.maxSize" value="1024000000" />
	
	<package name="core" extends="json-default">
		
		<interceptors>
			<interceptor name="exceptionInterceptor" class="com.shangkang.core.exception.Struts2ExceptionInterceptor"></interceptor>
			<interceptor-stack name="defaultInterception">
				<interceptor-ref name="json"></interceptor-ref>
				<interceptor-ref name="defaultStack"></interceptor-ref>
				<interceptor-ref name="exceptionInterceptor"></interceptor-ref>
			</interceptor-stack>
		</interceptors>
		
		<default-interceptor-ref name="defaultInterception"></default-interceptor-ref>
		
		<#foreach prop in result>
		<#assign tableField="${result[0].getFirstCharacterUppercase(prop.tableName)}">
		<#assign tableLowercaseField="${result[0].getFirstCharacterLowercase(prop.tableName)}">
		<action name="${tableLowercaseField}Base">
			<result>/${jspPath}/${tableField}.jsp</result>
		</action>
		</#foreach>
	</package>

</struts>