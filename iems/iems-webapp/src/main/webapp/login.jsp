<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="bg-black">
  <head>
		<meta charset="utf-8" />
		<title>Login</title>
		
		<meta name="description" content="login page" />
		<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<!-- <link rel="shortcut icon" href="assets/img/favicon.png" type="image/x-icon"> -->

		<link href="assets/libs/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
		<link href="assets/libs/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
		
		<!-- Theme style -->
		<link href="assets/css/AdminLTE.css" rel="stylesheet" type="text/css" />

		<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
		  <script src="assets/libs/html5shiv/html5shiv.min.js"></script>
		  <script src="assets/libs/respond.js/respond.min.js"></script>
		<![endif]-->

	</head>
	<body class="bg-black">
		<div class="form-box" id="login-box">
			<div class="header">Sign In</div>
			<form name='f' action="j_spring_security_check" method='POST'>
				<div class="body bg-gray">
					<div class="form-group">
						<input type="text" name="j_username" class="form-control"
							placeholder="Your Email" />
					</div>
					<div class="form-group">
						<input type="password" name="j_password" class="form-control"
							placeholder="Your Password" />
					</div>
					<div class="form-group">
						<label><input type="checkbox" name="_spring_security_remember_me" /> Remember me</label>
					</div>
				</div>
				<div class="footer">
					<button type="submit" class="btn bg-olive btn-block">Sign me in</button>
	
					<p>
						<a href="#">I forgot my password</a>
					</p>
	
					<a href="register.html" class="text-center">Register a new membership</a>
				</div>
			</form>
	
			<div class="margin text-center hide">
				<span>Sign in using social networks</span> <br />
				<button class="btn bg-light-blue btn-circle">
					<i class="fa fa-facebook"></i>
				</button>
				<button class="btn bg-aqua btn-circle">
					<i class="fa fa-twitter"></i>
				</button>
				<button class="btn bg-red btn-circle">
					<i class="fa fa-google-plus"></i>
				</button>
	
			</div>
		</div>

    <!--Basic Scripts-->
		<script src="assets/libs/jquery/jquery-2.1.3.min.js"></script>
		<script src="assets/libs/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
		
		<script src="assets/libs/RSA/jsbn.js"></script>
    <script src="assets/libs/RSA/jsbn2.js"></script>
    <script src="assets/libs/RSA/prng4.js"></script>
    <script src="assets/libs/RSA/rng.js"></script>
    <script src="assets/libs/RSA/rsa.js"></script>
    <script src="assets/libs/RSA/rsa2.js"></script>
		
    <script src="js/iEvent/iEvent.js"></script>
    
    <script src="js/iEvent/iEventLogin.js"></script>
    
  </body>
</html>