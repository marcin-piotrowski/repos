package piotrowski.marcin.repos.data.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "repositories")
data class Repository(
        @ColumnInfo(name = "owner_name")
        val ownerName: String,
        @ColumnInfo(name = "owner_avatar")
        val ownerAvatarUrl: String,
        @ColumnInfo(name = "name")
        val name: String,
        @ColumnInfo(name = "description")
        val description: String?,
        @ColumnInfo(name = "source")
        val source: Source
){
        @ColumnInfo(name = "id")
        @PrimaryKey(autoGenerate = true) var id: Long = 0

        enum class Source {
                GITHUB, BITBUCKET, UNKNOWN
        }
}