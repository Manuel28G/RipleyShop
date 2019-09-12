package cl.com.ripley.ripleyshop.general.model;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.util.Log;
import android.util.TypedValue;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.DecimalFormat;

import cl.com.ripley.ripleyshop.R;
public class UtilHelper {

    private static final String TAG = UtilHelper.class.toString();
    private static final Gson sGson = new GsonBuilder().create();
    private static final String PATTERN_NUMBER = "#,###";
    private static DecimalFormat sDecimalFormat = new DecimalFormat(PATTERN_NUMBER);

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

    public static String parseObjectToJsonString(Object t){
        return sGson.toJson(t);
    }

    public static <T> T  parseJsonToObject(String json, Class<T> _class){
        return sGson.fromJson(json, _class);
    }

    /**
     * Método que convierte un string tipo arreglo en una lista de objetos
     * @param json json string que se desea pasar
     * @return lista de objetos
     */
    public static <T> T parseJsonToArrayFromArrayJson(String json, final Type type){
        return sGson.fromJson(json,type);
    }

    private static Type getType(final Class<?> rawClass,final Class<?> parameter) {
        return new ParameterizedType() {
            @Override
            public Type[] getActualTypeArguments() {
                return new Type[] {parameter};
            }
            @Override
            public Type getRawType() {
                return rawClass;
            }
            @Override
            public Type getOwnerType() {
                return null;
            }
        };
    }

    /**
     * Metodo que retorna un numero en formato ##.##,###
     * @param number numero que se desea transformar {int}
     * @return numbero transformado al formato establecido {String}
     */
    public static String getFormmated(int number){
        return sDecimalFormat.format(number).replace(",",".");
    }

    /**
     * Metodo utilizado para tachar el texto contenido en un textview
     * @param textView Textview que se desea aplicar el tachado
     */
    public static void strikeText(TextView textView){
        textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }

    /**
     * Metodo para crear el texto (singularo o plural) para mostrar en pantalla
     * @param productCount cantidad de productos {int}
     * @return cantidad de productos en String {String}
     */
    public static String countProduct(int productCount,String singleString,String pluralString){
        String detailTitle = Integer.toString(productCount);
        if(productCount == 1){
            detailTitle += " "+ singleString;
        }
        else
        {
            detailTitle += " "+ pluralString;
        }
        return detailTitle;
    }
}
