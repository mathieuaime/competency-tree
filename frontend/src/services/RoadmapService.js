export default {
  async findAll () {
    return fetch('http://api:9090/api/v1/roadmaps')
      .then(res => res.json())
      .then(items => items)
  },
  async findByName (name, isAdmin) {
    let url = isAdmin
      ? `http://api:9090/api/v1/admin/roadmaps/${name}`
      : `http://api:9090/api/v1/me/roadmaps/${name}`

    return fetch(url)
      .then(res => res.json())
      .then(items => items)
  }
}
