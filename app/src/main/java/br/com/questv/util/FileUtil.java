package br.com.questv.util;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.content.FileProvider;
import android.util.Log;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.util.Objects;

import static android.content.ContentValues.TAG;
import static android.os.Environment.DIRECTORY_PICTURES;

public class FileUtil {

  private static final String AUTHORITY = "br.com.questv";

  public static Uri writeResponseBodyToDisk(@Nullable final ResponseBody responseBody,
                                            @NotNull final Long id,
                                            @Nullable Context context) {

    assert context != null;
    String fileName = String.valueOf(id);
    File root = context.getExternalFilesDir(DIRECTORY_PICTURES);
    final File file = new File(root, fileName + ".png");

    downloadContent(file, responseBody);
    return getUriFromFile(context, file);
  }

  private static void downloadContent(final File file, final ResponseBody responseBody) {
    if (!file.exists()) {
      try {
        file.createNewFile();
        new Asynchronous(file).execute(responseBody);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private static Uri getUriFromFile(final Context context, final File file) {
    if (file != null) {
      return FileProvider.getUriForFile(context, AUTHORITY, file);
    }
    return null;
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
