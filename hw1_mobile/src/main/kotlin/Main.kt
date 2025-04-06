fun main() {
    val service = GithubService.create()
    val storage = UserStorage()
    val consoleUI = ConsoleUI(service, storage)

    consoleUI.start()
}