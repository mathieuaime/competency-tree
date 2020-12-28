<template>
<div class="note" v-if="currentTask">
    <div class="note-header">
        <div class="title">
            <span class="icon-sharp-settings-24px"></span>
            <h2>{{ skill.name }}</h2>
        </div>
    </div>
    <transition name="slide-fade" mode="out-in">
        <div class="note-body">
            <div class="note-container">∑
                NAME : <input v-model="currentTask.name"></input>
                DESCRIPTION : <input v-model="currentTask.description"></input>
                REQUIRED : <input type="checkbox" v-model="currentTask.required"></input>
            </div>
        </div>
    </transition>
    <div class="note-footer">
        <button class="btn" @click.stop="showTask(undefined)">BACK</button>
        <button class="btn isActive" @click.stop="saveTask()">SAVE</button>
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
            <div class="note-container">
                <div class="title">
                    <span class="icon-sharp-settings-24px"></span>
                    <p>TASKS</p>
                </div>
                <button class="btn" v-for="task in skill.tasks" :key="task.id" @click.stop="showTask(task)">{{ task.name }}</button>
            </div>
        </div>
    </transition>
    <div class="note-footer">
        <button class="btn" @click.stop="addTask()">Add Task</button>
    </div>
</div>
</template>

<script>
import TaskService from '../../../services/TaskService'

export default {
  props: ['skill', 'primaryColor'],
  name: 'NoteAdmin',
  data () {
    return {
      currentTask: undefined
    }
  },
  mounted () {
    this.currentTask = undefined
  },
  methods: {
    addTask () {
      this.showTask({
        id: undefined,
        name: '',
        description: '',
        required: false,
        category: this.skill.category
      })
    },
    showTask (data) {
      this.currentTask = data
    },
    saveTask () {
      TaskService.save(this.roadmap.id, this.skill.id, this.currentTask)

      if (this.skill.tasks.indexOf(this.currentTask) === -1) {
        this.skill.tasks.push(this.currentTask)
      }

      this.currentTask = undefined
    }
  }
}
</script>

<style lang="scss" src="../style.scss" scoped></style>
