package com.production.sidorov.ivan.notekotlinmvp.mvp.model

import android.arch.persistence.room.*
import android.text.format.DateUtils
import java.util.*

@Entity(tableName = "notes")
data class Note(
        @ColumnInfo(name = "title") var title: String = "",
        @ColumnInfo(name = "text") var text: String = "",
        @ColumnInfo(name = "created_date")  var createdDate: Date = Calendar.getInstance().time,
        @ColumnInfo(name = "changed_at")  var changedDate: Date = Calendar.getInstance().time){
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true) var id: Long = 0
}


