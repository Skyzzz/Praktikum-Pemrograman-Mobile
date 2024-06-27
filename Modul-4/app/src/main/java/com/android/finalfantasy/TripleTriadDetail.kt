package com.android.finalfantasy

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class TripleTriadDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_item)

        val nameTriad = findViewById<TextView>(R.id.nameTripleTriad)
        val image = findViewById<ImageView>(R.id.imageTriad)
        val patch = findViewById<TextView>(R.id.patch)
        val owned = findViewById<TextView>(R.id.own)
        val desc = findViewById<TextView>(R.id.desc)

        val intentNameTriad = intent.getStringExtra("name")
        val intentImage = intent.getStringExtra("image")
        val intentPatch = intent.getStringExtra("patch")
        val intentOwned = intent.getStringExtra("owned")
        val intentDesc = intent.getStringExtra("desc")

        nameTriad.text = intentNameTriad
        Glide.with(this)
            .load(intentImage)
            .placeholder(R.drawable.img_placeholder)
            .error(R.drawable.img_placeholder)
            .into(image)
        patch.text = intentPatch
        owned.text = intentOwned
        desc.text = intentDesc

        val actionBar: ActionBar? = supportActionBar
        actionBar?.setTitle(intentNameTriad)
        actionBar!!.setDisplayHomeAsUpEnabled(true)
    }
}