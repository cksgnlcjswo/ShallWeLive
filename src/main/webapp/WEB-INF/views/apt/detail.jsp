<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="/common/nav.jsp"%>
<%@ include file="/common/header.jsp"%>

<!---------- 아파트 리스트 ----------->
<section>
	<!-- 매매 정보 리스트 -->
	<div class="container">
		<div style="height: 70px"></div>
		<h2 class="text-center mt-5 mb-3">${aptName}</h2>

		<div style="float: left;">
			<!-- request에 데이터가 담기면 보여주는 list_resut.jsp 생성하자! table형태로 ??-->
			<!-- <div class="table" style="display: none;">
				<ul id="aptlist" style="list-style: none;">

				</ul>
			</div> -->
			<c:if test="${not empty houseList }">
				<table class="table table-hover text-center">
					<thead>
						<tr>
							<th>거래금액</th>
							<th>전용면적</th>
							<th>층</th>
							<th>거래날짜</th>
						</tr>
					</thead>
					<tbody id="aptlist">
						<c:forEach items="${houseList }" var="house">
							<tr>
								<td>${house.dealAmount}</td>
								<td>${house.area }</td>
								<td>${house.floor }층</td>
								<td>${house.dealYear }.${house.dealMonth }. ${house.dealDay }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
			<!-- 페이징 처리시 버튼 -->
			<div class="row">
				<ul class="pagination justify-content-center">
					<li class="page-item"><a class="page-link" href="${root}/apt?action=detailPerPage&aptCode=${houseList[0].aptCode}&aptName=${aptName}&pgno=${param.pgno-1}">이전</a></li>
					<c:forEach var="nth" begin="1" end="${pagecnt}">
					<li class="page-item ${nth}"><a class="page-link" href="${root}/apt?action=detailPerPage&aptCode=${houseList[0].aptCode}&aptName=${aptName}&pgno=${nth}">${nth}</a></li>
					<!--<li class="page-item active"><a class="page-link" href="#">2</a>  -->
					</c:forEach>
					<li class="page-item"><a class="page-link" href="${root}/apt?action=detailPerPage&aptCode=${houseList[0].aptCode}&aptName=${aptName}&pgno=${param.pgno+1}">다음</a></li>
				</ul>
			</div>
		
		</div>
		<div id="map" style="width: 50%; height: 400px; float: right;"></div>
	</div>
	
	<!-- MAP -->

	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=f513d2f622a320481c860c0beaef9aa5&libraries=services"></script>
	<!-- MAP API -->
	<script>
    /////////////////////////////////// kakao map api /////////////////////////////////     
    
    var point  = new kakao.maps.LatLng(33.450701, 126.570667);
	//<c:if test="${not empty myAptList}">
	//    point = new kakao.maps.LatLng(${myAptList[0].dongLat}, ${myAptList[0].dongLng});
    //</c:if>
    
    var mapContainer = document.getElementById('map'), // 지도를 표시할 div  
    mapOption = { 
        center: point, // 지도의 중심좌표
        level: 5 // 지도의 확대 레벨
    };

	var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
	
	<c:if test="${not empty myAptList}">
		map.setCenter(new kakao.maps.LatLng(${myAptList[0].dongLat}, ${myAptList[0].dongLng}));
	</c:if>
	
	// 마커를 표시할 위치와 내용을 가지고 있는 객체 배열입니다 
	// 마커를 표시할 위치와 내용을 가지고 있는 객체 배열입니다 
	var positions = [];
	<c:forEach items="${myAptList }" var="apt">
		var obj = {
				content:'<div style="width:150px;text-align:center;padding:6px 0;font-size:5px;font-weight:bold">${apt.aptName}</div>',
				latlng:  new kakao.maps.LatLng(${apt.lat}, ${apt.lng})
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


	<!-- Core theme JS-->
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



<%@ include file="/common/footer.jsp"%>