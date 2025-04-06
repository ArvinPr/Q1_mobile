data class GithubUser(
    val login: String,
    val name: String?,
    val followers: Int,
    val following: Int,
    val created_at: String,
    val public_repos: Int
) {
    fun showInfo(): String {
        return """
            |Username: $login
            |Name: ${name ?: "N/A"}
            |Followers: $followers
            |Following: $following
            |Created at: ${created_at.substring(0, 10)}
            |Public repos: $public_repos
        """.trimMargin()
    }
}