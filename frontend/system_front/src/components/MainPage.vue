<template>
  <div>
    <section class="hero is-success is-fullheight">
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
    <section class = "hero is-info is-medium">
      <div class="hero-body">
       <div class="container has-text-centered">
         <h1 class="title">
           Начать использовать сервис
           <br/>
           <br/>
         </h1>
         <h2 class="subtitle has-text-centered">
          <div class="buttons">
            <b-button type="is-danger" expanded>Войти</b-button>
          </div>
         </h2>
       </div>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';

@Component
export default class MainPage extends Vue {}
</script>
