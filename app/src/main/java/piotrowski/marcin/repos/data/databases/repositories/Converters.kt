package piotrowski.marcin.repos.data.databases.repositories

import android.arch.persistence.room.TypeConverter
import piotrowski.marcin.repos.data.models.Repository

object Converters {

    @TypeConverter
    @JvmStatic
    fun fromSourceEnum(value: Repository.Source?): Int? {
        when(value){
            Repository.Source.GITHUB -> return 1
            Repository.Source.BITBUCKET -> return 2
        }
        return 0
    }

    @TypeConverter
    @JvmStatic
    fun toSourceEnum(value: Int?): Repository.Source? {
        when(value){
             1 -> return Repository.Source.GITHUB
             2 -> return Repository.Source.BITBUCKET
        }
        return Repository.Source.UNKNOWN
    }
}