<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/common/nav.jsp" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<!---------- join ----------->
    <main class="mb-4">
      <div class="container px-4 px-lg-5">
        <div class="row gx-4 gx-lg-5 justify-content-center">
          <div class="col-md-10 col-lg-8 col-xl-7">
            <!-- <p>
              Want to get in touch? Fill out the form below to send me a message and I will get back
              to you as soon as possible!
            </p> -->
            <div class="my-5">
              <!-- * * * * * * * * * * * * * * *-->
              <!-- * * SB Forms Contact Form * *-->
              <!-- * * * * * * * * * * * * * * *-->
              <!-- This form is pre-integrated with SB Forms.-->
              <!-- To make this form functional, sign up at-->
              <!-- https://startbootstrap.com/solution/contact-forms-->
              <!-- to get an API token!-->
              <form method="post" action="/user/signup" id="contactForm" data-sb-form-api-token="API_TOKEN">
                <div class="form-floating">
                  <input
                  	name="userId"
                    class="form-control"
                    id="id"
                    type="text"
                    placeholder="Enter your name..."
                    data-sb-validations="required"
                    value="${empty recommId ? '' : recommId }"
                  />
                  <label for="id">ID</label>
                  <button type="button" id="list-btn" onclick="location.href='${root}/user?action=recommId';"
						class="btn btn-outline-dark rounded text-uppercase btn-sm">아이디추천</button>
                  <!-- <div class="invalid-feedback" data-sb-feedback="name:required">
                    A name is required.
                  </div> -->
                </div>
                <div class="form-floating">
                  <input
                  	name="userPass"
                    class="form-control"
                    id="pass"
                    type="password"
                    placeholder="Enter your email..."
                    data-sb-validations="required"
                  />
                  <label for="pass">Password</label>
                  <div class="invalid-feedback" data-sb-feedback="email:required">
                    An email is required.
                  </div>
                  <div class="invalid-feedback" data-sb-feedback="email:email">
                    Email is not valid.
                  </div>
                </div>
                <div class="form-floating">
                  <input
                  	name="userName"
                    class="form-control"
                    id="name"
                    type="text"
                    placeholder="Enter your phone number..."
                    data-sb-validations="required"
                  />
                  <label for="name">Name</label>
                  <!-- <div class="invalid-feedback" data-sb-feedback="name:required">
                    A name is required.
                  </div> -->
                </div>
                <div class="form-floating">
                  <input
                  	name="email"
                    class="form-control"
                    id="email"
                    type="email"
                    placeholder="Enter your email..."
                    data-sb-validations="required"
                  />
                  <label for="email">Email</label>
                  <div class="invalid-feedback" data-sb-feedback="phone:required">
                    A phone number is required.
                  </div>
                </div>
                <div class="form-floating">
                  <input
                  	name="phone"
                    class="form-control"
                    id="phone"
                    type="text"
                    placeholder="Enter your email..."
                    data-sb-validations="required"
                  />
                  <label for="phone">Phone</label>
                  <div class="invalid-feedback" data-sb-feedback="phone:required">
                    A phone number is required.
                  </div>
                </div>
                <br />
                <!-- Submit success message-->
                <!---->
                <!-- This is what your users will see when the form-->
                <!-- has successfully submitted-->
                <div class="d-none" id="submitSuccessMessage">
                  <div class="text-center mb-3">
                    <div class="fw-bolder">Form submission successful!</div>
                    To activate this form, sign up at
                    <br />
                    <a href="https://startbootstrap.com/solution/contact-forms"
                      >https://startbootstrap.com/solution/contact-forms</a
                    >
                  </div>
                </div>
                <!-- Submit error message-->
                <!---->
                <!-- This is what your users will see when there is-->
                <!-- an error submitting the form-->
                <div class="d-none" id="submitErrorMessage">
                  <div class="text-center text-danger mb-3">Error sending message!</div>
                </div>
                <!-- Submit Button-->
                <button
                  class="btn btn-primary text-uppercase"
                  id="submitButton"
                  type="submit"
                >
                SIGN UP
                </button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </main>


<%@ include file="/WEB-INF/views/common/footer.jsp" %>