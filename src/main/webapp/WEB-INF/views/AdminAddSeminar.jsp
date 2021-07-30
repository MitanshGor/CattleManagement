<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="/resources/assets/vendor/bootstrap/css/bootstrap.min.css">
<link href="/resources/assets/vendor/fonts/circular-std/style.css"
	rel="stylesheet">
<link rel="stylesheet" href="/resources/assets/libs/css/style.css">
<link rel="stylesheet"
	href="/resources/assets/vendor/fonts/fontawesome/css/fontawesome-all.css">
<link rel="stylesheet"
	href="/resources/assets/vendor/datepicker/tempusdominus-bootstrap-4.css" />
<link rel="stylesheet"
	href="/resources/assets/vendor/inputmask/css/inputmask.css" />
<%@include file="HeaderFile.jsp"%>
<title>Add\Edit Seminar</title>
<style>
</style>
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
							<h2 class="pageheader-title">Seminar Management</h2>
							<div class="page-breadcrumb">
								<nav aria-label="breadcrumb">
									<ol class="breadcrumb">
										<li class="breadcrumb-item"><a href="adminDashboard"
											class="breadcrumb-link">Admin Dashboard</a></li>
										<li class="breadcrumb-item"><a href="seminarManagement"
											class="breadcrumb-link">Seminar Management</a></li>
										<li class="breadcrumb-item active" aria-current="page">Add\Edit
											Seminar</li>
									</ol>
								</nav>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<!-- ============================================================== -->
					<!-- valifation types -->
					<!-- ============================================================== -->
					<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
						<div class="card">
							<h5 class="card-header">Seminar Details</h5>
							<div class="card-body">
								<form id="validationform">
									<div class="form-group row">
										<label class="col-12 col-sm-3 col-form-label text-sm-right">Seminar
											Title</label>
										<div class="col-12 col-sm-8 col-lg-6">
											<input type="text" placeholder="Seminar Title"
												class="form-control" required>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-12 col-sm-3 col-form-label text-sm-right">Seminar
											Type</label>
										<div class="col-12 col-sm-8 col-lg-6">
											<label
												class="custom-control custom-radio custom-control-inline">
												<input type="radio" name="radio-inline" checked=""
												class="custom-control-input"><span
												class="custom-control-label">Online</span>
											</label> <label
												class="custom-control custom-radio custom-control-inline">
												<input type="radio" name="radio-inline"
												class="custom-control-input"><span
												class="custom-control-label">Offline</span>
											</label> <label
												class="custom-control custom-radio custom-control-inline">
												<input type="radio" name="radio-inline"
												class="custom-control-input"><span
												class="custom-control-label">Online + Offline</span>
											</label>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-12 col-sm-3 col-form-label text-sm-right">Seminar
											Fees</label>
										<div class="col-12 col-sm-8 col-lg-6">
											<input type="number" min="0" placeholder="Seminar Fees"
												class="form-control" required>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-12 col-sm-3 col-form-label text-sm-right">Seminar
											Zoom Link</label>
										<div class="col-12 col-sm-8 col-lg-6">
											<input type="text" placeholder="Seminar Zoom Link"
												class="form-control" readonly>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-12 col-sm-3 col-form-label text-sm-right">Seminar
											Whatsapp Group Link</label>
										<div class="col-12 col-sm-8 col-lg-6">
											<input type="text" placeholder="Seminar Whatsapp Group Link"
												class="form-control" required>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-4 col-sm-3 col-form-label text-sm-right">Seminar
											Banner English</label>
										<div class="col-4 col-sm-8 col-lg-6">
											<input type="file" placeholder="Seminar Banner English"
												class="form-control" required id="englishImageInput"
												onchange="updateImage(this)">
										</div>
										<div class="col-4 col-sm-8 col-lg-6">

											<img src="#" class="img-fluid" width=150px height=150px
												id="englishImage">
										</div>

									</div>
									<div class="form-group row">
										<label class="col-12 col-sm-3 col-form-label text-sm-right">Seminar
											Banner Gujarati</label>
										<div class="col-12 col-sm-8 col-lg-6">
											<input type="text" placeholder="Seminar Banner Gujarat"
												class="form-control" required>
										</div>

									</div>

									<div class="form-group row">
										<label class="col-12 col-sm-3 col-form-label text-sm-right">Seminar
											Description</label>
										<div class="col-12 col-sm-8 col-lg-6">
											<textarea class="form-control"
												id="exampleFormControlTextarea1" rows="10"></textarea>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-12 col-sm-3 col-form-label text-sm-right">Seminar
											Start Date</label>
										<div class="col-12 col-sm-8 col-lg-6">
											<input type="datetime-local" min="0"
												placeholder="Seminar Fees" class="form-control" required>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-12 col-sm-3 col-form-label text-sm-right">Seminar
											End Date</label>
										<div class="col-12 col-sm-8 col-lg-6">
											<input type="datetime-local" min="0"
												placeholder="Seminar Fees" class="form-control" required>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-12 col-sm-3 col-form-label text-sm-right">Registration
											Start Date</label>
										<div class="col-12 col-sm-8 col-lg-6">
											<input type="datetime-local" min="0"
												placeholder="Seminar Fees" class="form-control" required>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-12 col-sm-3 col-form-label text-sm-right">Registration
											End Date</label>
										<div class="col-12 col-sm-8 col-lg-6">
											<input type="datetime-local" min="0"
												placeholder="Seminar Fees" class="form-control" required>
										</div>
									</div>

									<div class="form-group row">
										<label class="col-12 col-sm-3 col-form-label text-sm-right">Registration
											Open</label>
										<div class="col-12 col-sm-8 col-lg-6 pt-1">
											<div class="switch-button switch-button-yesno">
												<input type="checkbox" name="switch19" id="switch19"><span>
													<label for="switch19"></label>
												</span>
											</div>
										</div>
									</div>

									<div class="form-group row text-right">
										<div class="col col-sm-10 col-lg-9 offset-sm-1 offset-lg-0 ">
											<button type="submit" class="btn btn-space btn-primary">Submit</button>
										</div>
									</div>
								</form>

							</div>
						</div>
					</div>
					<!-- ============================================================== -->
					<!-- end valifation types -->
					<!-- ============================================================== -->
				</div>

			</div>

		</div>
	</div>
	<script>
		function updateImage(element) {
			console.log(element)
			if (element.id == 'englishImageInput') {
				document.getElementById('englishImage').src = element.value
			}
		}
		</script>
	<%@include file="AdminFooter.jsp"%>
		<script src="/resources/assets/vendor/jquery/jquery-3.3.1.min.js"></script>
	<!-- 
	<script src="/resources/assets/vendor/bootstrap/js/bootstrap.bundle.js"></script>
    <script src="/resources/assets/vendor/slimscroll/jquery.slimscroll.js"></script>
    <script src="/resources/assets/libs/js/main-js.js"></script>
    <script src="/resources/assets/vendor/inputmask/js/jquery.inputmask.bundle.js"></script>
  -->
</body>
</html>