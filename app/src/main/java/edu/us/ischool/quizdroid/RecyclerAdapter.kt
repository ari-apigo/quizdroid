package edu.us.ischool.quizdroid

import android.content.Context
import android.content.Intent
import android.content.Intent.EXTRA_TEXT
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

// consulted https://www.raywenderlich.com/1560485-android-recyclerview-tutorial-with-kotlin#toc-anchor-009

class RecyclerAdapter(private val quizzes: ArrayList<Quiz>) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        val quizTopicTV: TextView = v.findViewById(R.id.quizTopic)
        val quizDescTV: TextView = v.findViewById(R.id.quizDescription)

        // initialize View.OnClickListener
        init {
            v.setOnClickListener(this)
        }

        override fun onClick(p0: View) {
            val quizTopic = quizTopicTV.text.toString()
            val intent = Intent(p0.context, TopicOverview::class.java).apply {
                putExtra(EXTRA_TEXT, quizTopic)
            }
            p0.context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // create a new view that defines the UI of a list item
        val item = LayoutInflater.from(parent.context)
                    .inflate(R.layout.quiz_list_item, parent, false)

        return ViewHolder(item)
    }

    override fun getItemCount() = quizzes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // get element from dataset at this position
        //      and replace view contents with that element's data
        holder.quizTopicTV.text = quizzes[position].topic
        holder.quizDescTV.text = quizzes[position].desc
    }

}