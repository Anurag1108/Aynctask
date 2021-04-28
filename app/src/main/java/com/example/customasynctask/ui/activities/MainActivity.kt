package com.example.customasynctask.ui.activities

import android.content.Context
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.customasynctask.R
import com.example.customasynctask.data.model.Movie
import com.example.customasynctask.framework.asynctask.MyAsyncTask
import com.example.customasynctask.framework.asynctask.MyAsyncTaskImpl
import com.example.customasynctask.framework.callbacks.AsyncResponse
import com.example.customasynctask.ui.adapters.MovieAdapter
import com.example.customasynctask.util.Constant
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException
import java.util.*


class MainActivity : AppCompatActivity(), AsyncResponse {


    private lateinit var displayData: Button
    private lateinit var context: Context
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var movieList: ArrayList<Movie> = ArrayList()
    private lateinit var adapter: MovieAdapter


    private lateinit var asyncTask: MyAsyncTask<*>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        context = this

        asyncTask = MyAsyncTaskImpl(this)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerview.layoutManager = linearLayoutManager


        displayData = findViewById(R.id.myAsyncTaskButton)


        displayData.setOnClickListener {
            asyncTask.execute(Constant.MOVIES_API_URL)
        }


    }


    override fun processFinish(output: String?) {
        try {

            val gson = Gson()
            val mData: ArrayList<Movie> =
                gson.fromJson(output, Array<Movie>::class.java).toList() as ArrayList<Movie>

            movieList.clear()
            movieList.addAll(mData)
            adapter = MovieAdapter(movieList)
            recyclerview.adapter = adapter


        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }


}

