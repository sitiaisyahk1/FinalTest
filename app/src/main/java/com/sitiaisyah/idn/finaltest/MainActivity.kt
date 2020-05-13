package com.sitiaisyah.idn.finaltest

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sitiaisyah.idn.finaltest.data.MainViewModel
import com.sitiaisyah.idn.finaltest.databinding.ActivityMainBinding
import com.sitiaisyah.idn.finaltest.room.Comment
import com.sitiaisyah.idn.finaltest.room.CommentAdapter
import com.sitiaisyah.idn.finaltest.room.CommentViewModel
import com.sitiaisyah.idn.finaltest.story.StoryAdapter
import com.sitiaisyah.idn.finaltest.story.StoryModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var StoryAdapter: StoryAdapter

    private val newCommentInputRequestCode = 1
    private lateinit var commentViewModel: CommentViewModel

    private lateinit var inputCommentText : EditText
    companion object{
        const val EXTRA_REPLAY = "com.sitiaisyah.idn.wordlistsql.REPLAY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        val mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        binding.mainBinding = mainViewModel
        binding.lifecycleOwner = this

        binding.camera = getDrawable(R.drawable.camera_ig)
        binding.dm = getDrawable(R.drawable.dm_ig)
        binding.pp = getDrawable(R.drawable.pp_ig)
        binding.menu = getDrawable(R.drawable.menu_ig)
        binding.post = getDrawable(R.drawable.post_ig)
        binding.comment = getDrawable(R.drawable.comment_ig)
        binding.share = getDrawable(R.drawable.send_ig)
        binding.bookmark = getDrawable(R.drawable.bookmark_ig)
        binding.user = getDrawable(R.drawable.user_ig)

        val recyclerView = findViewById<RecyclerView>(R.id.rv_comment)
        val adapter = CommentAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        commentViewModel = ViewModelProvider(this).get(CommentViewModel::class.java)

        commentViewModel.allComments.observe(this, Observer { comment ->
            comment?.let {
                adapter.setComment(it)
            }
        })

//        if (intent.extras != null) {
//            val bundle = intent.extras
//            comment.setText(bundle!!.getString("comment"))
//        } else {
//            comment.setText(intent.getStringExtra("comment"))
//        }


        showRecylerList()

        inputCommentText = findViewById(R.id.et_comment)

        val post = findViewById<TextView>(R.id.tv_post)
        post.setOnClickListener {
            val moveData = Intent()
//            val bundle = Bundle()       Aku ga tau cara biar data output nya muncul di MainActivity, yang aku pake code buat move Intent :(
            if (TextUtils.isEmpty(inputCommentText.text)){
                setResult(Activity.RESULT_CANCELED, moveData)
            }else{
                val comment = inputCommentText.text.toString()
                moveData.putExtra(MainActivity.EXTRA_REPLAY, comment)
                setResult(Activity.RESULT_OK, moveData)
            }
            finish()
        }
    }

    fun getListSg(): ArrayList<StoryModel> {
        val username = resources.getStringArray(R.array.username)
        val img = resources.obtainTypedArray(R.array.image)

        val listSg = ArrayList<StoryModel>()

        for (position in username.indices) {
            val Sg = StoryModel(
                username[position],
                img.getResourceId(position, -1)
            )
            listSg.add(Sg)
        }
        return listSg
    }

    private fun showRecylerList() {
        StoryAdapter = StoryAdapter { showSelected(it) }
        StoryAdapter.notifyDataSetChanged()
        StoryAdapter.setData(getListSg())

        rv_sg.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_sg.setHasFixedSize(true)
        rv_sg.adapter = StoryAdapter
    }

    private fun showSelected(it: StoryModel) {
        TODO("Not yet implemented")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == newCommentInputRequestCode && resultCode == Activity.RESULT_OK){
            data?.let { data ->
                val comment = Comment(
                    data.getStringExtra(EXTRA_REPLAY)
                )
                commentViewModel.insert(comment)
                Unit
            }
        }else{
            Toast.makeText(applicationContext,getString(R.string.error_empty_field), Toast.LENGTH_LONG).show()
        }
    }

}
