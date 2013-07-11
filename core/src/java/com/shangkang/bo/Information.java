/**
 * COPYRIGHT (C) 2010 LY. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of LY.
 *
 * Created By: Liuzh
 * Created On: Dec 15, 2010
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.shangkang.bo;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.shangkang.core.bo.Generic;

public class Information extends Generic<Information>{

	private long id;
	private String name;
	private String password;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public Information mapRow(ResultSet rs, int rowNum) throws SQLException {
		Information info = new Information();
		
		info.setId(rs.getLong("id"));
		info.setName(rs.getString("name"));
		info.setPassword(rs.getString("password"));
		
		return info;
	}
	
	
}
