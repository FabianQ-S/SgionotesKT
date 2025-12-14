package com.example.sgionoteskt.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.sgionoteskt.data.model.Etiqueta
import com.example.sgionoteskt.data.model.EtiquetaNotaCrossRef
import com.example.sgionoteskt.data.model.Nota
import com.example.sgionoteskt.data.model.UserPreference
import com.example.sgionoteskt.data.local.EtiquetaDao
import com.example.sgionoteskt.data.local.EtiquetaNotaDao
import com.example.sgionoteskt.data.local.NotaDao
import com.example.sgionoteskt.data.local.UserPreferenceDao

@Database(
    entities = [
        Nota::class,
        Etiqueta::class,
        EtiquetaNotaCrossRef::class,
        UserPreference::class
    ],
    version = 4
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun notaDao(): NotaDao
    abstract fun etiquetaDao(): EtiquetaDao
    abstract fun etiquetaNotaDao(): EtiquetaNotaDao
    abstract fun userPreferenceDao(): UserPreferenceDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE notas ADD COLUMN fecha_favorito INTEGER DEFAULT NULL")
            }
        }

        private val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE etiquetas ADD COLUMN es_favorito INTEGER NOT NULL DEFAULT 0")
                database.execSQL("ALTER TABLE etiquetas ADD COLUMN fecha_favorito INTEGER DEFAULT NULL")
            }
        }

        private val MIGRATION_3_4 = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE IF NOT EXISTS user_preferences (pref_key TEXT NOT NULL PRIMARY KEY, pref_value TEXT NOT NULL)")
            }
        }

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "notas_db"
                )
                    .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4)
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}