import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
    @GET("users/{username}")
    suspend fun getUser(@Path("username") username: String): GithubUser

    @GET("users/{username}/repos")
    suspend fun getUserRepos(@Path("username") username: String): List<Repository>

    companion object {
        fun create(): GithubService {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(GithubService::class.java)
        }
    }
}