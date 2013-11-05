package com.shangkang.utils;

import java.util.Comparator;

public class ComparatorUtil implements Comparator<Object> {
	public final static String SORT_DESC = "desc";
	public final static String SORT_ASC = "asc";
	private String sort;
	
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public ComparatorUtil() {
		super();
	}
	
	public ComparatorUtil(String sort) {
		super();
		this.sort = sort;
	}

	public int compare(Object o1, Object o2) {
		String s1 = o1.toString();
		String s2 = o2.toString();
		
		if(SORT_DESC.equals(sort))
			return s2.compareTo(s1);
		else
			return s1.compareTo(s2);
	}
	
}
