package mx.com.nmp.dis.elastic.util;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class FieldUtil {

    private FieldUtil() {

    }

    public static String getString(Map<String, Object> fields, String campo) {
        String result = null;
        if (fields.containsKey(campo)) {
            result = fields.get(campo).toString();
        }
        return result;
    }

    public static Date getDate(Map<String, Object> fields, String campo) {
        Date result = null;
        if (fields.containsKey(campo)) {
            result = (Date) fields.get(campo);
        }
        return result;
    }

    public static Double getDouble(Map<String, Object> fields, String campo) {
        Double result = null;
        if (fields.containsKey(campo)) {
            result = Double.valueOf(fields.get(campo).toString());
        }
        return result;
    }

    public static Long getLong(Map<String, Object> fields, String campo) {
        Long result = null;
        if (fields.containsKey(campo)) {
            result = Long.parseLong(fields.get(campo).toString());
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public static List<Long> getListLong(Map<String, Object> fields, String campo) {
        List<Long> result = null;
        if (fields.containsKey(campo)) {
            List<Integer> list = (List<Integer>) fields.get(campo);
            result = list.stream().map(Integer::longValue).collect(Collectors.toList());
        }
        return result;
    }
}
