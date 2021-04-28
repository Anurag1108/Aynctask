/*
 * Copyright (c) 2019 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.example.customasynctask.ui.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.customasynctask.R
import com.example.customasynctask.data.model.Movie
import com.example.customasynctask.ui.extensions.inflate
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recyclerview_item_row.view.*

class MovieAdapter(private val movies: ArrayList<Movie>) :
    RecyclerView.Adapter<MovieAdapter.MovieHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val inflatedView = parent.inflate(R.layout.recyclerview_item_row, false)
        return MovieHolder(inflatedView)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val itemMovie = movies[position]
        holder.bindMovie(itemMovie)
    }

    class MovieHolder(private val view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener {

        private var movie: Movie? = null

        init {
            view.setOnClickListener(this)
        }


        fun bindMovie(movie: Movie) {
            this.movie = movie

            Picasso.get().load(movie.image).into(view.imageView)

            view.textViewTitle.text = movie.title
            view.textViewLanguage.text = movie.language
            view.textViewRating.text = movie.rating
            view.textViewType.text = movie.type
            view.textViewVotesCount.text = movie.vote_count.toString()
            view.textViewLikePercent.text = movie.like_percent.toString()
        }

        override fun onClick(p0: View?) {

            Toast.makeText(itemView.context, "Clicked Item:$adapterPosition", Toast.LENGTH_SHORT)
                .show()

        }

    }

}