<%@page import="com.shangkang.im.MsgManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
MsgManager manager = new MsgManager();

String username = request.getParameter("username");
String password = request.getParameter("password");
String aliases = request.getParameter("aliases");
String email = request.getParameter("email");

String result = manager.regist(username, password, aliases, email);

out.print("{\"result\":\"" + result + "\"}");
%>
