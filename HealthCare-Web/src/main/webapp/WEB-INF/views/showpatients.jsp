<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix = "form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="font-awesome/css/font-awesome.min.css" />

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</head>
<body>

<h3>You have ${fn:length(patientList)} Patients</h3>
<div class="container">
    <div class="row">
    <form class="form-horizontal" action="logout" method="post">
                <div class="form-group">
                            <div class="col-md-12 text-right">
                                <button type="submit" class="btn btn-primary btn-lg">Logout</button>
                            </div>
                        </div>
                        </form>
        <div class="col-md-12">
            <div class="well well-sm">
                <form class="form-horizontal">
                    <fieldset>
                        <legend class="text-center header">Login</legend>

                        <div class="form-group">
                            <span class="col-md-1 col-md-offset-2 text-center"><i class="fa fa-user bigicon"></i></span>
                            <div class="col-md-8">
                               <table border="1" width="700">
         <tr>
         <th align="center">Patinet ID</th><th align="center">First Name</th><th align="center">Last Name</th>
         <th align="center">Age</th><th align="center">City</th><th align="center">Email</th><th align="center">Phone Number</th><th align="center">DOJ</th>
         <th align="center">Primary Doctor</th><th align="center">Vital Signs</th>
         </tr>
         <c:forEach items="${patientList}" var="patient" varStatus="count">
         <tr><td>${patient.empID}</td><td>${patient.firstName}</td><td>${patient.lastName}</td>
             <td>${patient.age}</td><td>${patient.city}</td><td>${patient.email}</td><td>${patient.phNum}</td><td>${patient.doj}</td>
             <td>${patient.primaryDoctor}</td>
             <td><a href="<c:out value='vitalsignform?primaryDoctor=${patient.primaryDoctor}&patientID=${patient.empID}'/>" >Take Vital Signs</a></td>
           
             
         </tr>	
        </c:forEach> 
         
         <tr><td colspan = "8" align="center"></td></tr>    
        
</table>
                            </div>
                        </div>
                       
                    </fieldset>
                </form>
                
            </div>
        </div>
    </div>
</div>

<style>
    .header {
        color: #36A0FF;
        font-size: 27px;
        padding: 10px;
    }

    .bigicon {
        font-size: 35px;
        color: #36A0FF;
    }
</style>

<!-- Contact Form - END -->

</div>

</body>
</html>