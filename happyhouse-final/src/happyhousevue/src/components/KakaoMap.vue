<template>
  <div :id="mapId" style="width:80%; height:90%; margin:0 auto;"></div>
</template>

<script
  type="text/javascript"
  src="//dapi.kakao.com/v2/maps/sdk.js?appkey=dfebd97b0ebbc269f439edcad3f447ac&libraries=services"
></script>

<script>
let staticMap = null;

export default {
  props: ['propsListData', 'mapId'],
  watch: {
    propsListData: function() {
      this.makeMap();
    },
  },
  methods: {
    makeMap: function() {
      let _this = this;
      let addressKey = '';
      let nameKey = '';
      let length = _this.propsListData.length;

      // 서로다른 xml 키를 위한 전처리
      if (_this.propsListData.length > 0) {
        let keys = Object.keys(_this.propsListData[0]);
        keys.forEach((key, index) => {
          if (key === '법정동') addressKey = key;
          else if (key === 'address') addressKey = key;
          if (key === 'name') nameKey = key;
          else if (key === '연립다세대') nameKey = key;
          else if (key === '아파트') nameKey = key;
        }); //end each
      }

      let mapContainer = document.getElementById(this.mapId),
        mapOption = {
          center: new kakao.maps.LatLng(33.450701, 126.570667),
          level: 7,
        };

      while (mapContainer.firstChild)
        mapContainer.removeChild(mapContainer.firstChild);

      let map = new kakao.maps.Map(mapContainer, mapOption);
      staticMap = map;

      let geocoder = new kakao.maps.services.Geocoder();

      _this.propsListData.forEach((nowObj, index) => {
        let addressValue = '';
        let nameValue = '';
        if (addressKey === '법정동')
          addressValue = nowObj['법정동']._text + ' ' + nowObj['지번']._text;
        else addressValue = nowObj['address'];
        if (nameKey === 'name') nameValue = nowObj['name'];
        else nameValue = nowObj[nameKey]._text;

        geocoder.addressSearch(addressValue, function(result, status) {
          if (status === kakao.maps.services.Status.OK) {
            let coords = new kakao.maps.LatLng(result[0].y, result[0].x);

            let marker = new kakao.maps.Marker({
              map: map,
              position: coords,
            });

            let content =
              '<div class="wrap">' +
              '    <div class="info">' +
              '        <div class="title">' +
              nameValue +
              '        </div>' +
              '        <div class="body">' +
              '            <div class="img">' +
              '                <img src="https://source.unsplash.com/400x400/?home" width="73" height="70">' +
              '           </div>' +
              '           <div class="desc">' +
              addressValue +
              '</div>' +
              '        </div>' +
              '   </div>' +
              '</div>';

            let overlay = new kakao.maps.CustomOverlay({
              content: content,
              map: map,
              position: marker.getPosition(),
            });

            kakao.maps.event.addListener(marker, 'click', function() {
              let arr = [];
              arr[0] = nowObj;
              arr[1] = [result[0].y, result[0].x];
              _this.$emit('selectedEmitObjArr', arr);
            });
            kakao.maps.event.addListener(marker, 'mouseover', function() {
              overlay.setMap(map);
            });
            kakao.maps.event.addListener(marker, 'mouseout', function() {
              overlay.setMap(null);
            });
            overlay.setMap(null);
            if (index == length - 1) map.setCenter(coords);
          } //end ok
        }); //end Search
      });
      map.relayout();
    },
  },
  created() {
    this.makeMap();
  },
};
</script>

<style>
.wrap {
  position: absolute;
  left: 0;
  bottom: 40px;
  width: 288px;
  height: 132px;
  margin-left: -144px;
  text-align: left;
  overflow: hidden;
  font-size: 12px;
  font-family: 'Malgun Gothic', dotum, '돋움', sans-serif;
  line-height: 1.5;
}
.wrap * {
  padding: 0;
  margin: 0;
}
.wrap .info {
  width: 286px;
  height: 120px;
  border-radius: 5px;
  border-bottom: 2px solid #ccc;
  border-right: 1px solid #ccc;
  overflow: hidden;
  background: #fff;
}
.wrap .info:nth-child(1) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.info .title {
  padding: 5px 0 0 10px;
  height: 30px;
  background: #eee;
  border-bottom: 1px solid #ddd;
  font-size: 18px;
  font-weight: bold;
}
.info .close {
  position: absolute;
  top: 10px;
  right: 10px;
  color: #888;
  width: 17px;
  height: 17px;
  background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/overlay_close.png');
}
.info .close:hover {
  cursor: pointer;
}
.info .body {
  position: relative;
  overflow: hidden;
  background: white;
}
.info .desc {
  position: relative;
  margin: 13px 0 0 90px;
  height: 75px;
}
.desc .ellipsis {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.desc .jibun {
  font-size: 11px;
  color: #888;
  margin-top: -2px;
}
.info .img {
  position: absolute;
  top: 6px;
  left: 5px;
  width: 73px;
  height: 71px;
  border: 1px solid #ddd;
  color: #888;
  overflow: hidden;
}
.info:after {
  content: '';
  position: absolute;
  margin-left: -12px;
  left: 50%;
  bottom: 0;
  width: 22px;
  height: 12px;
  background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png');
}
.info .link {
  color: #5085bb;
}
</style>
