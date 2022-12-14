<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>WhereIsMyHome</title>
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
<!-- Font Awesome icons (free version)-->
<script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js"
	crossorigin="anonymous"></script>
<!-- Google fonts-->
<link
	href="https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic"
	rel="stylesheet" type="text/css" />
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800"
	rel="stylesheet" type="text/css" />
<!-- Core theme CSS (includes Bootstrap)-->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous" />
<link href="/css/app.css" rel="stylesheet" />
</head>
<body>

	<!-- Navigation-->
	<nav class="navbar navbar-expand-lg navbar-light" id="mainNav">
		<div class="container px-4 px-lg-5">
			<a class="navbar-brand post-subtitle" href="/index">WhereIsMyHome</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarResponsive"
				aria-controls="navbarResponsive" aria-expanded="false"
				aria-label="Toggle navigation">
				Menu <i class="fas fa-bars"></i>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ms-auto py-4 py-lg-0">
					<!-- 로그인 한 상태 -->
					<c:if test="${!empty loginUser }">
						<li class="nav-item">
							<a class="nav-link px-lg-3 py-3 py-lg-4" href="/user/interestArea">관심지역 추가/보기</a>
						</li>
						<li class="nav-item">
							<a style="color:#4de0ff" class="nav-link px-lg-3 py-3 py-lg-4 fw-bold" href="/user/modify">${loginUser.userName } 님</a>
						</li>
						<li class="nav-item">
							<a class="nav-link px-lg-3 py-3 py-lg-4" href="/user/logout">Logout</a>
						</li>
					
					</c:if>
					
					<!-- 로그아웃 상태 -->
					<c:if test="${empty loginUser }">
						<li class="nav-item">
							<a class="nav-link px-lg-3 py-3 py-lg-4" href="http://localhost:8080">게시판</a>
						</li>
						<li class="nav-item">
							<a class="nav-link px-lg-3 py-3 py-lg-4" href="/user/login">Login</a>
						</li>
						<li class="nav-item">
							<a class="nav-link px-lg-3 py-3 py-lg-4" href="/user/signup">sign up</a>
						</li>
					</c:if>
				</ul>
			</div>
		</div>
	</nav>
	<script>
	
	<!-- alert창 설정 --> 
	<c:if test="${!empty msg}">
		alert("${msg}");
	</c:if>
	
		/*!
		* Start Bootstrap - Clean Blog v6.0.8 (https://startbootstrap.com/theme/clean-blog)
		* Copyright 2013-2022 Start Bootstrap
		* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-clean-blog/blob/master/LICENSE)
		*/
		window.addEventListener('DOMContentLoaded', () => {
		    let scrollPos = 0;
		    const mainNav = document.getElementById('mainNav');
		    const headerHeight = mainNav.clientHeight;
		    window.addEventListener('scroll', function() {
		        const currentTop = document.body.getBoundingClientRect().top * -1;
		        if ( currentTop < scrollPos) {
		            // Scrolling Up
		            if (currentTop > 0 && mainNav.classList.contains('is-fixed')) {
		                mainNav.classList.add('is-visible');
		            } else {
		                console.log(123);
		                mainNav.classList.remove('is-visible', 'is-fixed');
		            }
		        } else {
		            // Scrolling Down
		            mainNav.classList.remove(['is-visible']);
		            if (currentTop > headerHeight && !mainNav.classList.contains('is-fixed')) {
		                mainNav.classList.add('is-fixed');
		            }
		        }
		        scrollPos = currentTop;
		    });
		})
	</script>