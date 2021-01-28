export default {
  const baseUrl = process.env.API_URL || 'localhost:8080/api/v1';

  async save (roadmapId, skillId, task) {
    console.log(task)
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
