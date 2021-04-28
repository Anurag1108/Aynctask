package com.example.customasynctask.framework.asynctask

import android.util.Log
import com.example.customasynctask.framework.callbacks.AsyncResponse
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.Reader
import java.net.HttpURLConnection
import java.net.URL


class MyAsyncTaskImpl(
    private val asyncResponse: AsyncResponse
) : MyAsyncTask<String>() {


    val LOG_TAG = "MyAsyncTask_Logs"

    override fun doInBackground(): String {
        Log.d(LOG_TAG, "doInBackgroundCalledOn: " + Thread.currentThread().name)
        var returnString = "ERRORSTRING"
        val url = URL(url)
        val urlConnection = url.openConnection() as HttpURLConnection
        try {
            val inputStream = BufferedInputStream(urlConnection.inputStream)

            val bufferedReader = BufferedReader(InputStreamReader(inputStream) as Reader?)
            bufferedReader.readLine().also { returnString = it }

        } finally {
            urlConnection.disconnect()
        }
        return returnString
    }

    override fun onPreExecute() {
        Log.d(LOG_TAG, "onPreExecuteCalledOn: " + Thread.currentThread().name)
    }


    override fun onPostExecute(valueFromDoInBackground: String) {
        Log.d(LOG_TAG, "onPostExecuteCalledOn: " + Thread.currentThread().name)
        Log.d(LOG_TAG, "taskOutput: $valueFromDoInBackground")
        asyncResponse.processFinish(valueFromDoInBackground)
    }


}