package cl.com.ripley.ripleyshop.general.model;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.util.TypedValue;
import java.io.IOException;
import java.io.InputStream;

public class UtilHelper {

    private static final String TAG = UtilHelper.class.toString();

    /**
     * Metodo que retorna la lectura de un archivo en memoria desde la carpeta assets de android
     * @param context Contexto de la aplicación
     * @param fileName nombre del archivo con su extensión
     * @return contenido del archivo
     */
    public static String readFile(Context context, String fileName){
        String fileString = null;
        try {
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            fileString = new String(buffer, "UTF-8");
        }
        catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }
        return fileString;
    }

    /**
     * Metodo que funciona para pasar de DP a PX
     * @param dp densidad de pixeles
     * @param resources variable resource del sistema
     * @return cantidad de pixeles equivalentes a los DP
     */
    public static int dpToPx(int dp, Resources resources) {
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics()));
    }
}
