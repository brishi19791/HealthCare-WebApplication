<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<h2>Welcome ${sessionScope.userAccount.userName}</h2>
<div class="container">

<div class="page-header">
    
<!-- Contact Form - START -->
<div class="container">


 <form class="form-horizontal" action="logout" method="post">
                <div class="form-group">
                            <div class="col-md-12 text-right">
                                <button type="submit" class="btn btn-primary btn-lg">logout</button>
                            </div>
                        </div>
                        </form>
    <div class="row">
        <div class="col-md-12">
            <div class="well well-sm">
           
                <form class="form-horizontal" action="sendtolab" method="get">
                    <fieldset>
                    
                        <legend class="text-center header">Diagnose</legend>
                         <input id="encounterID" name="encounterID" type="hidden" value=${encounterID}>
                        <div class="form-group">
                            <span class="col-md-1 col-md-offset-2 text-center"><i class="fa fa-user bigicon"></i></span>
                            <div class="col-md-8">
                               <b>Test Type</b> <select name="testType">
          						  <option value="X-Ray">X-Ray</option>
          						   <option value="Endoscopy">Endoscope</option>
          						    <option value="Blood Test">Blood Test</option>
         
       							</select>
                            </div>
                        </div>
                        
                        
                                          
                         <div class="form-group">
                            <span class="col-md-1 col-md-offset-2 text-center"><i class="fa fa-phone-square bigicon"></i></span>
                            <div class="col-md-8">
                           <b>Lab Assistant</b> <select name="receiver">
          <c:forEach var="item" items="${labList}">
            <option value="${item.userName}">${item.userName}</option>
          </c:forEach>
        </select>
                            </div>
                        </div>
                       <div class="form-group">
                            <span class="col-md-1 col-md-offset-2 text-center"><i class="fa fa-user bigicon"></i></span>
                            <div class="col-md-8">
                               <b>Message</b>	<input id="message" name="message" type="text" placeholder="Message" class="form-control">
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-12 text-center">
                                <button type="submit" class="btn btn-primary btn-lg">Send to Lab</button>
                            </div>
                        </div>
                    </fieldset>
                </form>
                
                 <form class="form-horizontal" action="backhome1" method="post">
                <div class="form-group">
                            <div class="col-md-12 text-left">
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