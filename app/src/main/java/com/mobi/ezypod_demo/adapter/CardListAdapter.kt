package com.mobi.ezypod_demo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.mobi.ezypod_demo.R
import kotlin.collections.ArrayList

class CardListAdapter : RecyclerView.Adapter<CardListAdapter.ViewHolder> {


    var context: Context? = null
    var dataList: List<Int> = ArrayList()

    constructor(context: Context,categoryList: ArrayList<Int>){
        this.dataList = categoryList
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.list_cards, parent, false)
        context = parent.context
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.productNameTxt.text = "Product Name $position"

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

//        val productNameTxt: AppCompatTextView = itemView.product_name_txt
//        val categoryImg: AppCompatImageView = itemView.new_arrival_product_image
    }
}