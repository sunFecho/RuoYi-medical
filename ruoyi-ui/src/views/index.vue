<template>
  <div>
    <div id="map_contentView" ></div>
  </div>
</template>

<script>
import { loadModules } from "esri-loader";
export default {
  mounted() {
    // lazy load the required ArcGIS API for JavaScript modules and CSS
    loadModules(["esri/layers/WebTileLayer", "esri/Map", "esri/views/SceneView"],
      {css: "https://js.arcgis.com/4.24/esri/themes/light/main.css" ,
      url: "https://js.arcgis.com/4.24/"})
      .then(([WebTileLayer, Map, SceneView]) => {
        var tiandituBaseUrl = "http://{subDomain}.tianditu.gov.cn"; //天地图服务地址
        var token = "32ee8f404010489225e6a96013a8d1e3"; //天地图管网申请token
        const map = new Map({
          basemap: 'topo-vector',
          ground: "world-elevation",
          logo: false,
        });
        this.view = new SceneView({
          //这个是让地图显示在id为map_contentView的标签上，是id叫他不是其他的
          container: 'map_contentView',
          map: map,
          center: [120.479, 31.899],
          zoom: 12,
        });
        this.view.ui.remove('attribution');
      });
  },


};

</script>

<style>
#map_contentView {
  position: absolute;
  width: 100%;
  height: 100%;
}
</style>
