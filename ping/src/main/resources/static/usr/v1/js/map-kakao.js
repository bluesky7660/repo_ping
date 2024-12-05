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
  for (let i = 0; i < newMarkers.length; i++) {
      const markerPosition = newMarkers[i].getPosition(); // 마커 위치 가져오기
      if (restrictBounds.contain(markerPosition)) { // LatLngBounds.contains() 사용
          visibleMarkersOnMap.push(newMarkers[i]);
      }
  }

  // 마커 정보로 사이드바 생성
  for (let i = 0; i < visibleMarkersOnMap.length; i++) {
      const id = i;
      let additionalInfoHtml = '';

      if (loadedMarkersData[id]["additional_info"]) {
          for (let a = 0; a < loadedMarkersData[id]["additional_info"].length; a++) {
              additionalInfoHtml +=
                  '<dl>' +
                  '<dt>' + loadedMarkersData[id]["additional_info"][a]["title"] + '</dt>' +
                  '<dd>' + loadedMarkersData[id]["additional_info"][a]["value"] + '</dd>' +
                  '</dl>';
          }
      }

      resultsHtml.push(
          '<div class="ts-result-link" data-ts-id="' + loadedMarkersData[id]["id"] + '" data-ts-ln="' + newMarkers[id].loopNumber + '">' +
                    '<span class="ts-center-marker"><img src="/usr/v1/template/themeforest-v1.0/assets/img/result-center.svg"></span>' +
                    '<a href="' + loadedMarkersData[id]["url"] + '" class="card ts-item ts-card ts-result">' +
                        ( ( loadedMarkersData[i]["ribbon"] !== undefined ) ? '<div class="ts-ribbon">' + loadedMarkersData[i]["ribbon"] + '</div>' : "" ) +
                        ( ( loadedMarkersData[i]["ribbon_corner"] !== undefined ) ? '<div class="ts-ribbon-corner"><span>' + loadedMarkersData[i]["ribbon_corner"] + '</span></div>' : "" ) +
                        '<div href="detail-01.html" class="card-img ts-item__image" style="background-image: url(' + loadedMarkersData[id]["marker_image"] + ')"></div>' +
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

  // 사이드바에 HTML 추가
  document.querySelector('.ts-results-wrapper').innerHTML = resultsHtml.join('');
}
function loadPointData() {
  
}


$(document).ready(function($) {
  kakao.maps.load(function() {
    
    initializeMap('ts-map-simple');
    var newMarkers = [];
    var mapContainer = document.getElementById('map');
    if(mapContainer){
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
      var restrictBounds = new kakao.maps.LatLngBounds(pos1, pos2);

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
          restrictBounds = new kakao.maps.LatLngBounds(pos1, pos2);

          // 지도 중심이 설정된 경계 밖으로 나갈 경우
          if (!restrictBounds .contain(center)) {
              sw = restrictBounds.getSouthWest(); // 경계의 남서쪽 좌표
              ne = restrictBounds.getNorthEast(); // 경계의 북동쪽 좌표

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
        newMarkers.push(customMarker);
        

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
      console.log("fsSeqList",fsSeqList);
      const shMpValue = document.querySelector("input[name=shMpValue]");
      console.log("shMpValue.value: " + shMpValue.value);
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
                marker_image: data.marker_image || "/usr/v1/template/themeforest-v1.0/assets/img/marker-default-img.png",  // 기본 이미지 설정
                url: "/v1/mapPoint/mapPointDetail?mpSeq=" + data.mpSeq // URL 예시: 상세 페이지 링크
              };
            });
            
            loadedMarkersData.forEach(function (markerData) {
                createMarker(markerData);
            });
            // console.log("ajax 끝");
            console.log("restrictBounds:",restrictBounds.contain(newMarkers[0].getPosition()));
            createSideBarResult(map,loadedMarkersData,newMarkers);
            kakao.maps.event.addListener(map, 'idle', () => {
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
