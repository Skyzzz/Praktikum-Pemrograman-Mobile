package com.android.finalfantasy

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MainViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView){
    fun bindData(tripleTriadData: TripleTriadData)
    {
        val name = itemView.findViewById<TextView>(R.id.nameTriad)
        val image = itemView.findViewById<ImageView>(R.id.gambar)
        val desc = itemView.findViewById<TextView>(R.id.description)
        val owned = itemView.findViewById<TextView>(R.id.owned)

        name.text = tripleTriadData.name
        Glide.with(itemView)
            .load(tripleTriadData.image)
            .placeholder(R.drawable.img_placeholder)
            .error(R.drawable.img_placeholder)
            .into(image)
        desc.text = tripleTriadData.patch
        owned.text = tripleTriadData.owned

        itemView.setOnClickListener {
            val context = itemView.context

            val intent = Intent(context, TripleTriadDetail::class.java).apply {
                putExtra("name", tripleTriadData.name)
                putExtra("image", tripleTriadData.image)
                putExtra("patch", tripleTriadData.patch)
                putExtra("owned", tripleTriadData.owned)
                putExtra("desc", tripleTriadData.description)
            }
            context.startActivity(intent)
        }
    }
}