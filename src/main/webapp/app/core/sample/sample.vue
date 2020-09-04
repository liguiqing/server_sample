<template>
  <div id="sample">
    <!--sample-->
    <div class="sample" v-for="sample in samples" :key="sample.id">{{ sample.title }}</div>
  </div>
</template>
<script lang="ts" src="./sample.component.ts">
</script>
<style rel="stylesheet/scss" lang="scss" scoped>
#sample {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
  grid-gap: 25px;
  align-items: center;
  justify-content: center;
  .sample {
    min-width: 100px;
    min-height: 70px;
  }
}
</style>
