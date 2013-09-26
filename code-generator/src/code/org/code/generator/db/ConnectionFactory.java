/**
 * COPYRIGHT (C) 2012 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2012-4-23
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package org.code.generator.db;

import static org.code.generator.util.StringUtility.stringHasValue;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.code.generator.config.JDBCConnectionConfiguration;

public class ConnectionFactory {

	private static ConnectionFactory instance = new ConnectionFactory();

    public static ConnectionFactory getInstance() {
        return instance;
    }

    /**
	 *  
	 */
    private ConnectionFactory() {
        super();
    }

    public Connection getConnection(JDBCConnectionConfiguration config)
            throws SQLException {
        Driver driver = getDriver(config);

        Properties props = new Properties();

        if (stringHasValue(config.getUserId())) {
            props.setProperty("user", config.getUserId()); //$NON-NLS-1$
        }

        if (stringHasValue(config.getPassword())) {
            props.setProperty("password", config.getPassword()); //$NON-NLS-1$
        }
        
        if (stringHasValue(config.getRemarksReporting())) {
            props.setProperty("remarksReporting", config.getRemarksReporting()); //$NON-NLS-1$
        }

        props.putAll(config.getProperties());

        Connection conn = driver.connect(config.getConnectionURL(), props);

        if (conn == null) {
            throw new SQLException("RuntimeError.7"); //$NON-NLS-1$
        }

        return conn;
    }

    private Driver getDriver(JDBCConnectionConfiguration connectionInformation) {
        String driverClass = connectionInformation.getDriverClass();
        Driver driver;

        try {
            Class<?> clazz = ObjectFactory.externalClassForName(driverClass);
            driver = (Driver) clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e); //$NON-NLS-1$
        }

        return driver;
    }
    
    public static void main(String[] s) throws Exception
    {
    	JDBCConnectionConfiguration cfg = new JDBCConnectionConfiguration();
    	
    	cfg.setConnectionURL("jdbc:oracle:thin:@192.168.3.136:1521:sktest");
    	cfg.setDriverClass("oracle.jdbc.OracleDriver");
    	cfg.setUserId("ucr_sk");
    	cfg.setPassword("ucr_sk");
    
    	Connection con = ConnectionFactory.getInstance().getConnection(cfg);
    	
    	DatabaseMetaData dbMeta = con.getMetaData();  
    	
    	String tableTypes = "TABLE";
    	String[] tablesType = null;
    	
    	if(!tableTypes.contains(","))
    		tablesType = new String[]{tableTypes};
    	else	
    		tablesType = tableTypes.split(",");

    	ResultSet tables = dbMeta.getTables(null, "ucr_sk".toUpperCase(), null, tablesType);
    	
    	while(tables.next())
    	{
    		System.out.println(tables.getString("TABLE_NAME"));
    		System.out.println(tables.getString("REMARKS"));
    		System.out.println(tables.getString("TABLE_TYPE"));
    	}
    	
    	System.out.println("********************");
    	
    	ResultSet rs = dbMeta.getPrimaryKeys(null, "TEST", "Infor_detail".toUpperCase()); 
    	
    	while(rs.next())  
        {  
    		System.out.println(rs.getString("TABLE_NAME"));
    		System.out.println(rs.getString("COLUMN_NAME"));
        } 
    	
    	ResultSet rs2 = dbMeta.getColumns(null, "TEST", "Infor_detail".toUpperCase(), null); 
    	
    	while(rs2.next())  
        {  
//    		System.out.println(rs2.getString("COLUMN_NAME"));
//    		System.out.println(rs2.getInt("DATA_TYPE"));
//    		System.out.println(rs2.getInt("COLUMN_SIZE"));
//    		System.out.println(rs2.getInt("DECIMAL_DIGITS"));
//    		System.out.println(rs2.getString("REMARKS"));
//    		System.out.println(rs2.getString("COLUMN_DEF"));
//    		System.out.println(rs2.getString("TABLE_NAME"));
        } 
                    
    	rs.close();
    	rs2.close();
        con.close();  
    }
}
