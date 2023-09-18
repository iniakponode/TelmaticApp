package com.uoa.telmaticsapp.util

import android.content.Context
import android.content.Intent
import androidx.core.content.FileProvider
import com.uoa.telmaticsapp.BuildConfig
import java.io.File
import java.util.*

class FileUtils {

     fun generateFile(context: Context, fileName:String): File?{
        val csvFile=File(context.filesDir,fileName)
        csvFile.createNewFile()

        return if (csvFile.exists()){
            csvFile
        }else{
            null
        }
    }
     fun gotoFile(context: Context,fileName: File): Intent {
        val intent=Intent(Intent.ACTION_VIEW)
         val contentUri=FileProvider.getUriForFile(
             Objects.requireNonNull(context),
             BuildConfig.APPLICATION_ID + ".fileProvider", fileName);
//        val contentUri=FileProvider.getUriForFile(context,"${context.packageName}.fileProvider",fileName)
        val mimeType=context.contentResolver.getType(contentUri)
        intent.setDataAndType(contentUri,mimeType)
        intent.flags=Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
        return  intent

    }
}