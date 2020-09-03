package com.notes.zk.newnotes.view

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.notes.zk.newnotes.bean.SecondaryListDataTree
import java.util.*

abstract class SecondaryListAdapter<K, V, GVH : RecyclerView.ViewHolder, SVH : RecyclerView.ViewHolder> :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_GROUP_ITEM = 0
        const val VIEW_TYPE_SUB_ITEM = 1
    }

    var dataTrees: List<SecondaryListDataTree<K, V>> = ArrayList()

    abstract fun groupItemViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    abstract fun subItemViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        var viewHolder: RecyclerView.ViewHolder? = null

        if (p1 == VIEW_TYPE_GROUP_ITEM) {
            viewHolder = groupItemViewHolder(p0)
        } else if (p1 == VIEW_TYPE_SUB_ITEM) {
            viewHolder = subItemViewHolder(p0)
        }

        return viewHolder!!
    }

    abstract fun onGroupItemBindViewHolder(holder: RecyclerView.ViewHolder, groupItemIndex: Int)

    abstract fun onSubItemBindViewHolder(holder: RecyclerView.ViewHolder, groupItemIndex: Int, subItemIndex: Int)

    abstract fun onGroupItemClick(isExpand: Boolean, holder: GVH, groupItemIndex: Int)

    abstract fun onSubItemClick(holder: SVH, groupItemIndex: Int, subItemIndex: Int)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemStatus = getItemStatusByPosition(position)
        val data = dataTrees[itemStatus.groupItemIndex]
        if (itemStatus.viewType == VIEW_TYPE_GROUP_ITEM) {
            onGroupItemBindViewHolder(holder, itemStatus.groupItemIndex)
            holder.itemView.setOnClickListener {
                if (!data.groupItemStatus) {
                    onGroupItemClick(false, holder as GVH, itemStatus.groupItemIndex)
                    data.groupItemStatus = true
                    notifyItemRangeInserted(holder.adapterPosition + 1, data.subItems.size)
                } else {
                    onGroupItemClick(true, holder as GVH, itemStatus.groupItemIndex)
                    data.groupItemStatus = false
                    notifyItemRangeRemoved(holder.adapterPosition + 1, data.subItems.size)
                }
            }
        } else if (itemStatus.viewType == VIEW_TYPE_SUB_ITEM) {
            onSubItemBindViewHolder(holder, itemStatus.groupItemIndex, itemStatus
                    .subItemIndex)
            holder.itemView.setOnClickListener { onSubItemClick(holder as SVH, itemStatus.groupItemIndex, itemStatus.subItemIndex) }
        }
    }

    override fun getItemCount(): Int {
        var itemCount = 0

        for (data in dataTrees) {
            if (data.groupItemStatus) {
                itemCount += data.subItems.size + 1
            } else {
                itemCount++
            }
        }

        return itemCount
    }

    override fun getItemViewType(position: Int): Int {
        return getItemStatusByPosition(position).viewType
    }

    private fun getItemStatusByPosition(position: Int): ItemStatus {
        var itemStatus: ItemStatus? = null
        var count = 0
        var i = 0
        for (indices in dataTrees.indices) {
            i = indices
            if (count == position) {
                itemStatus = ItemStatus()
                itemStatus.viewType = VIEW_TYPE_GROUP_ITEM
                itemStatus.groupItemIndex = indices
                break
            } else if (count > position) {
                itemStatus = ItemStatus()
                itemStatus.viewType = VIEW_TYPE_SUB_ITEM
                itemStatus.groupItemIndex = (indices - 1)
                itemStatus.subItemIndex = (position - (count - dataTrees[indices - 1].subItems.size))
                break
            }
            count++

            if (dataTrees[indices].groupItemStatus) {
                count += dataTrees[indices].subItems.size
            }
        }

        if (i >= dataTrees.size - 1 && itemStatus == null) {
            itemStatus = ItemStatus()
            itemStatus.viewType = VIEW_TYPE_SUB_ITEM
            itemStatus.groupItemIndex = i
            itemStatus.subItemIndex = (position - (count - dataTrees[i].subItems.size))
        }
        return itemStatus ?: ItemStatus()
    }

    private class ItemStatus {
        var viewType = 0
        var groupItemIndex = -1
        var subItemIndex = -1
    }
}