package com.notes.zk.newnotes.bean

import com.notes.zk.newnotes.R

class Album() {
    var albumName : String? = null
    var albumImageUrl : Int = R.drawable.jay_chou
    var albumYear = "2004年8月3号"
    var albumSongNumber = 1
    var albumSongs : MutableList<Song> = mutableListOf()

    open class Song(songName : String, songTime : String){
        var songName = songName
        var songTime  = songTime
    }
}