package com.example.kinofan.ui.model

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

class FilmLoader(
    private val id: Long,
    private val listener: FilmLoaderListener
) {

    @RequiresApi(Build.VERSION_CODES.N)
    fun goToInternet() {

        Thread {
            val uri =
                URL("https://api.themoviedb.org/3/movie/$id?api_key=5a4d3f5b8db540f6abf906e6fdf2fdb9&language=ru-RU")

            var urlConnection: HttpsURLConnection? = null

            try {
                urlConnection = uri.openConnection() as HttpsURLConnection
                urlConnection.apply {
                    requestMethod = "GET"
                    readTimeout = 1000
                }

                val reader = BufferedReader(InputStreamReader(urlConnection.inputStream))
                val result = reader.lines().collect(Collectors.joining("\n"))

                val filmDTO: FilmDTO = Gson().fromJson(result, FilmDTO::class.java)

                listener.onLoaded(filmDTO)
            } catch (e: Exception) {
                listener.onFailed(e)
                Log.e("", "FAILED", e)
            } finally {
                urlConnection?.disconnect()
            }
        }.start()

    }


    interface FilmLoaderListener {
        fun onLoaded(filmDTO: FilmDTO)
        fun onFailed(throwable: Throwable)
    }

}