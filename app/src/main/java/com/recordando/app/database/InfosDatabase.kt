package com.recordando.app.database

import android.app.Application
import com.recordando.app.entities.Infos
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass


class InfosDatabase : Application() {
    override fun onCreate() {
        super.onCreate()


        Realm.init(this)

        val configuration = RealmConfiguration.Builder()
            .name("Infos.realm")
            .deleteRealmIfMigrationNeeded()
            .schemaVersion(0)
            .build()

        Realm.setDefaultConfiguration(configuration)


    }
}