<template>
  <div id="home">
    <!--sample-->
    <div v-for="account in accounts" :key="account.id">{{ account.name }}</div>
  </div>
</template>
<script lang="ts" src="./home.component.ts">
</script>
<style rel="stylesheet/scss" lang="scss" scoped>
</style>
