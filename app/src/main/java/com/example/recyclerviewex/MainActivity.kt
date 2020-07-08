package com.example.recyclerviewex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var blogRecyclerAdapter: BlogRecyclerAdapter
    val data=DataSource.createDataSet()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        addDataSet()
    }

    private fun addDataSet() {
        blogRecyclerAdapter.submitList(data)
    }

    private fun initRecyclerView() {
        recycler_view.apply {
            blogRecyclerAdapter= BlogRecyclerAdapter()
            layoutManager=LinearLayoutManager(this@MainActivity)
            val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
            itemTouchHelper.attachToRecyclerView(this)
            adapter=blogRecyclerAdapter
        }
    }

    val itemTouchHelperCallback=
        object :
        ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT or ItemTouchHelper.UP or ItemTouchHelper.DOWN){
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                                target: RecyclerView.ViewHolder): Boolean {
                if (viewHolder.itemViewType != target.itemViewType)
                    return false
                val fromPosition = viewHolder.adapterPosition
                val toPosition = target.adapterPosition
                val item = data.removeAt(fromPosition)
                data.add(toPosition, item)
                blogRecyclerAdapter.notifyItemMoved(fromPosition, toPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                data.removeAt(viewHolder.adapterPosition)
                blogRecyclerAdapter.notifyDataSetChanged()
            }


        }




}