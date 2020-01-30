<template>
  <section class="hero is-success is-medium">
   <div class="hero-head">
     <nav class="navbar">
       <div class="container">
         <div class="navbar-brand">
          <a class="navbar-item">
            <img src="../assets/logo.png" alt="Logo">
          </a>
        </div>
         <div id="navbarMenuHeroA" class="navbar-menu">
           <div class="navbar-end">
             <a class="navbar-item is-active">
               Главная страница
             </a>
             <a class="navbar-item">
               Личный кабинет
             </a>
           </div>
         </div>
       </div>
     </nav>
   </div>

   <div class="hero-body">
     <div class="container has-text-centered">
       <h1 class="title">
         Studeeper
       </h1>
       <h2 class="subtitle">
         Сервис для поиска места в жизни
       </h2>
     </div>
   </div>
  </section>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';

@Component
export default class MainPage extends Vue {}
</script>
