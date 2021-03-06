package com.project.forensika.checktools

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.project.forensika.R
import com.project.forensika.model.CheckTools.Result
import java.util.*
import androidx.appcompat.widget.Toolbar

class AplikasiActivity : AppCompatActivity() {
    private lateinit var imageViewTools: ImageView
    private lateinit var textViewAturan: TextView
    private lateinit var textViewTool: TextView
    private lateinit var textViewDate: TextView
    private lateinit var buttonOk: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aplikasi)

        val aplikasiList: ArrayList<Result>? = intent.getParcelableArrayListExtra(CheckTools.CHECK_TOOLS_RESULT)

        imageViewTools = findViewById(R.id.image_Logo_Tools)
        textViewAturan = findViewById(R.id.tv_aturan)
        textViewTool = findViewById(R.id.tv_tool)
        textViewDate = findViewById(R.id.tv_created_at)
        buttonOk = findViewById(R.id.btn_ok)
//        val toolbar = findViewById<Toolbar>(R.id.toolbar)
//        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)

        aplikasiList?.let { aplikasis->
            if (!aplikasis.isNullOrEmpty()) {
                aplikasis.forEach { aplikasi->
                    textViewAturan.text = getString(R.string.aturan, aplikasi.namaAturan)
                    textViewTool.text = getString(R.string.tool, aplikasi.namaAplikasi)
                    Glide.with(imageViewTools)
                            .load(aplikasi.fotoAplikasi)
                            .into(imageViewTools)
                    textViewDate.text = aplikasi.createdAt
                }
            }else{
                textViewAturan.text = "-"
                textViewTool.text = "-"
                Glide.with(imageViewTools)
                        .load(R.drawable.nodata)
                        .into(imageViewTools)
                textViewDate.text = "-"
            }
        }

        buttonOk.setOnClickListener {
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}