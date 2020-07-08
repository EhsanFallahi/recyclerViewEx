package com.example.recyclerviewex

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.recyclerviewex.databinding.LayoutBlogListItemBinding
import com.example.recyclerviewex.models.BlogPost

class BlogRecyclerAdapter :RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private var items:List<BlogPost> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding=LayoutBlogListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BlogViewHolder(binding)
    }

    override fun getItemCount()=items.size

    fun submitList(blogList: List<BlogPost>){
        items=blogList
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is BlogViewHolder ->{
                holder.bind(items.get(position))
            }
        }
    }


    class BlogViewHolder(val binding: LayoutBlogListItemBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(blogPost: BlogPost){
            binding.blogTitle.setText(blogPost.title)
            binding.blogAuthor.setText(blogPost.username)

            val requestOptions=RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(blogPost.image)
                .into(binding.blogImage)
        }
    }

}