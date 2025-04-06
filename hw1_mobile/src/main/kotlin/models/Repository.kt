data class Repository(
    val name: String,
    val language: String?,
    val stargazers_count: Int
) {
    fun showInfo(): String {
        return "$name (${language ?: "N/A"}) - ★$stargazers_count"
    }
}