const baseUrl = process.env.API_URL || ''

export default {

  async save (roadmapId, skillId, task) {
    let url = `${baseUrl}/api/v1/roadmaps/${roadmapId}/skills/${skillId}/tasks`
    console.debug(`PUT ${url}`)

    fetch(url, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(task)
    })
      .catch(error => {
        console.error('There was an error!', error)
      })
  },
  async check (taskId) {
    let url = `${baseUrl}/api/v1/me/tasks/${taskId}/check`
    console.debug(`PUT ${url}`)

    fetch(url, {
      method: 'PUT'
    })
      .catch(error => {
        console.error('There was an error!', error)
      })
  }
}
