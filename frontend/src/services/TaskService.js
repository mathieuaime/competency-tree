export default {
  async save (roadmapId, skillId, task) {
    console.log(task)
    fetch(`/api/v1/roadmaps/${roadmapId}/skills/${skillId}/tasks`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(task)
    })
  },
  async check (taskId) {
    fetch(`/api/v1/me/tasks/${taskId}/check`, {
      method: 'PUT'
    })
  }
}
