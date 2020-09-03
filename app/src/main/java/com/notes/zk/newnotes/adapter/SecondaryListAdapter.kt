package com.notes.zk.newnotes.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.notes.zk.newnotes.R
import com.notes.zk.newnotes.bean.Album
import com.notes.zk.newnotes.bean.SecondaryListDataTree
import com.notes.zk.newnotes.view.SecondaryListAdapter


class SecondaryListAdapter(context: Context, data: MutableList<SecondaryListDataTree<Album, Album.Song>>) : SecondaryListAdapter<Album, Album.Song, com.notes.zk.newnotes.adapter.SecondaryListAdapter.GroupItemViewHolder, com.notes.zk.newnotes.adapter.SecondaryListAdapter.SubItemViewHolder>() {
    private var mContext: Context = context

    init {
        dataTrees = data
    }

    class GroupItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvGroupAlbumImage: ImageView = itemView.findViewById<View>(R.id.item_secondary_list_album_iv) as ImageView
        var tvGroupAlbumName: TextView = itemView.findViewById<View>(R.id.item_secondary_list_album_name_tv) as TextView
        var tvGroupAlbumYear: TextView = itemView.findViewById<View>(R.id.item_secondary_list_album_year_tv) as TextView
        var tvGroupAlbumNumber: TextView = itemView.findViewById<View>(R.id.item_secondary_list_album_number_tv) as TextView
        var ivGroup: ImageView = itemView.findViewById<View>(R.id.item_secondary_list_iv) as ImageView
    }

    class SubItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvSubSongName: TextView = itemView.findViewById<View>(R.id.item_secondary_list_song_name_tv) as TextView
        var tvSubSongTime: TextView = itemView.findViewById<View>(R.id.item_secondary_list_song_time_tv) as TextView
    }

    override fun groupItemViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.item_secondary_list_group, parent, false)

        return GroupItemViewHolder(v)
    }

    override fun subItemViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.item_secondary_list_sub, parent, false)

        return SubItemViewHolder(v)
    }

    override fun onGroupItemBindViewHolder(holder: RecyclerView.ViewHolder, groupItemIndex: Int) {
        val album = dataTrees[groupItemIndex].groupItem
        val groupItemViewHolder = holder as GroupItemViewHolder
        groupItemViewHolder.tvGroupAlbumName.text = album.albumName
        groupItemViewHolder.tvGroupAlbumYear.text = album.albumYear
        groupItemViewHolder.tvGroupAlbumNumber.text = "${album.albumSongNumber}首"
        groupItemViewHolder.tvGroupAlbumImage.setImageDrawable(mContext.resources.getDrawable(album.albumImageUrl))
    }

    override fun onSubItemBindViewHolder(holder: RecyclerView.ViewHolder, groupItemIndex: Int, subItemIndex: Int) {
        val song = (dataTrees[groupItemIndex].subItems)[subItemIndex]
        val subItemViewHolder = holder as SubItemViewHolder
        subItemViewHolder.tvSubSongName.text = song.songName
        subItemViewHolder.tvSubSongTime.text = song.songTime
    }

    override fun onGroupItemClick(isExpand: Boolean, holder: GroupItemViewHolder, groupItemIndex: Int) {
        Toast.makeText(mContext, "group item $groupItemIndex is expand $isExpand", Toast.LENGTH_SHORT).show()
        if (isExpand) {
            holder.ivGroup.clearAnimation()
//            val rotate = AnimationUtils.loadAnimation(context, R.anim.anticlockwise_rotation)
//            holder.ivGroup.startAnimation(rotate)
        } else {
            val rotate = AnimationUtils.loadAnimation(mContext, R.anim.clockwise_rotation)
            holder.ivGroup.startAnimation(rotate)
        }
    }

    override fun onSubItemClick(holder: SubItemViewHolder, groupItemIndex: Int, subItemIndex: Int) {
        Toast.makeText(mContext, "sub item $subItemIndex in group item $groupItemIndex", Toast.LENGTH_SHORT).show()
    }
}