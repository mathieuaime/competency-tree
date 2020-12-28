<template>
  <NoteAdmin
    :skill="skill"
    :primaryColor="primaryColor"
    v-if="admin"
  ></NoteAdmin>
  <NoteUser :skill="skill" :primaryColor="primaryColor" v-else></NoteUser>
</template>

<script>
import NoteUser from './User/NoteUser'
import NoteAdmin from './Admin/NoteAdmin'

export default {
  props: ['admin', 'primaryColor', 'skill'],
  components: {
    NoteUser,
    NoteAdmin
  },
  name: 'Note'
}
</script>
