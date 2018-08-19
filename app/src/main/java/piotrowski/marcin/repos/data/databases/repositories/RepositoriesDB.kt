package piotrowski.marcin.repos.data.databases.repositories

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import piotrowski.marcin.repos.data.dao.RepositoryDao
import piotrowski.marcin.repos.data.models.Repository

@Database(entities = [(Repository::class)], version = 1, exportSchema =  false)
@TypeConverters(Converters::class)
abstract class RepositoriesDB : RoomDatabase() {
    abstract fun repositoryDao(): RepositoryDao

    companion object {
        fun create(context: Context): RepositoryDao {
            return Room.databaseBuilder(context, RepositoriesDB::class.java, "repositories-db")
                    .allowMainThreadQueries()
                    .build()
                    .repositoryDao()
        }
    }
}