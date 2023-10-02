package com.example.bookshelfapp.ui.adapter

import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.bookshelfapp.R
import com.example.bookshelfapp.data.local.BookEntity
import com.example.bookshelfapp.databinding.ItemBookBinding
import com.example.bookshelfapp.util.GlideLoader

class BookListAdapter(
    var data: List<BookEntity> = arrayListOf(),
    val onRootItemClicked : (BookEntity) -> Unit,
    val addToFav :(BookEntity) -> Unit

) : RecyclerView.Adapter<BookListAdapter.BookViewHolder>() {


    class BookViewHolder(val itemBinding: ItemBookBinding) :
        RecyclerView.ViewHolder(itemBinding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder(
            ItemBookBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {

        with(holder) {
            val result= data[position]
            GlideLoader(itemBinding.imgBook.context).loadPic(result.image.toString(),itemBinding.imgBook)
            itemBinding.txtBookName.text = result.title
            itemBinding.txtHits.text = result.hits.toString()

            itemBinding.root.setOnClickListener {
                onRootItemClicked(result)
            }
            itemBinding.imgFav.setOnClickListener {
                addToFav(result)
            }
            if(result.isFavourite){
                itemBinding.imgFav.setColorFilter(ContextCompat.getColor(itemBinding.imgFav.context, R.color.yellow), PorterDuff.Mode.SRC_IN)
            }else{
                itemBinding.imgFav.colorFilter = null
            }
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }
    fun updateData(data: List<BookEntity>){
        this.data = data
        notifyDataSetChanged()
    }


}
