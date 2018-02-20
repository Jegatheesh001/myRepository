
<% 
response.setHeader("Pragma", "no-cache");
response.setHeader("Expires", "0");
response.setHeader("Content-Disposition", "filename=\"cardReader.jnlp\";");
response.setContentType("application/x-java-jnlp-file");
%>
<?xml version="1.0" encoding="utf-8"?>
<jnlp spec="1.0+" 
      codebase=<%=request.getScheme() + "://"+ request.getServerName() + ":" 
+ 
request.getServerPort()+ request.getContextPath() + "/" %> 
      href="cardReader.jnlp&#063;username=<%=request.getParameter("username")%>">
    <information>
        <title>Card Reader</title>
        <vendor>Jegatheeesh</vendor>
        <homepage href="http://localhost:8080/" />
        <description>Card Reader</description>
    </information>
    <security>
    <sandbox/>
    </security>
    <resources>
        <j2se version="1.6+" />
        <jar href="CardReader.jar" />
    </resources>
    <application-desc main-class="com.myweb.jnlp.MyJnlp" >
       <argument><%=request.getParameter("username")%></argument>
    </application-desc>
</jnlp>