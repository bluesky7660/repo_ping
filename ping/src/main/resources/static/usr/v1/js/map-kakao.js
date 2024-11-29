$(document).ready(function($) {
  var mapContainer = document.getElementById('map');
  var mapOption = {
      center: new kakao.maps.LatLng(36.219334746848095, 127.86117181879779), // 지도의 중심좌표
      level: 13 // 지도의 확대 레벨
  };
 
  var map = new kakao.maps.Map(mapContainer, mapOption); 
  map.setMaxLevel(13);

  // 대한민국 경계 설정 (kakao.maps.LatLngBounds 사용)
  // 제한할 영역의 좌표 (예시: 대한민국의 대략적인 좌표)
var pos1 = new kakao.maps.LatLng(34.0, 126.0); // 남서쪽 좌표
var pos2 = new kakao.maps.LatLng(38.5, 127.5); // 북동쪽 좌표

// LatLngBounds 객체 생성
var bounds = new kakao.maps.LatLngBounds(pos1, pos2);

// 지도 중심을 제한할 영역으로 설정하는 함수
var constrainBounds = function() {
    var center = map.getCenter(); // 현재 지도 중심 좌표 가져오기
    var zoomLevel = map.getLevel(); // 현재 지도 레벨 가져오기
    var clipLat, clipLng, sw, ne;

    // 지도 줌 레벨에 따라 경계 범위를 동적으로 설정
    if (zoomLevel <= 11) {
        // 기본 대한민국 범위
        pos1 = new kakao.maps.LatLng(33.0, 124.0);
        pos2 = new kakao.maps.LatLng(38.5, 127.5);
    } else {
        // 제주도를 포함할 수 있도록 경계를 확장 (줌 11 이상일 때)
        pos1 = new kakao.maps.LatLng(34.0, 126.0); // 남서쪽 좌표 확장
        pos2 = new kakao.maps.LatLng(38.5, 127.5); // 북동쪽 좌표 확장
    }

    // LatLngBounds 객체 재설정
    bounds = new kakao.maps.LatLngBounds(pos1, pos2);

    // 지도 중심이 설정된 경계 밖으로 나갈 경우
    if (!bounds.contain(center)) {
        sw = bounds.getSouthWest(); // 경계의 남서쪽 좌표
        ne = bounds.getNorthEast(); // 경계의 북동쪽 좌표

        // 제한된 경계 안에서 중심 좌표를 계산
        clipLat = Math.min(Math.max(sw.getLat(), center.getLat()), ne.getLat());
        clipLng = Math.min(Math.max(sw.getLng(), center.getLng()), ne.getLng());

        // 제한된 좌표로 지도 중심을 설정
        map.setCenter(new kakao.maps.LatLng(clipLat, clipLng));
    }
};

// 지도 이동 시 경계 제한 적용
kakao.maps.event.addListener(map, 'drag', constrainBounds);

// 지도 줌 변경 시 경계 제한 적용
kakao.maps.event.addListener(map, 'zoom_changed', constrainBounds);


  // **************************** Map 마커 클러스터 영역 **************************
  
  
  // var mapContainer = document.getElementById('map');
  // var mapOption = {
  //     center: new kakao.maps.LatLng(37.5665, 126.9780), // 서울 중심 좌표
  //     level: 12
  // };
  // var map = new kakao.maps.Map(mapContainer, mapOption);
  
  // 마커 생성 및 CustomOverlay 연결
  // 마커와 인포윈도우 생성 함수
  // var loadedMarkersData = [
  //   {
  //     id: 1,
  //     lat: 37.402707,
  //     lng: 126.922044,
  //     title: "화이트 큐브 리조트",
  //     price: 435000,
  //     address: "경기도 안양시 동안구, 대한민국",
  //     url: "https://example.com",
  //     marker_image: "https://via.placeholder.com/150",
  //     ribbon: "특별 할인",
  //     ribbon_corner: "최고 판매",
  //     badge: "추천 상품"
  //   },
  //   {
  //     id: 2,
  //     lat: 37.654213,
  //     lng: 127.060894,
  //     title: "블루 레이크 빌라",
  //     price: 300000,
  //     address: "서울특별시 강남구, 대한민국",
  //     url: "https://example.com",
  //     marker_image: "https://via.placeholder.com/150",
  //     ribbon: "새로운 상품",
  //     ribbon_corner: "독점",
  //     badge: "핫딜"
  //   }
  // ];

  // 마커 클러스터러 생성
  var clusterer = new kakao.maps.MarkerClusterer({
    map: map,               // 클러스터러가 표시될 지도
    averageCenter: true,    // 마커들의 평균 위치에 클러스터 표시
    minLevel: 5,            // 최소 zoom 레벨
    gridSize: 200,
  });
  // function onMarkerClick(Position) {
  //   map.jump(Position,5);
  //   // map.setLevel(5);
  // } 
  function onMarkerClick(position) {
    console.log("Clicked position:", position); // 디버깅용 로그
    
    // 지도 중심을 마커 위치로 이동
    map.setCenter(position);
    
    // 약간의 지연 후 줌 레벨 변경
    setTimeout(function() {
        map.setLevel(5); // 레벨을 3으로 설정
    }, 3000); // 300ms 딜레이
}
  function restLevel() {
    var center = new kakao.maps.LatLng(36.219334746848095, 127.86117181879779);
    // map.pajumpnTo(center,13);
    map.setLevel(13);
    setTimeout(function() {
      map.setCenter(center);
  }, 3000);
  }

  // 마커와 인포윈도우를 한 번에 처리하는 함수
  function createMarker(markerData) {
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
      (markerData.ribbon ? '<div class="ts-marker__feature">' + markerData.ribbon + '</div>' : "") +
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

    // 인포윈도우 HTML 생성
    var infowindowHTML = document.createElement("div");
    infowindowHTML.className ="infobox-wrapper";
    infowindowHTML.innerHTML = '<div class="ts-infobox" data-ts-id="' + markerData.id + '">' +
        '<img src="/usr/v1/template/themeforest-v1.0/assets/img/infobox-close.svg" class="ts-close">' +
        (markerData.ribbon ? '<div class="ts-ribbon">' + markerData.ribbon + '</div>' : '') +
        (markerData.ribbon_corner ? '<div class="ts-ribbon-corner"><span>' + markerData.ribbon_corner + '</span></div>' : '') +
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
                      (markerData.marker_image || "/usr/v1/template/themeforest-v1.0/assets/img/img-item-thumb-01.jpg") + 
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
      // onMarkerClick(markerPosition);
    });

    // 인포윈도우 닫기 버튼 클릭 시 인포윈도우 숨기기
    infowindowHTML.querySelector('.ts-close').addEventListener('click', function () {
      markerHTML.classList.remove("ts-hide-marker");
      // infowindow.setMap(null);
      infowindowHTML.classList.remove("ts-show");
      infowindow.setZIndex(0);
      infowindow.setPosition(restPosition);
      // restLevel();
    });
    
  }
  $.ajax({
    url: '/v1/mapPoint/mapPointSearchList', 
    method: 'POST', 
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
            marker_image: data.marker_image || "/usr/v1/template/themeforest-v1.0/assets/img/marker-default-img.png",  // 기본 이미지 설정
            url: "/v1/mapPoint/mapPointDetail?mpSeq=" + data.mpSeq // URL 예시: 상세 페이지 링크
          };
        });
        loadedMarkersData.forEach(function (markerData) {
            createMarker(markerData);
        });
    },
    error: function(xhr, status, error) {
        console.error("포인트 데이터를 가져오지 못했습니다:", error);
    }
  });
    // 데이터에 있는 마커를 순차적으로 생성
    // loadedMarkersData.forEach(function (markerData) {
    //   createMarker(markerData);
    // });

    // lat=위도, lng=경도
    // var positions = [
    //     {
    //         title: '안양역', 
    //         "lat": 37.402707,
    //         "lng": 126.922044
    //     },
    //     {
    //         title: '안양역 주위 1', 
    //         "lat": 37.400707,
    //         "lng": 126.920044
    //     },
    //     {
    //         title: '안양역 주위 2', 
    //         "lat": 37.403007,
    //         "lng": 126.925044
    //     },
    //     {
    //         title: '안양역 주위 3',
    //         "lat": 37.405707,
    //         "lng": 126.925044
    //     },
    //     {
    //         title: '노원역', 
    //         "lat": 37.654213,
    //         "lng": 127.060894
    //     },
    //     {
    //         title: '노원구', 
    //         "lat": 37.651642,
    //         "lng": 127.066584
    //     }
    // ];
		
    // var infowindow = new kakao.maps.InfoWindow({
    //     content: '<div style="padding:5px;">마커 정보</div>'
    // });
    
    // var infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });

    // var markers = positions.map(function(position) {
    //     var marker = new kakao.maps.Marker({
    //         position: new kakao.maps.LatLng(position.lat, position.lng),
    //         title: position.title
    //     });

    //     var customOverlay = new kakao.maps.CustomOverlay({
    //         position: new kakao.maps.LatLng(position.lat, position.lng),
    //         content: '<div class="custom-marker" style="background-color: #4A90E2; color: white; font-size: 20px; width: 40px; height: 40px; border-radius: 50%; display: flex; align-items: center; justify-content: center;">' + position.number + '</div>',
    //         yAnchor: 1 // 마커의 수직 위치 조정
    //     });

    //     // 마커 클릭 시 인포윈도우 표시
    //     kakao.maps.event.addListener(marker, 'click', function() {
    //         infowindow.setContent('<div style="padding:5px;">' + position.title + '</div>');
    //         infowindow.open(map, marker);
    //     });

    //     // 지도에 마커와 커스텀 오버레이 추가
    //     marker.setMap(map);
    //     customOverlay.setMap(map);

    //     return customOverlay; // customOverlay를 리턴하여 후속 작업에 활용
    // });
    
    // 클러스터러 적용
    // var clusterer = new kakao.maps.MarkerClusterer({
    //     map: map,
    //     averageCenter: true,
    //     minLevel: 5,
    //     markers: markers
    // });
    
})