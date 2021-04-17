package com.recordando.app.entities

import androidx.room.Entity
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Infos(

    @PrimaryKey
    var nome: String? = null,
    var idade:String? = null,
    var tell:String? = null,
    var tell2:String? = null,
    var email:String? = null


) : RealmObject()