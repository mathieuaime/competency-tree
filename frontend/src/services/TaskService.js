const baseUrl = process.env.API_URL || ''

export default {

  async save (roadmapId, skillId, task) {
    fetch(`${baseUrl}/roadmaps/${roadmapId}/skills/${skillId}/tasks`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(task)
    })
  },
  async check (taskId) {
    fetch(`${baseUrl}/me/tasks/${taskId}/check`, {
      method: 'PUT'
    })
  }
}
