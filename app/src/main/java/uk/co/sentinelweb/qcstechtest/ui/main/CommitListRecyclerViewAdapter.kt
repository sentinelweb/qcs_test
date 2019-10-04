package uk.co.sentinelweb.qcstechtest.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.commit_list_item.view.*
import uk.co.sentinelweb.qcstechtest.R


class CommitListRecyclerViewAdapter(
        initialValues: LiveData<List<CommitModel>>,
        owner: LifecycleOwner
    ) : RecyclerView.Adapter<CommitListRecyclerViewAdapter.ViewHolder>() {

        internal var items = mutableListOf<CommitModel>()


        private val onClickListener: View.OnClickListener

        init {
            onClickListener = View.OnClickListener { v ->
                val item = v.tag as CommitModel
                Toast.makeText(v.context, item.message, Toast.LENGTH_SHORT).show()
            }
            initialValues.observe(owner, Observer { modelList ->
                items.clear()
                items.addAll(modelList)
                notifyDataSetChanged()
            })
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.commit_list_item, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = items[position]
            holder.messageView.text = item.message
            holder.nameView.text = item.name
            holder.dateView.text = item.dateString
            Picasso.get().load(item.imageUrl).into(holder.imageView)

            with(holder.itemView) {
                tag = item
                setOnClickListener(onClickListener)
            }
        }

        override fun getItemCount() = items.size

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val messageView: TextView = view.commit_item_message
            val nameView: TextView = view.commit_item_name
            val dateView: TextView = view.commit_item_date
            val imageView: ImageView = view.commit_item_image
        }
    }