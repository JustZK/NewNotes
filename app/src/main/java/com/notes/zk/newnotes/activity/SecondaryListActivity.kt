package com.notes.zk.newnotes.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.WindowManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.notes.zk.newnotes.R
import com.notes.zk.newnotes.adapter.SecondaryListAdapter
import com.notes.zk.newnotes.bean.Album
import com.notes.zk.newnotes.bean.SecondaryListDataTree
import com.notes.zk.newnotes.databinding.ActivitySecondaryListBinding
import com.notes.zk.newnotes.view.RvDividerItemDecoration

class SecondaryListActivity : AppCompatActivity() {
    private lateinit var mSecondaryListBinding: ActivitySecondaryListBinding

    private var data : MutableList<SecondaryListDataTree<Album, Album.Song>> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mSecondaryListBinding = DataBindingUtil.setContentView(this, R.layout.activity_secondary_list)

        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)

        setSupportActionBar(mSecondaryListBinding.secondaryListToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        Glide.with(this).load(R.drawable.jay_chou).apply(RequestOptions().fitCenter()).into(mSecondaryListBinding.secondaryListTopIv)

        val albumFantasy = Album()
        albumFantasy.albumName = "范特西"
        albumFantasy.albumYear = "2001年9月14号"
        albumFantasy.albumSongNumber = 10
        albumFantasy.albumImageUrl = R.drawable.fantasy_image
        albumFantasy.albumSongs.add(Album.Song("爱在西元前", "3:54"))
        albumFantasy.albumSongs.add(Album.Song("爸我回来了", "3:55"))
        albumFantasy.albumSongs.add(Album.Song("简单爱", "4:30"))
        albumFantasy.albumSongs.add(Album.Song("忍者", "2:38"))
        albumFantasy.albumSongs.add(Album.Song("开不了口", "4:44"))
        albumFantasy.albumSongs.add(Album.Song("上海一九四三", "3:15"))
        albumFantasy.albumSongs.add(Album.Song("对不起", "3:43"))
        albumFantasy.albumSongs.add(Album.Song("威廉古堡", "3:56"))
        albumFantasy.albumSongs.add(Album.Song("双节棍", "3:21"))
        albumFantasy.albumSongs.add(Album.Song("安静", "5:34"))
        data.add(SecondaryListDataTree(albumFantasy, albumFantasy.albumSongs))
        val sevenFantasy = Album()
        sevenFantasy.albumName = "七里香"
        albumFantasy.albumYear = "2004年8月3号"
        sevenFantasy.albumSongNumber = 10
        sevenFantasy.albumImageUrl = R.drawable.qilixiang_image
        sevenFantasy.albumSongs.add(Album.Song("我的地盘", "3:54"))
        sevenFantasy.albumSongs.add(Album.Song("七里香", "3:55"))
        sevenFantasy.albumSongs.add(Album.Song("接口", "4:30"))
        sevenFantasy.albumSongs.add(Album.Song("外婆", "2:38"))
        sevenFantasy.albumSongs.add(Album.Song("将军", "4:44"))
        sevenFantasy.albumSongs.add(Album.Song("搁浅", "3:15"))
        sevenFantasy.albumSongs.add(Album.Song("乱舞春秋", "3:43"))
        sevenFantasy.albumSongs.add(Album.Song("困兽之斗", "3:56"))
        sevenFantasy.albumSongs.add(Album.Song("游园会", "3:21"))
        sevenFantasy.albumSongs.add(Album.Song("止战之殇", "5:34"))
        data.add(SecondaryListDataTree(sevenFantasy, sevenFantasy.albumSongs))


        val adapter = SecondaryListAdapter(this, data)

        mSecondaryListBinding.secondaryListRv.layoutManager = LinearLayoutManager(this)
        mSecondaryListBinding.secondaryListRv.setHasFixedSize(true)
//        mSecondaryListBinding.secondaryListRv.addItemDecoration(RvDividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        mSecondaryListBinding.secondaryListRv.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}