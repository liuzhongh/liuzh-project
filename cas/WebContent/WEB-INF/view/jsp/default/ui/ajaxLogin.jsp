<%@ page contentType="text/html; charset=UTF-8"%>  

            <%  
                Boolean isFrame = (Boolean)request.getAttribute("isFrame");  
                Boolean isLogin = (Boolean)request.getAttribute("isLogin");  
                %>
                <%
                if(isLogin.booleanValue())
                	response.sendRedirect("http://192.168.3.189:8090/trans");
                else
                	response.sendRedirect("http://192.168.3.189:8090/trans/loginMain.html");
                /* response.getWriter().print("loginSuccessCallback({login:" + (isLogin ? "'success'": "'false'") + ", msg: '用户名或密码错误！'})"); */
            %>  
       
