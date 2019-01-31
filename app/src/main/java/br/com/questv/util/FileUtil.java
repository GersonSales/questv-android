package br.com.questv.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import static android.content.ContentValues.TAG;
import static android.os.Environment.DIRECTORY_PICTURES;

public class FileUtil {

  private static final String AUTHORITY = "br.com.questv";
  private static final String FAILURE = "Failure";
  private static final String JPG = "jpg";
  private static final String UNDERSCORE = "_";


  public static Uri writeResponseBodyToDisk(@Nullable ResponseBody responseBody, @Nullable Context context) {

    assert context != null;
//    File futureStudioIconFile = new File(context.getExternalFilesDir() + File.separator + "image.png");
    final File file = createImageFile(context);

    new Asynchronous(file).execute(responseBody);
    return getUriFromFile(context, file);

  }

  private static Uri getUriFromFile(final Context context, final File file) {
    if (file != null) {
      return FileProvider.getUriForFile(context, AUTHORITY, file);
    }
    return null;
  }

  private static File createImageFile(final Context context) {
    if (context != null) {
      return createMediaFile(context.getExternalFilesDir(DIRECTORY_PICTURES + "/image"), JPG);
    }
    return Environment.getExternalStorageDirectory();
  }

  private static File createMediaFile(final File storageDirectory,
                                      final String extension) {
    final @SuppressLint("SimpleDateFormat") String timeStamp =
        new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

    final String fileName = extension.toUpperCase(Locale.getDefault()) +
        UNDERSCORE + timeStamp + UNDERSCORE;
    return createMediaFile(storageDirectory, fileName, extension);
  }

  private static File createMediaFile(final File storageDirectory, final String fileName,
                                      final String extension) {
    File mediaFile = null;
    try {
      mediaFile = File.createTempFile(
          fileName + "-",  /* prefix */
          "." + extension,         /* suffix */
          storageDirectory      /* directory */
      );

    } catch (IOException e) {
      Log.e(FAILURE, e.getMessage(), e);
    }
    return mediaFile;
  }


  static class Asynchronous extends AsyncTask<ResponseBody, Void, Void> {
    private final File file;

    private Asynchronous(final File file) {
      this.file = file;
    }


    @Override
    protected Void doInBackground(ResponseBody... responseBodies) {
      writeResponseBodyToDisk(responseBodies[0], file);
      return null;
    }


    private void writeResponseBodyToDisk(@Nullable ResponseBody responseBody, final File file) {


      try {
        // todo change the file location/name according to your needs

        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
          byte[] fileReader = new byte[4096];

          long fileSize = Objects.requireNonNull(responseBody).contentLength();
          long fileSizeDownloaded = 0;

          inputStream = responseBody.byteStream();
          outputStream = new FileOutputStream(file);

          while (true) {
            int read = inputStream.read(fileReader);

            if (read == -1) {
              break;
            }

            outputStream.write(fileReader, 0, read);

            fileSizeDownloaded += read;

            Log.d(TAG, "file download: " + fileSizeDownloaded + " of " + fileSize);
          }

          outputStream.flush();

//          return true;
        } catch (IOException e) {
//          return false;
        } finally {
          if (inputStream != null) {
            inputStream.close();
          }

          if (outputStream != null) {
            outputStream.close();
          }
        }
      } catch (IOException e) {
//        return false;
      }
    }


  }


}
