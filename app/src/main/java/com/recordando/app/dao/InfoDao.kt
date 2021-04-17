package com.recordando.app.dao

import androidx.room.*
import com.recordando.app.entities.Infos


@Dao
interface InfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInfo(info:Infos)

}