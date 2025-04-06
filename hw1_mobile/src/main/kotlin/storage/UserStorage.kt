class UserStorage {
    private val users = mutableMapOf<String, GithubUser>()
    private val repos = mutableMapOf<String, List<Repository>>()

    fun saveUser(user: GithubUser) {
        users[user.login] = user
    }

    fun saveRepos(username: String, reposList: List<Repository>) {
        repos[username] = reposList
    }

    fun getUser(username: String): GithubUser? = users[username]

    fun getRepos(username: String): List<Repository>? = repos[username]

    fun getAllUsers(): List<GithubUser> = users.values.toList()
}