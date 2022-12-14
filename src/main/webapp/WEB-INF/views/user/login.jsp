<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/common/nav.jsp" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<!---------- login ----------->
    <main class="mb-4">
        <div class="container px-4 px-lg-5">
            <div class="row gx-4 gx-lg-5 justify-content-center">
                <div class="col-md-10 col-lg-8 col-xl-7">
                    <div class="col-md-10 col-lg-8 col-xl-7">
                        <!--  -->
                    </div>
                    
                    <div class="my-5">
                        <!-- * * * * * * * * * * * * * * *-->
                        <!-- * * SB Forms Contact Form * *-->
                        <!-- * * * * * * * * * * * * * * *-->
                        <!-- This form is pre-integrated with SB Forms.-->
                        <!-- To make this form functional, sign up at-->
                        <!-- https://startbootstrap.com/solution/contact-forms-->
                        <!-- to get an API token!-->
                        <form method="post" action="/user/login" id="contactForm" data-sb-form-api-token="API_TOKEN">
                            <div class="form-floating">
                                <input name="userId" class="form-control" id="name" type="text" placeholder="Enter your id..."
                                    data-sb-validations="required" />
                                <label for="ID">ID</label>
                            </div>
                            <div class="form-floating">
                                <input name="userPass" class="form-control" id="pass" type="password" placeholder="Enter your password..."
                                    data-sb-validations="required,password" />
                                <label for="PW">PW</label>
                            </div>

                            <!-- Submit error message-->
                            <!---->
                            <!-- This is what your users will see when there is-->
                            <!-- an error submitting the form-->
                            <div class="d-none" id="submitErrorMessage">
                                <div class="text-center text-danger mb-3">Error sending message!</div>
                            </div>
                            <br>
                            <!-- Submit Button-->
                            <button class="btn btn-primary text-uppercase" id="submitButton"
                                type="submit">Login</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </main>


<%@ include file="/WEB-INF/views/common/footer.jsp" %>