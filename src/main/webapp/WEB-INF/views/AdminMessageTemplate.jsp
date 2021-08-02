<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="HeaderFile.jsp"%>
<title>Template Management</title>
</head>
<body>
	<div class="dashboard-main-wrapper">
		<%@include file="AdminNavBar.jsp"%>
		<%@include file="AdminSideBar.jsp"%>
		<div class="dashboard-wrapper">
			<div class="container-fluid dashboard-content ">
				<div class="row">
					<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
						<div class="page-header">
							<h2 class="pageheader-title">Message Templates</h2>
							<p class="pageheader-text">Nulla euismod urna eros, sit amet
								scelerisque torton lectus vel mauris facilisis faucibus at enim
								quis massa lobortis rutrum.</p>
							<div class="page-breadcrumb">
								<nav aria-label="breadcrumb">
									<ol class="breadcrumb">
										<li class="breadcrumb-item"><a href="adminDashboard"
											class="breadcrumb-link">Admin Dashboard</a></li>
										<li class="breadcrumb-item active" aria-current="page">Message
											Template</li>
									</ol>
								</nav>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-12">
						<div id="alert" class="alert alert-primary">
							<span id="msg">${msg }</span>
						</div>
					</div>
				</div>
				<div class="row">
					<!-- ============================================================== -->
					<!-- basic tabs  -->
					<!-- ============================================================== -->
					<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12 mb-5">
						<div class="tab-regular">
							<ul class="nav nav-tabs " id="myTab" role="tablist">
								<li class="nav-item"><a class="nav-link active"
									id="home-tab" data-toggle="tab" href="#home" role="tab"
									aria-controls="home" aria-selected="true">Seminar
										Confirmation Message Template</a></li>
								<li class="nav-item"><a class="nav-link" id="profile-tab"
									data-toggle="tab" href="#profile" role="tab"
									aria-controls="profile" aria-selected="false">Counselling
										Confirmation Message Template</a></li>
								<li class="nav-item"><a class="nav-link" id="contact-tab"
									data-toggle="tab" href="#contact" role="tab"
									aria-controls="contact" aria-selected="false">Counselling
										Cancellation Message Template</a></li>
							</ul>
							<div class="tab-content" id="myTabContent">
								<div class="tab-pane fade show active" id="home" role="tabpanel"
									aria-labelledby="home-tab">
									<p class="lead">Seminar Confirmation Message Template</p>
									<div class="alert alert-info show" role="alert">
										<h4 class="alert-heading">Use Following Syntax</h4>
										<p>Wherever you want the values to be replaced based on
											the seminar and registering user. Thereby place following
											placeholder at that place. (Property : Syntax To Be Used)</p>
										<p class="text-danger">Place Holder are Case Sensitive and it should be written between the curly braces as shown below.</p>
										<ul>
											<li>Registered User Name : {user name}</li>
											<li>Seminar Title : {seminar title}</li>
											<li>Seminar Date and Time: {seminar date}</li>
											<li>Seminar ID : {seminar id}</li>
											<li>Whatsapp Group Link : {whatsapp link}</li>

										</ul>

									</div>
									<hr>
									<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
										<form action="updateSeminarRegistrationMessage" method="post">
											<div class="form-group">
												<label for="inputText3" class="col-form-label">Subject</label>
												<input id="inputText3" type="text" name="subject" value="${semianrRegistration.subject}" class="form-control">
											</div>
											<div class="form-group">
												<label for="exampleFormControlTextarea1">Body of
													Message</label>
												<textarea class="form-control" name="body"
													id="exampleFormControlTextarea1" rows="15">${semianrRegistration.body}</textarea>
											</div>
											<div class="form-group mx-auto text-center">
												<input type="submit" value="Update Message"
													class="btn bg-dark text-white open">
											</div>
										</form>
									</div>
								</div>
								<div class="tab-pane fade" id="profile" role="tabpanel"
									aria-labelledby="profile-tab">
									<p class="lead">Counselling Confirmation Message Template</p>
									<div class="alert alert-info show" role="alert">
										<h4 class="alert-heading">Use Following Syntax</h4>
										<p>Wherever you want the values to be replaced based on
											the seminar and registering user. Thereby place following
											placeholder at that place. (Property : Syntax To Be Used)</p>
										<p class="text-danger">Place Holder are Case Sensitive and it should be written between the curly braces as shown below.</p>
										<ul>
											<li>Registered User Name : {user name}</li>
											<li>Counselling ID : {counselling id}</li>
											<li>Counselling Date and Time: {counselling date}</li>
										</ul>

									</div>
									<hr>
									<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
										<form action="updateCounsellingRegistrationMessage" method="post">
											<div class="form-group">
												<label for="inputText3" class="col-form-label">Subject</label>
												<input id="inputText3" type="text" name="subject" value="${counsellingRegistration.subject}" class="form-control">
											</div>
											<div class="form-group">
												<label for="exampleFormControlTextarea1"> Body of
													Message</label>
												<textarea class="form-control"
													id="exampleFormControlTextarea1" name="body" rows="15">${counsellingRegistration.body }</textarea>
											</div>
											<div class="form-group mx-auto text-center">
												<input type="submit" value="Update Message"
													class="btn bg-dark text-white open">
											</div>
										</form>
									</div>

								</div>
								<div class="tab-pane fade" id="contact" role="tabpanel"
									aria-labelledby="contact-tab">
									<p class="lead">Counselling Cancellation Message Template</p>
									<div class="alert alert-info show" role="alert">
										<h4 class="alert-heading">Use Following Syntax</h4>
										<p>Wherever you want the values to be replaced based on
											the seminar and registering user. Thereby place following
											placeholder at that place. (Property : Syntax To Be Used)</p>
											<p class="text-danger">Place Holder are Case Sensitive and it should be written between the curly braces as shown below.</p>
										<ul>
											<li>Registered User Name : {user name}</li>
											<li>Counselling ID : {counselling id}</li>
											<li>Counselling Date and Time: {counselling date}</li>
										</ul>

									</div>
									<hr>
									<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
										<form action="updateCounsellingCancellationMessage" method="post">
											<div class="form-group">
												<label for="inputText3" class="col-form-label">Subject</label>
												<input id="inputText3" type="text"  name="subject" value="${counsellingCancellation.subject}" class="form-control">
											</div>
											<div class="form-group">
												<label for="exampleFormControlTextarea1"> Body of
													Message</label>
												<textarea class="form-control"
													id="exampleFormControlTextarea1" name="body" rows="15">${counsellingCancellation.body }</textarea>
											</div>
											<div class="form-group mx-auto text-center">
												<input type="submit" value="Update Message"
													class="btn bg-dark text-white open">
											</div>
										</form>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<%@include file="AdminFooter.jsp"%>
			</div>
		</div>
		</div>
</body>
</html>