const baseUrl = process.env.API_URL || ''

export default {

  async findAll () {
    let url = `${baseUrl}/api/v1/roadmaps`
    console.debug(`GET ${url}`)

    return fetch(url)
      .then(res => res.json())
      .then(items => items)
      .catch(error => {
        console.error('There was an error!', error)
      })
  },
  async findByName (name, isAdmin) {
    let url = isAdmin ? `/api/v1/roadmaps/${name}` : `/api/v1/me/roadmaps/${name}`
    console.debug(`GET ${url}`)

    return fetch(`${baseUrl}${url}`)
      .then(res => res.json())
      .then(items => items)
      .catch(error => {
        console.error('There was an error!', error)
      })
  }
}
