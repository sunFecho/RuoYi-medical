<template>
  <div id = "sceneview">

  </div>
</template>

<script>
import {loadModules} from 'esri-loader'
export default {
  name: "Index",
  // data() {
  //
  // },
  mounted(){
    this._createSceneView();
  },
  methods: {
   //创建场景
    _createSceneView() {
      let options={
        url:'https://js.arcgis.com/4.24/',
        css:'https://js.arcgis.com/4.24/esri/themes/light/main.css'
      };
      loadModules(["esri/Map", "esri/views/MapView"],options).then(([Map,SceneView])=>{
        let map = new Map({
          basemap: "streets",
          ground: "world-elevation"
        });
        let view = new SceneView({
          container: "sceneview", // Reference to the DOM node that will contain the view
          map: map // References the map object created in step 3
        });
        console.log(view);
      })
    }

  },
};
</script>

<style>
#sceneview{
  position: absolute;
  width: 100%;
  height: 100%;
}
</style>

