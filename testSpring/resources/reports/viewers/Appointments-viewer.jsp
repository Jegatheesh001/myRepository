<%@ page contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%><%@ taglib
	uri="/WEB-INF/taglib/crystal-tags-reportviewer.tld" prefix="crviewer"%><crviewer:viewer
	reportSourceType="reportingComponent" viewerName="Appointments-viewer"
	reportSourceVar="Appointments" isOwnPage="true">
	<crviewer:report
		reportName="D:/Softwares/Windows/Apache Tomcat/apache-tomcat-9.0.0.M26/webapps/testSpring/resources/reports/Appointments.rpt" />
</crviewer:viewer>