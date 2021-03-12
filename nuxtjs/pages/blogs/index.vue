<template>
  <div id="blogs">
    <b-container>
      <b-row>
        <b-col class="h1">
          Blogs
          <b-icon-plus-circle-fill
            class="cursor-pointer"
            @click="$bvModal.show('blog-form-modal')"
          />
        </b-col>
      </b-row>
      <b-row>
        <b-col v-for="(blog, index) in blogs" :key="index" cols="6">
          <b-card>
            <b-card-title>
              {{ blog.title }}
              <b-icon-pencil-square
                class="cursor-pointer"
                @click="
                  $bvModal.show('blog-form-modal')
                  blogForm.title = blog.title
                  blogForm.content = blog.content
                  blogUpdate = blog.id
                "
              />
              <b-icon-trash-fill
                class="cursor-pointer"
                @click="deleteBlog(blog.id)"
              />
              <nuxt-link :to="{ name: 'blogs-id', params: { id: blog.id } }">
                <b-icon-eye-fill />
              </nuxt-link>
            </b-card-title>
            <b-card-text>
              {{ blog.content }}
            </b-card-text>
          </b-card>
        </b-col>
      </b-row>
    </b-container>
    <b-modal
      id="blog-form-modal"
      title="Blog Submission"
      @hidden="resetForm"
      @ok="handleForm"
    >
      <form ref="form" @submit.stop.prevent="submitBlog">
        <b-form-group
          label="Title"
          label-for="title"
          invalid-feedback="Title is required"
        >
          <b-form-input
            id="name-input"
            v-model="blogForm.title"
            :state="blogForm.title.length > 0"
            required
          />
        </b-form-group>
        <b-form-group
          label="Content"
          label-for="content"
          invalid-feedback="Content is required"
        >
          <b-form-textarea
            id="name-input"
            v-model="blogForm.content"
            :state="blogForm.content.length > 0"
            required
          />
        </b-form-group>
      </form>
    </b-modal>
  </div>
</template>

<script>
import {
  BIconPlusCircleFill,
  BIconPencilSquare,
  BIconTrashFill,
  BIconEyeFill,
} from 'bootstrap-vue'
import { mapState } from 'vuex'

export default {
  components: {
    BIconPlusCircleFill,
    BIconPencilSquare,
    BIconTrashFill,
    BIconEyeFill,
  },
  async fetch({ store }) {
    await store.dispatch('blog/getAllBlogs')
  },
  data() {
    return {
      blogForm: { title: '', content: '' },
      blogUpdate: '',
    }
  },
  computed: {
    ...mapState({
      blogs: (state) => state.blog.blogs,
    }),
  },
  methods: {
    resetForm() {
      this.blogForm = { title: '', content: '' }
      this.blogUpdate = ''
    },
    handleForm(bvModalEvt) {
      bvModalEvt.preventDefault()
      if (!this.$refs.form.checkValidity()) {
        this.$bvToast.toast('Blog not valid', {
          toaster: 'b-toaster-top-center',
          solid: true,
          noCloseButton: true,
          variant: 'danger',
        })
        return
      }
      this.submitBlog()
    },
    async submitBlog() {
      if (this.blogUpdate) {
        await this.$store.dispatch('blog/updateBlog', {
          body: this.blogForm,
          id: this.blogUpdate,
        })
      } else {
        await this.$store.dispatch('blog/createBlog', this.blogForm)
      }
      this.$nextTick(() => {
        this.$bvModal.hide('blog-form-modal')
      })
    },
    async deleteBlog(id) {
      await this.$store.dispatch('blog/deleteBlog', id)
    },
  },
}
</script>

<style lang="scss" scoped>
.cursor-pointer {
  cursor: pointer;
}
</style>
