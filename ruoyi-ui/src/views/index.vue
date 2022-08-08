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

//定义提示框
        var dialog = new TooltipDialog({
          id: "tooltipDialog",
          style: "position: absolute; width: 250px; font: normal normal normal 10pt Helvetica;z-index:100"
        });
        dialog.startup();
        var highlightSymbol = new SimpleFillSymbol(//红边画笔
          SimpleFillSymbol.STYLE_SOLID,
          new SimpleLineSymbol(
            SimpleLineSymbol.STYLE_SOLID,
            new Color([255, 0, 0]), 3
          ),
          new Color([125, 125, 125, 0.35])
        );

//打开提示框
        function showtip(template) {
          return function (evt) {
            var content = esriLang.substitute(evt.graphic.attributes, template);
            var highlightGraphic = new Graphic(evt.graphic.geometry, highlightSymbol);
            map.graphics.add(highlightGraphic);//红色描边

            dialog.setContent(content);

            domStyle.set(dialog.domNode, "opacity", 0.85);
            dijitPopup.open({
              popup: dialog,
              x: evt.pageX,
              y: evt.pageY
            });
          }
        }
//关闭提示框
        function closeDialog() {
          map.graphics.clear();//擦去红色描边
          dijitPopup.close(dialog);
        }

        function buildFeatureLayer(param) {
          var fl = new FeatureLayer(param.url, {
            id: param.id,
            mode: FeatureLayer.MODE_ONDEMAND,
            definitionExpression: param.expression,
            infoTemplate: param.template,
            outFields: param.fields,
            visible: false
          });
          map.addLayer(fl);
          if (param.template != null) {
            fl.on("mouse-over", showtip(param.template));//mouseover时，打开提示框
          }
          return fl;
        }
        map.on("load", function () {
          map.graphics.enableMouseEvents();
          map.graphics.on("mouse-out", closeDialog);//mouseout时，关闭提示框
          map.on("mouse-down", closeDialog);
        });
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
