package dev.mkeeda.spaceship.infra.datasource

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.mkeeda.spaceship.data.credential.SecureAccessConfig
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SecureAccessFileDataSource @Inject constructor(
    @ApplicationContext context: Context
) {
    private val contentResolver = context.contentResolver

    suspend fun getFileName(secureAccessFilePath: Uri): String {
        // TODO Inject Dispatcher
        return withContext(Dispatchers.IO) {
            val cursor = contentResolver.query(secureAccessFilePath, null, null, null)
            checkNotNull(cursor) {
                "ContentResolver cursor is null"
            }
            val fileName = cursor.use { cursor ->
                val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                cursor.getString(nameIndex)
            }
            fileName
        }
    }

    suspend fun load(secureAccessFilePath: Uri): SecureAccessConfig {
        withContext(Dispatchers.IO) {
            TODO()
        }
    }
}
