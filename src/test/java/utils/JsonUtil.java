package utils;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class JsonUtil {

    private static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
    }

    //1.Method: Json Datasini Java Objesine cevirir.(De-Serialization)

    public static <T> T convertJsonToJava(String json, Class<T> cls){//Generic Method(Her data tipi ile calisabilen)
        T javaResult = null;

        try {
            javaResult = mapper.readValue(json,cls);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return javaResult;
    }

    //2.Method: Java Objesini Json Datasina cevirir.(Serialization)

}
