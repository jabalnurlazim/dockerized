<template>
  <div id="blog-id">
    <b-container>
      <b-row>
        <b-col class="text-center">
          <h1>{{ blog.title }}</h1>
        </b-col>
      </b-row>
      <b-row>
        <b-col>{{ blog.content }}</b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import { mapState } from 'vuex'

export default {
  async fetch({ store, params }) {
    await store.dispatch('blog/getBlog', params.id)
  },
  computed: {
    ...mapState({
      blog: (state) => state.blog.blog,
    }),
  },
}
</script>
