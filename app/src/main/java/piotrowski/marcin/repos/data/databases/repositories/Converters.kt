package piotrowski.marcin.repos.data.databases.repositories

import android.arch.persistence.room.TypeConverter
import piotrowski.marcin.repos.data.models.Repository

object Converters {

    @TypeConverter
    @JvmStatic
    fun fromSourceEnum(value: Repository.Source?): Int? {
        return when(value){
            Repository.Source.GITHUB -> 1
            Repository.Source.BITBUCKET -> 2
            else -> {
                0
            }
        }
    }

    @TypeConverter
    @JvmStatic
    fun toSourceEnum(value: Int?): Repository.Source? {
        return when(value){
             1 -> Repository.Source.GITHUB
             2 -> Repository.Source.BITBUCKET
            else -> {
                Repository.Source.UNKNOWN
            }
        }
    }
}