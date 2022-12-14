<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!---------- 아파트 리스트 ----------->
<section>
	<!-- 매매 정보 리스트 -->
	<div class="container">
		<div style="height: 70px"></div>
		<h2 class="text-center mt-5 mb-3">아파트 매매 정보</h2>
		<form method="GET" action="/apt/list" id="aptsearchform">
			
			<div class="row col-md-12 justify-content-center mb-2">
				<div class="form-group col-md-2">
					<select class="form-select bg-light text-dark text-center rounded" id="sido"
						name="sido" >
						<option value="">시도선택</option>
					</select>
				</div>
				<div class="form-group col-md-2">
					<select class="form-select bg-light text-dark text-center rounded" id="gugun"
						name="gugun">
						<option value="">구군선택</option>
					</select>
				</div>
				<div class="form-group col-md-2">
					<select class="form-select bg-light text-dark text-center rounded" id="dong"
						name="dong">
						<option value="">동선택</option>
					</select>
				</div>
				<div class="form-group col-md-2">
					<select class="form-select bg-dark text-light text-center rounded" id="year"
						name="year">
						<option value="">매매년선택</option>
					</select>
				</div>
				<div class="form-group col-md-2">
					<select class="form-select bg-dark text-light text-center rounded" id="month"
						name="month">
						<option value="">매매월선택</option>
					</select>
				</div>
				<div class="form-group col-md-2">
					<button type="button" id="list-btn" class="btn btn-outline-dark rounded text-uppercase btn-sm">search</button>
				</div>
			</div>
		</form>
		<div style="float: left;">
			<!-- request에 데이터가 담기면 보여주는 list_resut.jsp 생성하자! table형태로 ??-->
			<!-- <div class="table" style="display: none;">
				<ul id="aptlist" style="list-style: none;">

				</ul>
			</div> -->
			
			<table class="table table-hover text-center">
				<thead>
					<tr>
						<th>아파트이름</th>
						<th>거래금액</th>
						<th>면적</th>
						<th>거래날짜</th>
					</tr>
				</thead>
				<tbody id="aptlist">
					
					<c:forEach  items="${info.page}" var="item" varStatus="status" >
						<tr id="apt-tr" onclick="trClick('${item.apartmentName}', '${item.dealAmount }','${item.dealYear }','${item.dealMonth }','${item.dealDay }','${item.area }','${item.lng }','${item.lat }')" data-bs-toggle="modal" data-bs-target="#exampleModal">
							<td >${item.apartmentName}</td>
							<td>${item.dealAmount}</td>
							<td>${item.area}</td>
							<td>${item.dealYear}-${item.dealMonth}-${item.dealDay}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<!-- <div>${info.page.get(0) }</div> -->
		
			
			<%-- 페이지 네비게이션--%>
			${info.navigation}
		</div>
		<div id="map" style="width: 50%; height: 400px; float: right;"></div>
		
		<!-- Modal -->
		<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h1 class="modal-title fs-5" id="exampleModalLabel">아파트 상세 정보</h1>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      <div class="modal-body">
		        
		        <p>아파트이름 : <span id="mymodal-td-aptName"></span></p>
		        <p>거래금액 : <span id="mymodal-td-amount"></span></p>
		        <p>거래날짜 : <span id="mymodal-td-date"></span></p>
		        <p>면적 : <span id="mymodal-td-area"></span></p>
		        <p>위도 : <span id="mymodal-td-lng"></span></p>
		        <p>경도 : <span id="mymodal-td-lat"></span></p>
		        
		        
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
		      </div>
		    </div>
		  </div>
		</div>


	</div>
	
	<!-- modal -->
	<script>
		const trClick = (name, amount, year, month, day, area, lng, lat) => {
			//console.log(name, amount, year, month, day, area, lng, lat);
			document.querySelector("#mymodal-td-aptName").innerHTML = name;
			document.querySelector("#mymodal-td-amount").innerHTML = amount;
			document.querySelector("#mymodal-td-date").innerHTML = year + '-' + month + '-' + day;
			document.querySelector("#mymodal-td-area").innerHTML = area;
			document.querySelector("#mymodal-td-lng").innerHTML = lng;
			document.querySelector("#mymodal-td-lat").innerHTML = lat;
		}
	</script>

	<!-- MAP -->

	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=6d3a6a4b94dd0452d7b57361acaad671&libraries=services"></script>

	<!-- MAP API -->
	<script>
    /////////////////////////////////// kakao map api /////////////////////////////////     
    

    var point  = new kakao.maps.LatLng(33.450701, 126.570667);

    var mapContainer = document.getElementById('map'), // 지도를 표시할 div  
    mapOption = { 
        center: point, // 지도의 중심좌표
        level: 5 // 지도의 확대 레벨
    };

	var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
	
	//지도 센터를 0번째 인덱스로 보내기
	<c:if test="${not empty info.page}">
		map.setCenter(new kakao.maps.LatLng(${info.page[0].lat}, ${info.page[0].lng}));
	</c:if>
	console.log(${myAptList.dongCode})
	// 마커를 표시할 위치와 내용을 가지고 있는 객체 배열입니다 
	// 마커를 표시할 위치와 내용을 가지고 있는 객체 배열입니다 
	
	var positions = [];
	<c:forEach items="${info.page}" var="item">
	/* console.log(${myAptList.houseinfoList[nth-1].lat}) */
		var obj = {
				content:'<div style="width:150px;text-align:center;padding:6px 0;font-size:5px;font-weight:bold">${item.apartmentName}</div>',
				latlng:  new kakao.maps.LatLng(${item.lat}, ${item.lng})
			} 
		positions.push(obj);
		console.log(obj);
	</c:forEach>
	console.log(positions);	
	
	for (var i = 0; i < positions.length; i ++) {
	    // 마커를 생성합니다
	    var marker = new kakao.maps.Marker({
	        map: map, // 마커를 표시할 지도
	        position: positions[i].latlng // 마커의 위치
	    });
	
	    // 마커에 표시할 인포윈도우를 생성합니다 
	    var infowindow = new kakao.maps.InfoWindow({
	        content: positions[i].content // 인포윈도우에 표시할 내용
	    });
	   
	
	    // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
	    // 이벤트 리스너로는 클로저를 만들어 등록합니다 
	    // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
	    kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
	    kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
	}
	
	// 인포윈도우를 표시하는 클로저를 만드는 함수입니다 
	function makeOverListener(map, marker, infowindow) {
	    return function() {
	        infowindow.open(map, marker);
	    };
	}
	
	// 인포윈도우를 닫는 클로저를 만드는 함수입니다 
	function makeOutListener(infowindow) {
	    return function() {
	        infowindow.close();
	    };
	}
    </script>


	<!-- 구군시 select box 설정-->
	<script>
        ///////////////////////// select box 설정 (지역, 매매기간) /////////////////////////
        let date = new Date();

        window.onload = function () {
            let yearEl = document.querySelector("#year");
            let yearOpt = `<option value="">매매년도선택</option>`;
            let year = date.getFullYear();
            for (let i = year; i > year - 20; i--) {
                yearOpt += `<option value="\${i}">\${i}년</option>`;
            }
            yearEl.innerHTML = yearOpt;

            // 브라우저가 열리면 시도정보 얻기.
            sendRequest("sido", "*00000000");
        };

        document.querySelector("#year").addEventListener("change", function () {
            let month = date.getMonth() + 1;
            let monthEl = document.querySelector("#month");
            let monthOpt = `<option value="">매매월선택</option>`;
            let yearSel = document.querySelector("#year");
            let m = yearSel[yearSel.selectedIndex].value == date.getFullYear() ? month : 13;
            for (let i = 1; i < m; i++) {
                monthOpt += `<option value="\${i < 10 ? "0" + i : i}">\${i}월</option>`;
            }
            monthEl.innerHTML = monthOpt;
        });

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

        ///////////////////////// 아파트 매매 정보 얻어오기 /////////////////////////
        // 아파트 매매 정보 얻어오기 버튼 클릭 시 
        document.querySelector("#list-btn").addEventListener("click", function () {
        	var form = document.querySelector("#aptsearchform");
        	form.submit();
        });
    </script>
</section>
