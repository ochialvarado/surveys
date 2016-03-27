<%
    String user = request.getParameter("usuario");
    if(user != null && user.equalsIgnoreCase("administrator")){
        String redirectURL = "dashboard.jsp?"+"usuario="+user;
        response.sendRedirect(redirectURL);
    }
    else {
        String redirectWithErrorURL = "index.jsp?error=true";
        response.sendRedirect(redirectWithErrorURL);
    }
%>