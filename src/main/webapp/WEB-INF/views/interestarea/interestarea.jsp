<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/nav.jsp"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<!---------- index ----------->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!---------- 아파트 리스트 ----------->
<section>
	<!-- 매매 정보 리스트 -->
	<div class="container">
		<div style="height: 70px"></div>
		<h2 class="text-center mt-5 mb-3">관심지역 설정</h2>
		<form method="post" action="/user/interest" id="aptsearchform"
			style="float: left; width: 70%">

			<div class="row col-md-12 justify-content-center mb-2">
				<div class="form-group col-md-2">
					<select class="form-select bg-light text-dark text-center rounded"
						id="sido" name="sido">
						<option value="">시도선택</option>
					</select>
				</div>
				<div class="form-group col-md-2">
					<select class="form-select bg-light text-dark text-center rounded"
						id="gugun" name="gugun">
						<option value="">구군선택</option>
					</select>
				</div>
				<div class="form-group col-md-2">
					<select class="form-select bg-light text-dark text-center rounded"
						id="dong" name="dong">
						<option value="">동선택</option>
					</select>
				</div>

				<div class="form-group col-md-2">
					<button type="button" id="list-btn"
						class="btn btn-outline-dark rounded text-uppercase btn-sm">add</button>
				</div>



			</div>
		</form>
		<form method="post" action="${root }/user" id="sortform"
			style="float: left; width: 30%">
			<input type="hidden" name="dongCode" value="${param.dongCode}">
			<input type="hidden" name="action" value="sortInterstApt">
			<div class="form-group col-md-6">
				<select class="form-select bg-light text-dark text-center rounded"
					id="sort" name="categoryCode" onchange="this.form.submit()">
					<option value="none">카테고리별추전순</option>
					<option value="N">관광/오락/여가</option>
					<option value="L">부동산</option>
					<option value="F">생활서비스</option>
					<option value="D">소매</option>
					<option value="O">숙박</option>
					<option value="P">스포츠</option>
					<option value="Q">음식</option>
					<option value="R">학원/교육</option>
				</select>
				</div>
		</form>
		<div style="float: left;">
			<!-- request에 데이터가 담기면 보여주는 list_resut.jsp 생성하자! table형태로 ??-->
			<!-- <div class="table" style="display: none;">
				<ul id="aptlist" style="list-style: none;">

				</ul>
			</div > -->

			<table class="table table-hover text-center"
				style="width: 500px; margin: 0 auto">
				<thead>
					<tr>
						<th>시</th>
						<th>구</th>
						<th>동</th>
					</tr>
				</thead>
				<tbody id="aptlist">
					

				</tbody>
			</table>
			<c:if test="${ empty houseList }">
				<div class="text-center">관심 지역을 추가해주세요:)</div>
			</c:if>
		</div>
		<div>

			<table class="table table-hover text-center"
				style="width: 500px; margin: 0 auto">
				<thead>
					<tr>
						<th>아파트이름</th>
						<th>건설 날짜</th>
						<th>상세 주소</th>

					</tr>
				</thead>
				<tbody id="aptlist">
					<c:if test="${not empty houseList }">
						<c:forEach var="item" items="${ houseList }">
							<tr>
								<td>${item.apartmentName}</td>
								<td>${item.buildYear}</td>
								<td>${item.roadName}</td>
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
			</table>
			<c:if test="${ empty houseList }">
				<div class="text-center">관심 지역을 클릭해주세요:)</div>
			</c:if>

		</div>
	</div>



	<!-- 구군시 select box 설정-->
	<script>
	
		//관심지역 
		(
        		async() => {
        			const userId = '${loginUser.userId}';
    				const url = "/interest/" +userId; 
        			try{
        				
    					const response = await fetch(url);
    					console.log(response)
    					if(response.ok){
    						if(response.status != 204) {
    							
	    						const data = await response.json();
	    						   		
	       						let aptlist =document.querySelector("#aptlist")
	       						   						
	       						aptlist.innerHTML = '';
	       						
	       						
	       						data.forEach((item)=>{
	       							aptlist.innerHTML += `
	       								<tr>
	       									<td>\${item.sidoName}</td>
	       									<td>\${item.gugunName}</td>
	       									<td>\${item.dongName}</td>
	       								</tr>
	       							`;
	       						})	
    						}						
    					}
    				}catch(e){
    					alert(e.message)
    				}
        		}		
        	)();
	
	
	
	
        ///////////////////////// select box 설정 (지역, 매매기간) /////////////////////////
        let date = new Date();

        window.onload = function () {
            //let yearEl = document.querySelector("#year");
            //let yearOpt = `<option value="">매매년도선택</option>`;
            //let year = date.getFullYear();
            //for (let i = year; i > year - 20; i--) {
            //    yearOpt += `<option value="\${i}">\${i}년</option>`;
            //}
           // yearEl.innerHTML = yearOpt;

            // 브라우저가 열리면 시도정보 얻기.
            sendRequest("sido", "*00000000");
        };

     

        // https://juso.dev/docs/reg-code-api/
        // let url = "https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes";
        // let regcode = "*00000000";
        // 전국 특별/광역시, 도
        // https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes?regcode_pattern=*00000000

        // 시도가 바뀌면 구군정보 얻기.
        document.querySelector("#sido").addEventListener("change", function () {
            if (this[this.selectedIndex].value) {
                let regcode = this[this.selectedIndex].value.substr(0, 2) + "*00000";
                sendRequest("gugun", regcode);
            } else {
                initOption("gugun");
                initOption("dong");
            }
        });

        // 구군이 바뀌면 동정보 얻기.
        document.querySelector("#gugun").addEventListener("change", function () {
            if (this[this.selectedIndex].value) {
                let regcode = this[this.selectedIndex].value.substr(0, 5) + "*"; // 시, 도에 따른 모든(*)아파트가져오기
                sendRequest("dong", regcode);
            } else {
                initOption("dong");
            }
        });

        function sendRequest(selid, regcode) {
            const url = "https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes";
            let params = "regcode_pattern=" + regcode + "&is_ignore_zero=true";
            fetch(`\${url}?\${params}`)
                .then((response) => response.json())
                .then((data) => addOption(selid, data));
        }

        function addOption(selid, data) {
            let opt = ``;
            initOption(selid);
            switch (selid) {
                case "sido": //시도일 때
                    opt += `<option value="">시도선택</option>`;
                    data.regcodes.forEach(function (regcode) {
                        opt += `
                <option value="\${regcode.code}">\${regcode.name}</option>
                `;
                    });
                    break;
                case "gugun": // 구군일 때
                    opt += `<option value="">구군선택</option>`;
                    for (let i = 0; i < data.regcodes.length; i++) {
                        if (i != data.regcodes.length - 1) {
                            if (
                                data.regcodes[i].name.split(" ")[1] == data.regcodes[i + 1].name.split(" ")[1] &&
                                data.regcodes[i].name.split(" ").length !=
                                data.regcodes[i + 1].name.split(" ").length
                            ) {
                                data.regcodes.splice(i, 1);
                                i--;
                            }
                        }
                    }
                    let name = "";
                    data.regcodes.forEach(function (regcode) {
                        if (regcode.name.split(" ").length == 2) name = regcode.name.split(" ")[1];
                        else name = regcode.name.split(" ")[1] + " " + regcode.name.split(" ")[2];
                        opt += `
                <option value="\${regcode.code}">\${name}</option>
                `;
                    });
                    break;
                case "dong": // 동일 때
                    opt += `<option value="">동선택</option>`;
                    let idx = 2;
                    data.regcodes.forEach(function (regcode) {
                        if (regcode.name.split(" ").length != 3) idx = 3;
                        opt += `
                <option value="\${regcode.code}">\${regcode.name.split(" ")[idx]}</option>
                `;
                    });
            }
            document.querySelector(`#\${selid}`).innerHTML = opt;
        }

        function initOption(selid) {
            let options = document.querySelector(`#\${selid}`);
            options.length = 0;
            // let len = options.length;
            // for (let i = len - 1; i >= 0; i--) {
            //   options.remove(i);
            // }
        }

        ///////////////////////// 관심지역아파트 매매 정보 얻어오기 /////////////////////////
        // 아파트 매매 정보 얻어오기 버튼 클릭 시 
        document.querySelector("#list-btn").addEventListener("click", function () {
        	var form = document.querySelector("#aptsearchform");
        	form.submit();  
        	
        	
        	
        });
    </script>
</section>


<%@ include file="/WEB-INF/views/common/footer.jsp"%>