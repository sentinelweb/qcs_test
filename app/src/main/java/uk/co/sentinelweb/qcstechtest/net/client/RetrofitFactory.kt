package uk.co.sentinelweb.wtestapp.net.client

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uk.co.sentinelweb.qcstechtest.net.service.RepoService


class RetrofitFactory {
    //https://api.github.com/repos/JetBrains/kotlin/commits
    companion object {
        internal const val BASE_URL = "https://api.github.com/repos/"
    }

    fun createClient(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    fun createRepoService(retrofit: Retrofit): RepoService =
        retrofit.create<RepoService>(RepoService::class.java)

}