<%@page import="com.shangkang.im.MsgManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
MsgManager manager = new MsgManager();

String username = request.getParameter("username");
String password = request.getParameter("password");
String newPassword = request.getParameter("newPassword");

boolean result = manager.changePassword(username, password, newPassword);

out.print("{\"result\":\"" + result + "\"}");
%>
