package piotrowski.marcin.repos.data.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import io.reactivex.Observable
import piotrowski.marcin.repos.data.models.Repository

@Dao
interface RepositoryDao {

    @Query("SELECT * FROM repositories WHERE id = :id")
    fun getById(id: Long): Flowable<Repository>

    @Query("SELECT * FROM repositories")
    fun getAll(): Flowable<List<Repository>>

    @Insert(onConflict = REPLACE)
    fun insert(vararg repository: Repository)
}