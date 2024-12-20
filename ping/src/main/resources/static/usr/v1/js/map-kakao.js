function initializeMap(mapElementId) {
  // 맵을 초기화할 요소 선택
  const mapElement = document.getElementById(mapElementId);
  if (!mapElement) {
      console.error('맵요소가 없음'); 
      return;
  }

  // 데이터 속성으로부터 정보 읽어오기
  const zoomLevel = parseInt(mapElement.getAttribute('data-ts-map-zoom'), 10);
  const latitude = parseFloat(mapElement.getAttribute('data-ts-map-center-latitude'));
  const longitude = parseFloat(mapElement.getAttribute('data-ts-map-center-longitude'));
  const ptName = mapElement.getAttribute('data-ts-map-Name');

  // 카카오맵 생성
  const container = document.getElementById(mapElementId);
  const options = {
      center: new kakao.maps.LatLng(latitude, longitude),
      level: zoomLevel
  };
  const map = new kakao.maps.Map(container, options);

  // 포인트 마커 생성
  const markerPosition = new kakao.maps.LatLng(latitude, longitude);
  const marker = new kakao.maps.Marker({
      position: markerPosition,
      text: `${ptName}`
  });
  marker.setMap(map);
  const customOverlay = new kakao.maps.CustomOverlay({
    position: markerPosition,
    content: `<div style="background-color: white; padding: 5px; border-radius: 3px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.2); display: inline-block;">${ptName}</div>`,
    yAnchor: 2.3 // 텍스트가 마커 위에 위치하도록 설정
  });
  customOverlay.setMap(map);

  // 마커에 인포윈도우 추가
  // const infowindow = new kakao.maps.InfoWindow({
  //     content: `<div style="padding:5px; text-align:center;">${ptName}</div>`
  // });
  // infowindow.open(map, marker);
}

