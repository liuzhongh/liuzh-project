package com.shangkang.manager;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.shangkang.tools.DateHelper;
import com.shangkang.tools.UtilHelper;
import com.shangkang.utils.ComparatorUtil;

public class SystemInfoManager {
	private Logger logger = Logger.getLogger(SystemInfoManager.class);

	public final static String FILE_PATH = "/resources/systemInfo";
	public final static String TIME_TAG = "########### Recode Time ############";
	public final static String USER_TAG = "########## Users ################";
	public final static String DISKS_TAG = "########## Disks usage ################";
	public final static String PROCESSES_TAG = "########## Processes info ################";
	public final static String MEMORY_TAG = "########## Memory info ################";
	public final static String CURRENT_CONNECTIONS_TAG = "########## Current Connections ################";
//	public final static String CONNECTION_VIA_80_TAG = "########## Connection via 80 ################";
//	public final static String CONNECTION_VIA_22_TAG = "########## Connection via 22 ################";
//	public final static String CONNECTION_VIA_11002_TAG = "########## Connection via 11002 ################";
//	public final static String CONNECTION_VIA_9080_TAG = "########## Connection via 9080 ################";
//	public final static String CONNECTION_VIA_8080_TAG = "########## Connection via 8080 ################";
	public final static String END_TAG = "########## End ################";
	
	private String date; 
	
	/**
	 * 获取文件夹下面文件
	 * @param filePath
	 * @return
	 */
	public String[] findSystemResources(String filePath) 
	{
		File file = new File(filePath);
		if(!file.exists())
			return null;
			
		return file.list();
	}
	
	/**
	 * 封装系统资源信息
	 * @return
	 * @throws ServiceException
	 */
	public Map<String, Object> createSystemInfo(String filePath, String fileName)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		
		String info = null;
		try {
			info = FileUtils.readFileToString(new File(filePath + "/" + fileName), "UTF-8");
		} catch (IOException e) {
			logger.error("读系统资源文件出错：", e);
			
			return map;
		}
		
		//String regexUsers = USER_TAG + "\r\n([^#]+)\r\n" + DISKS_TAG + "\r\n([^#]+)\r\n" + PROCESSES_TAG + "\r\n([^#]+)\r\n" + MEMORY_TAG + "\r\n([^#]+)\r\n" + CURRENT_CONNECTIONS_TAG + "\r\n([\\d]*)\r\n" + CONNECTION_VIA_80_TAG + "\r\n([\\d]*)\r\n" + CONNECTION_VIA_22_TAG + "\r\n([\\d]*)\r\n" + CONNECTION_VIA_11002_TAG + "\r\n([\\d]*)\r\n" + CONNECTION_VIA_9080_TAG + "\r\n([\\d]*)\r\n" + CONNECTION_VIA_8080_TAG + "\r\n([\\d]*)\r\nEnd";
		String regexUsers = USER_TAG + "\r\n([^#]+)\r\n" + DISKS_TAG + "\r\n([^#]+)\r\n" + PROCESSES_TAG + "\r\n([^#]+)\r\n" + MEMORY_TAG + "\r\n([^#]+)\r\n" + CURRENT_CONNECTIONS_TAG + "\r\n([^#]+)\r\n" + END_TAG;
		Pattern p = Pattern.compile(regexUsers, 0);
		Matcher m = p.matcher(info);
		if(m.find())
		{
			String str = null;
			if(!UtilHelper.isEmpty(str=m.group(1)))
				map.put("users", UtilHelper.getList(str.split("\r\n")));
			if(!UtilHelper.isEmpty(str=m.group(2)))
				map.put("disks", createInfo(str.split("\r\n")));
			if(!UtilHelper.isEmpty(str=m.group(3)))
				map.put("processes", createInfo(str.split("\r\n")));
			if(!UtilHelper.isEmpty(str=m.group(4)))
				map.put("memorys", createInfo2(str.split("\r\n")));
			if(!UtilHelper.isEmpty(str=m.group(5)))
				map.put("connections", createInfo2(str.split("\r\n")));
			
//			Map<String, Object> connections = new HashMap<String, Object>();
//			String[] keys = {"80", "22", "11002", "9080", "8080", "Current Connections"};
//			connections.put("key", keys);
//			int[] values = new int[keys.length];
//			if(!UtilHelper.isEmpty(str=m.group(5)))
//				values[5] = Integer.parseInt(str);
//			if(!UtilHelper.isEmpty(str=m.group(6)))
//				values[0] = Integer.parseInt(str);
//			if(!UtilHelper.isEmpty(str=m.group(7)))
//				values[1] = Integer.parseInt(str);
//			if(!UtilHelper.isEmpty(str=m.group(8)))
//				values[2] = Integer.parseInt(str);
//			if(!UtilHelper.isEmpty(str=m.group(9)))
//				values[3] = Integer.parseInt(str);
//			if(!UtilHelper.isEmpty(str=m.group(10)))
//				values[4] = Integer.parseInt(str);
//			connections.put("value", values);
//			map.put("connections", connections);
		}
		
