/**
 * COPYRIGHT (C) 2012 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2013-2-26 10:33:21
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.shangkang.bo;

import com.shangkang.core.bo.Model;

public class FileUpload extends Model{
	
	public static final String[] PERMIT_IMAGE_TYPE = new String[]{"gif","jpg","jpeg","png","bmp"};
	
	public static final String MOD_PRODUCT_DRAFT = "draft";
	public static final String MOD_MERCHANT = "merchant";
	public static final String MOD_SUPPLY = "supply";
	public static final String MOD_PRODUCT = "product";
	public static final String MOD_CUSTOMER = "customer";
	public static final String MOD_NEWS = "news";
	public static final String MOD_NEWS_IMAGE = "news.image";
	public static final String MOD_NEWS_VIDEO = "newsvideo";
	public static final String MOD_QUAL = "qual";
	public static final String MOD_PRODUCT_REPORT = "productReport";
	public static final String MOD_BRAND = "brand";
	public static final String MOD_LOGO = "logo";
	public static final String MOD_GIFT = "gift";
	public static final String MOD_RESUME_HEAD ="resumeHead";
	public static final String COMMEND ="commends";
	public static final String MOD_SUPPLY_DEMAND = "supplyDemand";
	public static final String MOD_COMPANY_DYNAMIC = "companyDynamic";
	
	public static final String FILE_SAVE_SIZE = "size";
	public static final String FILE_SAVE_TYPE = "type";
	public static final String FILE_SAVE_DIR = "saveDir";
	
	public static final String MAIN_FLAG_TRUE = "1";
	public static final String MAIN_FLAG_FALSE = "0";
	
	public static final String BIG_IMAGE_SUFFIX = "_big";
	public static final String SMALL_IMAGE_SUFFIX  = "_small";
	public static final String SRC_IMAGE_SUFFIX = "_src";
	
	public static final Integer BIG_IMAGE_WIDTH = 800;
	public static final Integer BIG_IMAGE_HEIGHT = 800;
	
	public static final Integer SMALL_IMAGE_WIDTH = 310;
	public static final Integer SMALL_IMAGE_HEIGHT = 310;
	
	public static final Integer SQUARE_IMAGE_WIDTH = 180;
	public static final Integer SQUARE_IMAGE_HEIGHT = 180;
	
	
	/**
	 * 文件类型:数据包
	 */
	public static final String[] DATA_PACKAGE = {"zip", "rar", "gz", "bz2"};
	
	/**
	 * 文件类型:文档
	 */
	public static final String[] DOC_TYPE = {"doc", "docx", "xls", "xlsx", "ppt", "txt"};
	
	/**
	 * 文件类型:多媒体
	 */
	public static final String[] MULTI_MEDIA = { "swf", "flv", "mp3", "wav",
			"wma", "wmv", "mid", "avi", "mpg", "asf", "rm", "rmvb"};

	/**
	 * 文件类型:图片
	 */
	public static final String[] IMAGE_TYPE = {"bmp", "jpg", "png", "gif", "jpeg"};
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	public static final String IMAGE_WATER_PRINT_ADD_WORD = "www.3kw.com.cn";
	
	
	/**
	  *	
	  */
	private Long fileUploadId;

	/**
	  *	
	  */
	private String moduleId;

	/**
	  *	
	  */
	private String mapKey;

	/**
	  *	
	  */
	private String fileType;

	/**
	  *	
	  */
	private String url;

	/**
	  *	0:否
1:是
	  */
	private String mainFlag;

	/**
	  *	
	  */
	private String title;

	/**
	  *	
	  */
	private String description;

	/**
	  *	
	  */
	private String creator;

	/**
	  *	
	  */
	private String createTime;

	/**
	  *	
	  */
	private String updateUser;

	/**
	  *	
	  */
	private String updateTime;

	/**
	  *	
	  */
	public Long getFileUploadId()
	{
		return fileUploadId;
	}
	
	/**
	  *	
	  */
	public void setFileUploadId(Long fileUploadId)
	{
		this.fileUploadId = fileUploadId;
	}
	
	/**
	  *	
	  */
	public String getModuleId()
	{
		return moduleId;
	}
	
	/**
	  *	
	  */
	public void setModuleId(String moduleId)
	{
		this.moduleId = moduleId;
	}
	
	/**
	  *	
	  */
	public String getMapKey()
	{
		return mapKey;
	}
	
	/**
	  *	
	  */
	public void setMapKey(String mapKey)
	{
		this.mapKey = mapKey;
	}
	
	/**
	  *	
	  */
	public String getFileType()
	{
		return fileType;
	}
	
	/**
	  *	
	  */
	public void setFileType(String fileType)
	{
		this.fileType = fileType;
	}
	
	/**
	  *	
	  */
	public String getUrl()
	{
		return url;
	}
	
	/**
	  *	
	  */
	public void setUrl(String url)
	{
		this.url = url;
	}
	
	/**
	  *	0:否
1:是
	  */
	public String getMainFlag()
	{
		return mainFlag;
	}
	
	/**
	  *	0:否
1:是
	  */
	public void setMainFlag(String mainFlag)
	{
		this.mainFlag = mainFlag;
	}
	
	/**
	  *	
	  */
	public String getTitle()
	{
		return title;
	}
	
	/**
	  *	
	  */
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	/**
	  *	
	  */
	public String getDescription()
	{
		return description;
	}
	
	/**
	  *	
	  */
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	/**
	  *	
	  */
	public String getCreator()
	{
		return creator;
	}
	
	/**
	  *	
	  */
	public void setCreator(String creator)
	{
		this.creator = creator;
	}
	
	/**
	  *	
	  */
	public String getCreateTime()
	{
		return createTime;
	}
	
	/**
	  *	
	  */
	public void setCreateTime(String createTime)
	{
		this.createTime = createTime;
	}
	
	/**
	  *	
	  */
	public String getUpdateUser()
	{
		return updateUser;
	}
	
	/**
	  *	
	  */
	public void setUpdateUser(String updateUser)
	{
		this.updateUser = updateUser;
	}
	
	/**
	  *	
	  */
	public String getUpdateTime()
	{
		return updateTime;
	}
	
	/**
	  *	
	  */
	public void setUpdateTime(String updateTime)
	{
		this.updateTime = updateTime;
	}
	
	public String toString()
	{
		return "FileUpload [" + 
					"fileUploadId=" + fileUploadId + 
					", moduleId=" + moduleId + 
					", mapKey=" + mapKey + 
					", fileType=" + fileType + 
					", url=" + url + 
					", mainFlag=" + mainFlag + 
					", title=" + title + 
					", description=" + description + 
					", creator=" + creator + 
					", createTime=" + createTime + 
					", updateUser=" + updateUser + 
					", updateTime=" + updateTime + 
				"]";
	}
}

