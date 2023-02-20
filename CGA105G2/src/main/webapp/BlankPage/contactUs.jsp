<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <title>🗃️管理</title>
</head>
<body>
<!-- header start -->
<c:if test="${storeId > 0}">
    <%@ include file="/front-end/store/01h/headerin.jsp" %>
</c:if>
<c:if test="${memId > 0}">
    <%@ include file="/front-end/Member/01h/headerin.jsp" %>
</c:if>
<c:if test="${ (memId ==0)&& (storeId == 0)&& (empId == 0)}">
    <%@ include file="/front-end/Member/01h/headerout.jsp" %>
</c:if>
<!-- header end -->
<!-- main -->
	<div class="container-fluid container">
			<main role="main" class="col-md-9 m-sm-auto col-lg-10 px-md-4 my-5 container">
				<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom text-center" >
					<h1 class="h2 text-center mx-auto mt-5">🔆聯繫我們</h1>
					<div class="btn-toolbar mb-2 mb-md-0"></div>
				</div>
				<!-- "contacts" section start -->
				<section class="section container" id="contacts">
					<div class="section-content container">
						<div class="">
							<div class="col-12 col-lg-12 mb-14 mb-lg-0 text-center">
								<small class="fs-1 font-family-secondary text-uppercase font-weight-bold letter-spacing-caption text-muted text-center">
									We answer within 24 hours </small>
								<h1 class="text-center">Contact Us</h1>
								<form action="/CGA105G2/SendMailServlet" class="row mt-17" method="POST">
									<div class="col-12 ">
										<div class="form-group">
											<label for="name" class="form-label">Name</label> <input type="text" class="form-control" name="name" id="name"/>
										</div>
									</div>
									<div class="col-12 col-sm-6">
										<div class="form-group">
											<label for="phone" class="form-label">Phone</label> <input
												type="text" class="form-control" name="phone" id="phone"/>
										</div>
									</div>
									<div class="col-12 col-sm-6">
										<div class="form-group">
											<label for="email" class="form-label">Email</label> <input
												type="text" class="form-control" name="email" id="email"/>
										</div>
									</div>
									<div class="col-12">
										<div class="form-group">
											<label for="message"
												   class="form-label">Message</label>
											<textarea name="message" id="message" class="form-control"
													  rows="3"></textarea>
										</div>
										<div class="form-group mb-0">
											<input type="hidden" name="action" value= "sendMail">
											<input type="submit" value="發送訊息"
												   class="btn btn-primary btn-lg btn-block fs-5">

										</div>
									</div>
								</form>
							</div>

						</div>
					</div>
				</section>
			</main>
	</div>
<!-- main -->
<!-- footer start -->
<%@ include file="/front-end/Member/01h/footerin.jsp" %>
<!-- footer end -->
<!-- sidebar menu Class -->
<script>
    $("a:contains(📭聯繫我們)").closest("a").addClass("active disabled topage");
</script>
</body>
</html>