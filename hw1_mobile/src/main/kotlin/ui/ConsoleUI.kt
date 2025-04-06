import kotlinx.coroutines.runBlocking

class ConsoleUI(
    private val githubService: GithubService,
    private val storage: UserStorage
) {
    fun start() {
        while (true) {
            printMenu()
            when (readlnOrNull()) {
                "1" -> fetchUser()
                "2" -> showUsers()
                "3" -> searchUser()
                "4" -> searchRepo()
                "5" -> return
                else -> println("Invalid choice!")
            }
        }
    }

    private fun printMenu() {
        println("\nGitHub User Info")
        println("1. Get user by username")
        println("2. Show all users in storage")
        println("3. Search users")
        println("4. Search repositories")
        println("5. Exit")
        print("Your choice: ")
    }

    private fun fetchUser() = runBlocking {
        print("Enter GitHub username: ")
        val username = readlnOrNull() ?: return@runBlocking

        try {
            val user = githubService.getUser(username)
            val userRepos = githubService.getUserRepos(username)

            storage.saveUser(user)
            storage.saveRepos(username, userRepos)

            println("\nUser info:")
            println(user.showInfo())

            println("\nTop 3 repositories:")
            userRepos.take(3).forEach { repo ->
                println("- ${repo.showInfo()}")
            }
        } catch (e: Exception) {
            println("Error: ${e.message}")
        }
    }

    private fun showUsers() {
        val users = storage.getAllUsers()
        if (users.isEmpty()) {
            println("No users in storage")
            return
        }

        println("\nStored users:")
        users.forEachIndexed { index, user ->
            println("${index + 1}. ${user.login}")
        }
    }

    private fun searchUser() {
        print("Enter search query: ")
        val query = readlnOrNull()?.lowercase() ?: return

        val results = storage.getAllUsers()
            .filter { it.login.lowercase().contains(query) }

        if (results.isEmpty()) {
            println("No users found")
            return
        }

        println("\nSearch results:")
        results.forEach { user ->
            println(user.showInfo())
            println()
        }
    }

    private fun searchRepo() {
        print("Enter repository name: ")
        val query = readlnOrNull()?.lowercase() ?: return

        val results = storage.getAllUsers().flatMap { user ->
            (storage.getRepos(user.login) ?: emptyList())
                .filter { it.name.lowercase().contains(query) }
                .map { user to it }
        }

        if (results.isEmpty()) {
            println("No repositories found")
            return
        }

        println("\nSearch results:")
        results.forEach { (user, repo) ->
            println("${user.login}/${repo.name}")
            println("Language: ${repo.language ?: "N/A"}")
            println("Stars: ${repo.stargazers_count}\n")
        }
    }
}