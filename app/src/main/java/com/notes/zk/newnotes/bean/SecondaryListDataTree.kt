package com.notes.zk.newnotes.bean

class SecondaryListDataTree<K, V>(groupItem: K, subItems: List<V>) {
    var groupItemStatus = false
    var groupItem : K = groupItem
    var subItems : List<V> = subItems

}