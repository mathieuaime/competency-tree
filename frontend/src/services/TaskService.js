const baseUrl = process.env.API_URL || ''

export default {

  async save (roadmapId, skillId, task) {
    fetch(`${baseUrl}/api/v1/roadmaps/${roadmapId}/skills/${skillId}/tasks`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(task)
    })
  },
  async check (taskId) {
    fetch(`${baseUrl}/api/v1/me/tasks/${taskId}/check`, {
      method: 'PUT'
    })
  }
}
