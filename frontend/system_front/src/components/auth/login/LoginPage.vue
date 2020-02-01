<template>
    <div>
        <section class = "hero is-primary is-fullheight">
            <div class = "hero-body">
                <div class = "container has-text-centered">
                    <UserForm />
                    <h2 class = "subtitle">
                        У вас ещё нет аккаунта?
                    </h2>
                    <div class = "buttons">
                        <b-button type="is-warning">Зарегистрироваться</b-button>
                    </div>
                </div>
            </div>
        </section>
    </div>
</template>

<script lang = "ts">

import UserForm from '@/components/common/form/user_form';

export default {
  components: {
    UserForm,
  },
};
</script>