		return map;
	} 
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> createInfo(String[] list)
	{
		List<Double> lst = null;
		String title = list[0];
		String[] titles = title.split("[ ]+");
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> type = new ArrayList<String>();
		for (int i = 1; i < list.length; i++) {
			if(!UtilHelper.isEmpty(list[i]))
			{
				String[] s = list[i].split("[ ]+");
				for (int j = 0; j < titles.length; j++) {
					
					if(UtilHelper.isEmpty(map.get(titles[j])))
						lst = new ArrayList<Double>();
					else
						lst = (List<Double>) map.get(titles[j]);
					
					if(s.length-1>j)
						lst.add(newDouble(s[j]));
					
					if(s.length-1 == j)
						type.add(s[j]);
					
					map.put(titles[j], lst);
				}
			}
		}
		
		map.put("key", UtilHelper.getList(titles));
		map.put("type", type);
		return map;
	}
	
	public Map<String, Object> createInfo2(String[] list)
	{
		List<String> keys = new ArrayList<String>();
		List<Double> values = new ArrayList<Double>();

		for (int i = 0; i < list.length; i++) {
			if(!UtilHelper.isEmpty(list[i]))
			{
				String[] s = list[i].split("[ ]+");
				if(s.length>0)
					keys.add(s[0]);
				
				if(s.length>1)
					values.add(new Double(s[1]));
			}
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("key", keys);
		map.put("value", values);
		return map;
	}
	
	public Double newDouble(String str)
	{
		try {
			return new Double(str);
		} catch (Exception e) {
			try {
				double d = new Double(str.substring(0, str.length()-1));
				String unit = str.substring(str.length()-1);
				if("M".equals(unit))
					d = d/1024;
				if("K".equals(unit))
					d = d/1024/1024;
				
				return d;
			} catch (Exception xe) {
				return new Double(0);
			}
		}
	}
	
	/**
	 * 封装系统资源比较信息
	 * @return
	 * @throws ServiceException
	 */
	public Map<String, Object> createCompareSystemInfo(String filePath) 
	{
		Map<String, Map<String, Map<String, Double>>> map = new HashMap<String, Map<String, Map<String, Double>>>();
		List<String> dates = new ArrayList<String>();
		
		String[] fileNames = findSystemResources(filePath);
		for (int i = 0; i < fileNames.length; i++) {
			Map<String, Map<String, Double>> m = createAllSystemInfo(filePath, fileNames[i]);
			map.put(date, m);
			dates.add(date);
		}
		
		Collections.sort(dates, new ComparatorUtil(ComparatorUtil.SORT_ASC));

		Map<String, ArrayList<Double>> pas = new HashMap<String, ArrayList<Double>>();
		Map<String, ArrayList<Double>> mas = new HashMap<String, ArrayList<Double>>();
		Map<String, ArrayList<Double>> cas = new HashMap<String, ArrayList<Double>>();
		for (int i = 0; i < dates.size(); i++) {
			Map<String, Map<String, Double>> m = map.get(dates.get(i));
			
			analysisData(pas, m.get("processes"));
			analysisData(mas, m.get("memorys"));
			analysisData(cas, m.get("connections"));
		}
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("processes", convertDate(pas));
		data.put("memorys", convertDate(mas));
		data.put("connections", convertDate(cas));
		data.put("keys", dates);
		
		return data;
	}
	
	public void analysisData(Map<String, ArrayList<Double>> as, Map<String, Double> map)
	{
		String key = null;
		ArrayList<Double> values = null;
		for (Map.Entry<String, Double> entry : map.entrySet()) {
			key = entry.getKey();
			
			if(UtilHelper.isEmpty(as.get(key)))
				values = new ArrayList<Double>();
			else
				values = as.get(key);
			
			values.add(entry.getValue());
			
			as.put(key, values);
		}
	}
	
	public List<Map<String, Object>> convertDate(Map<String, ArrayList<Double>> as) 
	{
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = null;
		for (Map.Entry<String, ArrayList<Double>> entry : as.entrySet()) {
			map = new HashMap<String, Object>();
			map.put("name", entry.getKey());
			map.put("data", entry.getValue());
			list.add(map);
		}
		
		return list;
	}
	
	public Map<String, Map<String, Double>> createAllSystemInfo(String filePath, String fileName)
	{
		Map<String, Map<String, Double>> map = new HashMap<String, Map<String, Double>>();
		
		String info = null;
		try {
			info = FileUtils.readFileToString(new File(filePath + "/" + fileName), "UTF-8");
		} catch (IOException e) {
			logger.error("读系统资源文件出错：", e);
			
			return map;
		}
		
		String regexUsers = TIME_TAG + "\r\n([^#]+)\r\n" +USER_TAG + "\r\n([^#]+)\r\n" + DISKS_TAG + "\r\n([^#]+)\r\n" + PROCESSES_TAG + "\r\n([^#]+)\r\n" + MEMORY_TAG + "\r\n([^#]+)\r\n" + CURRENT_CONNECTIONS_TAG + "\r\n([^#]+)\r\n" + END_TAG;
		Pattern p = Pattern.compile(regexUsers, 0);
		Matcher m = p.matcher(info);
		if(m.find())
		{
			String str = null;
			if(!UtilHelper.isEmpty(str=m.group(1)))
				date = DateHelper.format(DateHelper.parseTime(str.trim()), DateHelper.TIME_PATTERN_DEFAULT);
			else
				date = "";
			
			String[] list = null;
			if(!UtilHelper.isEmpty(str=m.group(4)))
			{
				list = str.trim().split("\r\n");
				double value = 0;
				String title = list[0];
				String[] titles = title.split("[ ]+");
				Map<String, Double> processes = new HashMap<String, Double>();
				for (int i = 1; i < list.length; i++) {
					if(!UtilHelper.isEmpty(list[i]))
					{
						String[] s = list[i].split("[ ]+");
						for (int j = 0; j < titles.length; j++) {
							
							if(UtilHelper.isEmpty(processes.get(titles[j])))
								value = 0;
							else
								value = (Double) processes.get(titles[j]);
							
							if(s.length-1>j)
							{
								DecimalFormat df=new DecimalFormat("#.00");
								double d = value + newDouble(s[j]);
								d = Double.valueOf(df.format(d));
								processes.put(titles[j], d);
							}
						}
					}
				}
				map.put("processes", processes);
			}
			
			if(!UtilHelper.isEmpty(str=m.group(5)))
			{
				Map<String, Double> memorys = new HashMap<String, Double>();
				list = str.trim().split("\r\n");
				for (int i = 0; i < list.length; i++) {
					if(!UtilHelper.isEmpty(list[i]))
					{
						String[] s = list[i].split("[ ]+");
						if(s.length>1)
							memorys.put(s[0], new Double(s[1]));
					}
				}
				
				map.put("memorys", memorys);
			}
			
			if(!UtilHelper.isEmpty(str=m.group(6)))
			{
				Map<String, Double> connections = new HashMap<String, Double>();
				list = str.trim().split("\r\n");
				for (int i = 0; i < list.length; i++) {
					if(!UtilHelper.isEmpty(list[i]))
					{
						String[] s = list[i].split("[ ]+");
						if(s.length>1)
							connections.put(s[0], new Double(s[1]));
					}
				}
				
				map.put("connections", connections);
			}
			
		}
		
		return map;
	} 
	
}
