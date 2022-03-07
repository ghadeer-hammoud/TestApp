package com.example.testapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.testapp.FakeDataSource
import com.example.testapp.models.Level
import java.util.concurrent.Executors

@Database(
    entities = [Level::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
public abstract class AppDatabase: RoomDatabase() {

    abstract fun levelDao(): LevelDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase{

            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "levels.db"
                ).addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        Executors.newSingleThreadExecutor().execute {
                            INSTANCE!!.levelDao().insertAll(FakeDataSource.getFakeData())
                        }
                       // getInstance(context).levelDao().insertAll(FakeDataSource.getFakeData())

                    }
                })
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}