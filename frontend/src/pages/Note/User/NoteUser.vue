<template>
  <div class="note" v-if="currentTask">
      <div class="note-header">
          <div class="title">
              <span class="icon-sharp-settings-24px" v-if="currentTask.required"></span>
              <span class="icon-sharp-filter_tilt_shift-24px" v-else></span>
              <h2 :key="currentTask.name">{{ currentTask.name }}</h2>
          </div>
      </div>
      <transition name="slide-fade" mode="out-in">
          <div key="currentTask.id" class="note-body">
              <div class="note-container">
                  {{ currentTask.description }}
              </div>
          </div>
      </transition>
      <div class="note-footer">
          <button class="btn" @click.stop="showTask(undefined)">BACK</button>
          <button class="btn isActive" @click.stop="checkTask()" v-if="!currentTask.done">UNLOCK</button>
      </div>
  </div>
  <div class="note" v-else-if="skill.name != undefined">
      <div class="note-header">
          <div class="title">
              <span :class="'icon-sharp-'+ skill.icon + '-24px'"></span>
              <h2>{{skill.name}}</h2>
          </div>
      </div>
      <transition name="slide-fade" mode="out-in">
          <div class="note-body">
              <div class="note-container" v-if="requireds.length > 0">
                  <div class="title">
                      <span class="icon-sharp-settings-24px"></span>
                      <p>REQUIRED</p>
                  </div>
                  <button class="btn" v-for="task in requireds" :class="{isActive: task.done}" :key="task.id" @click.stop="showTask(task)">{{ task.name }}</button>
              </div>
              <div class="note-container" v-if="optionals.length > 0">
                  <div class="title">
                      <span class="icon-sharp-filter_tilt_shift-24px"></span>
                      <p>OPTIONAL</p>
                  </div>
                  <button class="btn" v-for="task in optionals" :class="{isActive: task.done}" :key="task.id" @click.stop="showTask(task)">{{ task.name }}</button>
              </div>
          </div>
      </transition>
      <div class="note-footer">
          <div class="progress">
              <vm-progress :percentage="skillProgression" :text-inside="true" :stroke-width="18" :strokeColor="primaryColor"></vm-progress>
          </div>
      </div>
  </div>
</template>

<script>
import TaskService from '../../../services/TaskService'

export default {
  props: ['skill', 'primaryColor'],
  name: 'NoteUser',
  data () {
    return {
      currentTask: undefined
    }
  },
  computed: {
    requireds () {
      return this.skill.tasks.filter(item => item.required)
    },
    optionals () {
      return this.skill.tasks.filter(item => !item.required)
    },
    skillProgression () {
      return Math.round((this.skill.tasks.filter(item => item.done).length * 100) / this.skill.tasks.length)
    }
  },
  methods: {
    showTask (data) {
      this.currentTask = data
    },
    checkTask () {
      // maybe in validation status
      TaskService.check(this.currentTask.id)

      this.skill.tasks.find(i => i.id === this.currentTask.id).done = true

      if (this.currentTask.required) {
        this.skill.done = this.skill.tasks.filter(i => i.required).every(i => i.done)
      }

      this.currentTask = undefined
    }
  }
}
</script>

<style lang="scss" src="../style.scss" scoped></style>
