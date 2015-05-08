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

<h3>You have ${fn:length(employeeList)} Employees</h3>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="well well-sm">
                <form class="form-horizontal" action="deleteemployees" method="post">
                    <fieldset>
                        <legend class="text-center header">Login</legend>

                        <div class="form-group">
                            <span class="col-md-1 col-md-offset-2 text-center"><i class="fa fa-user bigicon"></i></span>
                            <div class="col-md-8">
                                <table border="1" width="700">
         <tr>
         <th align="center">Employee ID</th><th align="center">First Name</th><th align="center">Last Name</th>
         <th align="center">Age</th><th align="center">City</th><th align="center">Email</th><th align="center">Phone Number</th>
         </tr>
         <c:forEach items="${employeeList}" var="employee" varStatus="count">
         <tr><td>${employee.empID}</td><td>${employee.firstName}</td><td>${employee.lastName}</td>
             <td>${employee.age}</td><td>${employee.city}</td><td>${employee.email}</td><td>${employee.phNum}</td>
           
           
         </tr>	
        </c:forEach> 
         
         <tr><td colspan = "8" align="center"></td></tr>    
        
</table>
                            </div>
                        </div>
                        
                       
                        
                    </fieldset>
                </form>
                 <form class="form-horizontal" action="backhome" method="post">
                <div class="form-group">
                            <div class="col-md-12 text-center">
                                <button type="submit" class="btn btn-primary btn-lg">Back</button>
                            </div>
                        </div>
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