function createSideBarResult(map, loadedMarkersData, newMarkers) {
  const visibleMarkersOnMap = [];
  const resultsHtml = [];
  const bounds = map.getBounds();
  const restrictBounds = new kakao.maps.LatLngBounds(bounds.getSouthWest(), bounds.getNorthEast());
  // 맵 범위 내의 마커 필터링
  console.log("범위:",restrictBounds);
  console.log("newMarkers:",newMarkers);
  for (let i = 0; i < newMarkers.length; i++) {
      const markerPosition = newMarkers[i].getPosition(); // 마커 위치 가져오기
      if (restrictBounds.contain(markerPosition)) { // LatLngBounds.contains() 사용
          visibleMarkersOnMap.push(i);
      }
  }
  console.log("loadedMarkersData:",loadedMarkersData);
  console.log("visibleMarkersOnMap:",visibleMarkersOnMap);
  // 마커 정보로 사이드바 생성
  for (let i = 0; i < visibleMarkersOnMap.length; i++) {
      const id = visibleMarkersOnMap[i]; 

      resultsHtml.push(
          '<div class="ts-result-link" data-ts-id="' + loadedMarkersData[id]["id"] + '" data-ts-ln="' + newMarkers[id].loopNumber + '">' +
                    '<span class="ts-center-marker"><img src=""></span>' +
                    '<a href="' + loadedMarkersData[id]["url"] + '" class="card ts-item ts-card ts-result">' +
                        ( ( loadedMarkersData[i]["ribbon"] !== undefined ) ? '<div class="ts-ribbon">' + loadedMarkersData[i]["ribbon"] + '</div>' : "" ) +
                        ( ( loadedMarkersData[i]["ribbon_corner"] !== undefined ) ? '<div class="ts-ribbon-corner"><span>' + loadedMarkersData[i]["ribbon_corner"] + '</span></div>' : "" ) +
                        '<div class="card-img ts-item__image" style="background-image: url(' + loadedMarkersData[id]["marker_image"] + ')"></div>' +
                        '<div class="card-body">' +
                            '<figure class="ts-item__info">' +
                                '<h4>' + loadedMarkersData[id]["title"] + '</h4>' +
                                '<aside>' +
                                '<i class="fa fa-map-marker mr-2"></i>' + loadedMarkersData[id]["address"] + '</aside>' +
                            '</figure>' +
                            '<div class="ts-description-lists">'+
                              '<dl>' +
                                '<dt>생성일</dt>' +
                                '<dd>' + loadedMarkersData[id].regDate + '</dd>' +
                              '</dl>'+
                              '<dl>' +
                                '<dt>어종</dt>' +
                                '<dd>' + loadedMarkersData[id].fsNameList + '</dd>' +
                              '</dl>'+
                            '</div>' +
                        '</div>' +
                        '<div class="card-footer">' +
                            '<span class="ts-btn-arrow">Detail</span>' +
                        '</div>' +
                    '</a>' +
                '</div>'
      );
  }
  console.log("resultsHtml:",resultsHtml);
  document.querySelector('.ts-results-wrapper').innerHTML = ''; 
  // 사이드바에 HTML 추가
  document.querySelector('.ts-results-wrapper').innerHTML = resultsHtml.join('');
}
function loadPointData() {
  
}
function createMarker(markerData,clusterer,newMarkers,map) {
  var markerPosition = new kakao.maps.LatLng(markerData.lat, markerData.lng);
  var restPosition = new kakao.maps.LatLng(0, 0);
  // var marker = new kakao.maps.Marker({
  //     position: new kakao.maps.LatLng(markerData.lat, markerData.lng),
  //     title: markerData.title
  // });
  // marker.setMap(map);
  // 마커 HTML 생성
  var markerHTML = document.createElement("div");
  // markerHTML.className ="leaflet-marker-icon leaflet-div-icon leaflet-zoom-animated leaflet-interactive";
  markerHTML.className ="ts-marker-wrapper";
  markerHTML.innerHTML = 
    '<div class="ts-marker" data-ts-id="' + markerData.id + '">' +
    // (markerData.ribbon ? '<div class="ts-marker__feature">' + markerData.ribbon + '</div>' : "") +
    (markerData.title ? '<div class="ts-marker__title">' + markerData.title + '</div>' : "") +
    // (markerData.price ? '<div class="ts-marker__info">₩' + markerData.price.toLocaleString() + '</div>' : "") +
    (markerData.marker_image ? '<div class="ts-marker__image ts-black-gradient" style="background-image: url(' + markerData.marker_image + ')"></div>' :
      '<div class="ts-marker__image ts-black-gradient" style="background-image: url(/usr/v1/template/themeforest-v1.0/assets/img/marker-default-img.png)"></div>') +
  
    '</div>';

  // 커스텀 마커 생성
  var customMarker = new kakao.maps.CustomOverlay({
    position: markerPosition,
    content: markerHTML,
    clickable: true,
    yAnchor: 0.8,
    xAnchor: 0,
    zIndex:1
  });

  // customMarker.setMap(map);
  clusterer.addMarker(customMarker);
  newMarkers.push(customMarker);
  console.log("newMarkers");

  // 인포윈도우 HTML 생성
  var infowindowHTML = document.createElement("div");
  // console.log("markerData:",markerData);
  infowindowHTML.className ="infobox-wrapper";
  infowindowHTML.innerHTML = '<div class="ts-infobox " data-ts-id="' + markerData.id + '">' +
      '<img src="/usr/v1/template/themeforest-v1.0/assets/img/infobox-close.svg" class="ts-close">' +
      // (markerData.ribbon ? '<div class="ts-ribbon">' + markerData.ribbon + '</div>' : '') +
      // (markerData.ribbon_corner ? '<div class="ts-ribbon-corner"><span>' + markerData.ribbon_corner + '</span></div>' : '') +
      '<a href="' + markerData.url + '" class="ts-infobox__wrapper ts-black-gradient">' +
        (markerData.badge ? '<div class="badge badge-dark">' + markerData.badge + '</div>' : '') +
        '<div class="ts-infobox__content">' +
          '<figure class="ts-item__info">' +
          // (markerData.price ? '<div class="ts-item__info-badge">₩' + markerData.price.toLocaleString() + '</div>' : '') +
          (markerData.title ? '<h4>' + markerData.title + '</h4>' : '') +
          (markerData.address ? '<aside><i class="fa fa-map-marker mr-2"></i>' + markerData.address + '</aside>' : '') +
          '</figure>' +
          '<div class="ts-description-lists">'+
            '<dl>' +
              '<dt>생성일</dt>' +
              '<dd>' + markerData.regDate + '</dd>' +
            '</dl>'+
            '<dl>' +
              '<dt>어종</dt>' +
              '<dd>' + markerData.fsNameList + '</dd>' +
            '</dl>'+
          '</div>' +
        '</div>' +
        '<div class="ts-infobox_image" style="background-image: url(' + 
                    (markerData.marker_image !=null ? markerData.marker_image: "/usr/v1/template/themeforest-v1.0/assets/img/FishOn_default.png") + 
                ')"></div>' + 
      '</a>' +
    '</div>';

  // 인포윈도우 생성
  var infowindow = new kakao.maps.CustomOverlay({
    position:restPosition,
    content: infowindowHTML,
    clickable: true,
    yAnchor: 1,
    xAnchor: 0,
    zIndex: 0
  });

  infowindow.setMap(map);
  infowindowHTML.parentElement.classList.add("leaflet-popup", "leaflet-zoom-animated");
  // 마커 클릭 시 인포윈도우 표시
  markerHTML.addEventListener('click', function () {
    markerHTML.classList.add("ts-hide-marker");
    infowindowHTML.classList.add("ts-show");
    infowindow.setZIndex(2);
    infowindow.setPosition(markerPosition);
    infowindowHTML.parentElement.style.opacity=1;
    infowindowHTML.parentElement.style.transform="scale(1)";
    // onMarkerClick(markerPosition);
  });
  // 인포윈도우 닫기 버튼 클릭 시 인포윈도우 숨기기
  infowindowHTML.querySelector('.ts-close').addEventListener('click', function () {
    markerHTML.classList.remove("ts-hide-marker");
    infowindowHTML.parentElement.style.opacity=0;
    infowindowHTML.parentElement.style.transform="scale(0)";
    // infowindow.setMap(null);
    setTimeout(function () {
      infowindowHTML.classList.remove("ts-show");
      // infowindow.setZIndex(0);
      infowindow.setPosition(restPosition);
    }, 500);
    // restLevel();
  });
  
}

