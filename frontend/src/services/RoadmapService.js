const baseUrl = process.env.API_URL || 'localhost:8080/api/v1'

export default {

  async findAll () {
    return fetch(`${baseUrl}/roadmaps`)
      .then(res => res.json())
      .then(items => items)
  },
  async findByName (name, isAdmin) {
    let url = isAdmin ? `/roadmaps/${name}` : `/me/roadmaps/${name}`

    return fetch(`${baseUrl}${url}`)
      .then(res => res.json())
      .then(items => items)
  }
}
