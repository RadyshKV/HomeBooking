package com.geekbrains.homebooking.di.modules

import android.content.Context
import androidx.room.Room
import com.geekbrains.homebooking.db.AppDatabase
import com.geekbrains.homebooking.db.cache.IHotelsCache
import com.geekbrains.homebooking.db.cache.ICitiesCache
import com.geekbrains.homebooking.db.cache.RoomHotelsCache
import com.geekbrains.homebooking.db.cache.RoomCitiesCache
import dagger.Module
import dagger.Provides

private const val DB_NAME = "database.db"

@Module
class CacheModule {

    @Provides
    fun db(context: Context): AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java,
        DB_NAME
    )
        .build()

    @Provides
    fun citiesCache(db: AppDatabase): ICitiesCache {
        return RoomCitiesCache(db)
    }

    @Provides
    fun hotelsCache(db: AppDatabase): IHotelsCache {
        return RoomHotelsCache(db)
    }
}