$(document).ready(function($) {
  kakao.maps.load(function() {
    
    initializeMap('ts-map-simple');
    var newMarkers = [];
    var mapContainer = document.getElementById('map');
    if(mapContainer){
      var mapOption = {
        center: new kakao.maps.LatLng(36.319334746848095, 127.86117181879779), // 지도의 중심좌표
        level: 13 // 지도의 확대 레벨
      };
    
      var map = new kakao.maps.Map(mapContainer, mapOption); 
      console.log("map:",map.getBounds());
      map.setMaxLevel(13);
      kakao.maps.event.addListener(map, 'click', function(event) {
        var latLngc = event.latLng; // 클릭한 위치의 LatLng 객체
    
        // 클릭한 위치의 위도, 경도 값 가져오기
        var latitudec = latLngc.getLat();  // 위도
        var longitudec = latLngc.getLng(); // 경도
    
        console.log('클릭한 위치의 좌표:', latitudec, longitudec);
      });

      // 대한민국 경계 설정 (kakao.maps.LatLngBounds 사용)
      // 제한할 영역의 좌표 (예시: 대한민국의 대략적인 좌표)
      // var pos1 = new kakao.maps.LatLng(34.0, 126.0); // 남서쪽 좌표
      // var pos2 = new kakao.maps.LatLng(38.5, 127.5); // 북동쪽 좌표

      // // LatLngBounds 객체 생성
      // var restrictBounds = new kakao.maps.LatLngBounds(pos1, pos2);

      // // 지도 중심을 제한할 영역으로 설정하는 함수
      // var constrainBounds = function() {
      //     var center = map.getCenter(); // 현재 지도 중심 좌표 가져오기
      //     var zoomLevel = map.getLevel(); // 현재 지도 레벨 가져오기
      //     var clipLat, clipLng, sw, ne;

      //     // 지도 줌 레벨에 따라 경계 범위를 동적으로 설정
      //     if (zoomLevel <= 11) {
      //         // 기본 대한민국 범위
      //         pos1 = new kakao.maps.LatLng(33.0, 124.0);
      //         pos2 = new kakao.maps.LatLng(38.5, 127.5);
      //     } else {
      //         // 제주도를 포함할 수 있도록 경계를 확장 (줌 11 이상일 때)
      //         pos1 = new kakao.maps.LatLng(34.0, 126.0); // 남서쪽 좌표 확장
      //         pos2 = new kakao.maps.LatLng(38.5, 127.5); // 북동쪽 좌표 확장
      //     }

      //     // LatLngBounds 객체 재설정
      //     restrictBounds = new kakao.maps.LatLngBounds(pos1, pos2);

      //     // 지도 중심이 설정된 경계 밖으로 나갈 경우
      //     if (!restrictBounds .contain(center)) {
      //         sw = restrictBounds.getSouthWest(); // 경계의 남서쪽 좌표
      //         ne = restrictBounds.getNorthEast(); // 경계의 북동쪽 좌표

      //         // 제한된 경계 안에서 중심 좌표를 계산
      //         clipLat = Math.min(Math.max(sw.getLat(), center.getLat()), ne.getLat());
      //         clipLng = Math.min(Math.max(sw.getLng(), center.getLng()), ne.getLng());

      //         // 제한된 좌표로 지도 중심을 설정
      //         map.setCenter(new kakao.maps.LatLng(clipLat, clipLng));
      //     }
      // };

      // // 지도 이동 시 경계 제한 적용
      // kakao.maps.event.addListener(map, 'drag', constrainBounds);

      // // 지도 줌 변경 시 경계 제한 적용
      // kakao.maps.event.addListener(map, 'zoom_changed', constrainBounds);
      
      // 대한민국 기본 경계 설정 (대한민국의 대략적인 좌표)
      let defaultPos1 = new kakao.maps.LatLng(33.0, 124.0); // 남서쪽 좌표
      let defaultPos2 = new kakao.maps.LatLng(38.5, 132.0); // 북동쪽 좌표 (독도를 포함하도록 확장)
      let restrictBounds = new kakao.maps.LatLngBounds(defaultPos1, defaultPos2);

      // 지도 중심을 제한할 영역으로 설정하는 함수
      function constrainBounds() {
          const center = map.getCenter(); // 현재 지도 중심 좌표 가져오기
          const sw = restrictBounds.getSouthWest(); // 경계의 남서쪽 좌표
          const ne = restrictBounds.getNorthEast(); // 경계의 북동쪽 좌표

          // 중심 좌표가 제한된 경계 내에 있는지 확인
          if (!restrictBounds.contain(center)) {
              // 제한된 경계 안에서 새로운 중심 좌표 계산
              const clipLat = Math.min(Math.max(center.getLat(), sw.getLat()), ne.getLat());
              const clipLng = Math.min(Math.max(center.getLng(), sw.getLng()), ne.getLng());

              // 제한된 좌표로 지도 중심 설정
              if (clipLat !== center.getLat() || clipLng !== center.getLng()) {
                  map.setCenter(new kakao.maps.LatLng(clipLat, clipLng));
              }
          }
      }

      // 지도 줌 레벨에 따라 경계를 동적으로 변경
      function updateRestrictBounds() {
          const zoomLevel = map.getLevel(); // 현재 줌 레벨 가져오기

          // 줌 레벨에 따라 제한 경계를 설정
          if (zoomLevel > 11) {
              // 줌 레벨 11 이상일 때 (대한민국 기준 확장)
              restrictBounds = new kakao.maps.LatLngBounds(
                  new kakao.maps.LatLng(33.8, 128.0), // 남서쪽 좌표 확장
                  new kakao.maps.LatLng(38.5, 128.0)  // 북동쪽 좌표 확장 (독도 포함)
              );
          } else {
              // 기본 대한민국 경계
              restrictBounds = new kakao.maps.LatLngBounds(defaultPos1, defaultPos2);
          }
      }

      // 지도 이동 시 경계 제한 적용
      kakao.maps.event.addListener(map, 'dragend',() => {
        updateRestrictBounds();
        constrainBounds();
      });

      // 지도 줌 변경 시 경계 제한 갱신 및 적용
      kakao.maps.event.addListener(map, 'zoom_changed', () => {
          updateRestrictBounds();
          constrainBounds();
      });




      // **************************** Map 마커 클러스터 영역 **************************
      

      // 마커 클러스터러 생성
      var clusterer = new kakao.maps.MarkerClusterer({
        map: map,               // 클러스터러가 표시될 지도
        averageCenter: true,    // 마커들의 평균 위치에 클러스터 표시
        minLevel: 5,            // 최소 zoom 레벨
        gridSize: 50,
      });

      // 마커와 인포윈도우를 한 번에 처리하는 함수
      
      console.log("fsSeqList",fsSeqList);
      const shMpValue = document.querySelector("input[name=shMpValue]");
      console.log("shMpValue.value: " + shMpValue.value);

      document.getElementById("mapPoint_search_form").addEventListener("submit", function (event) {
        event.preventDefault();
      })
      document.getElementById("mapPoint_search_btn").addEventListener("click", function() {
        console.log("클릭성공");
        // $.ajax({
        //   url: '/v1/mapPoint/mapPointSearchList', 
        //   method: 'POST', 
        //   data: {
        //     fsSeqList:fsSeqList,
        //     shMpValue:shMpValue.value
        //   },
        //   success: function(response) {
        //       // 응답 받은 데이터를 바탕으로 마커 생성
        //       // console.log("response:",response);
        //       // console.log("data:",response.data);
        //       var loadedMarkersData = response.data.map(function (data) {
        //         const date = new Date(data.mpRegDate);
        //         const dateOnly = date.toISOString().split('T')[0]; 
        //         return {
        //           id: data.mpSeq,
        //           title: data.mpTitle,
        //           text: data.mpText,
        //           address: data.mpAddress,
        //           type: data.mpType,
        //           delNy: data.mpDelNy,
        //           regDate: dateOnly,
        //           fsNameList: data.fsNameList, // fsNameList는 서버에서 CONCAT된 값일 가능성이므로 필요에 따라 처리
        //           lat: data.mpLatitude,  // 데이터에 위도 값이 포함되어 있다고 가정
        //           lng: data.mpLongitude,  // 데이터에 경도 값이 포함되어 있다고 가정
        //           marker_image: data.path || "/usr/v1/template/themeforest-v1.0/assets/img/FishOn_default.png",  // 기본 이미지 설정
        //           url: "/v1/mapPoint/mapPointDetail?mpSeq=" + data.mpSeq // URL 예시: 상세 페이지 링크
        //         };
        //       });
              
        //       loadedMarkersData.forEach(function (markerData) {
        //           createMarker(markerData,clusterer,newMarkers,map);
        //       });
        //       // console.log("ajax 끝");
        //       // console.log("restrictBounds:",restrictBounds.contain(newMarkers[0].getPosition()));  
        //       createSideBarResult(map,loadedMarkersData,newMarkers);
        //       kakao.maps.event.addListener(map, 'idle', () => {
        //         createSideBarResult(map, loadedMarkersData, newMarkers);
        //       });
        //   },
        //   error: function(xhr, status, error) {
        //       console.error("포인트 데이터를 가져오지 못했습니다:", error);
        //   }
        // });
      });
      document.getElementById("keyword").addEventListener("keydown", (event) => {
        if (event.key === "Enter") {
          // Enter 키가 눌렸을 때 실행
          document.getElementById("mapPoint_search_btn").click(); // 버튼 클릭 이벤트를 트리거
        }
      })
      console.log("넘어감");
      $.ajax({
        url: '/v1/mapPoint/mapPointSearchList', 
        method: 'POST', 
        data: {
          fsSeqList:fsSeqList,
          shMpValue:shMpValue.value
        },
        success: function(response) {
            // 응답 받은 데이터를 바탕으로 마커 생성
            console.log("response:",response);
            console.log("data:",response.data);
            var loadedMarkersData = response.data.map(function (data) {
              const date = new Date(data.mpRegDate);
              const dateOnly = date.toISOString().split('T')[0]; 
              return {
                id: data.mpSeq,
                title: data.mpTitle,
                text: data.mpText,
                address: data.mpAddress,
                type: data.mpType,
                delNy: data.mpDelNy,
                regDate: dateOnly,
                fsNameList: data.fsNameList, // fsNameList는 서버에서 CONCAT된 값일 가능성이므로 필요에 따라 처리
                lat: data.mpLatitude,  // 데이터에 위도 값이 포함되어 있다고 가정
                lng: data.mpLongitude,  // 데이터에 경도 값이 포함되어 있다고 가정
                marker_image: data.path || "/usr/v1/template/themeforest-v1.0/assets/img/FishOn_default.png",  // 기본 이미지 설정
                url: "/v1/mapPoint/mapPointDetail?mpSeq=" + data.mpSeq // URL 예시: 상세 페이지 링크
              };
            });
            
            loadedMarkersData.forEach(function (markerData) {
              createMarker(markerData,clusterer,newMarkers,map);
            });
            // console.log("ajax 끝");
            // console.log("restrictBounds:",restrictBounds.contain(newMarkers[0].getPosition()));
            console.log("restrictBounds:",restrictBounds)
            createSideBarResult(map,loadedMarkersData,newMarkers);
            kakao.maps.event.addListener(map, 'idle', () => {
              console.log("restrictBounds:",restrictBounds)
              createSideBarResult(map, loadedMarkersData, newMarkers);
            });
        },
        error: function(xhr, status, error) {
            console.error("포인트 데이터를 가져오지 못했습니다:", error);
        }
      });
      console.log("끝");
    }
  });
})
