<template>
  <div id="home">
    <b-container>
      <b-row>
        <b-col><h1>Users</h1></b-col>
      </b-row>
      <b-row>
        <b-col>
          <b-card-group deck>
            <b-card
              v-for="(user, index) in users"
              :key="index"
              :title="user.nickName"
              :sub-title="user.email"
            >
              <b-card-text>
                <p>
                  <strong>Full Name: </strong>
                  {{ `${user.firstName} ${user.lastName}` }}
                </p>
                <p><strong>Age: </strong> {{ user.age }}</p>
              </b-card-text>
            </b-card>
          </b-card-group>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import { mapState } from 'vuex'

export default {
  async fetch({ store }) {
    await store.dispatch('users/fetchAll')
  },
  computed: {
    ...mapState({
      users: (state) => state.users.all,
    }),
  },
}
</script>
