export default {
  async findAll () {
    return fetch('/api/v1/roadmaps')
      .then(res => res.json())
      .then(items => items)
  },
  async findByName (name, isAdmin) {
    let url = isAdmin
      ? `/api/v1/roadmaps/${name}`
      : `/api/v1/me/roadmaps/${name}`

    return fetch(url)
      .then(res => res.json())
      .then(items => items)
  }
}
