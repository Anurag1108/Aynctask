package com.example.customasynctask.framework.asynctask

import com.example.customasynctask.framework.executors.ExecutorProvider
import java.util.concurrent.Callable
import java.util.concurrent.Future

abstract class MyAsyncTask<ResultType> {


    var url: String? = null

    // Functions to be implemented by the user.
    abstract fun doInBackground(): ResultType
    abstract fun onPreExecute()
    abstract fun onPostExecute(valueFromDoInBackground: ResultType)

    fun execute(url: String) {
        this.url = url
        onPreExecute()

        ExecutorProvider.backgroundTaskExecutor?.let {
            val future: Future<ResultType> =
                it.submit(Callable<ResultType>
                {
                    doInBackground()
                })
            val valueFromDoInBackground = future.get()

            onPostExecute(valueFromDoInBackground)
        }
    }

}