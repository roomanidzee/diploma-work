<template>
    <div>
        <section class = "hero has-background-info is-fullheight">
            <div class = "hero-body">
                <div class = "container has-text-centered">
                    <registration-form />
                </div>
            </div>
        </section>
    </div>
</template>

<script lang = "ts">

import RegistrationForm from '@/components/auth/register/registration_form';

export default {
  components: {
    'registration-form': RegistrationForm,
  },
};
</script>
