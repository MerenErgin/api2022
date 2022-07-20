package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GoRestResponsePojo {

    //1) Tum keyler icin private veriable'lar olusturulur
    private Object meta;
    private GoRestDataPojo data;

    //2) Tum parametrelerle ve parametresiz constructor'lar olusturulur
    public GoRestResponsePojo(Object meta, GoRestDataPojo data) {
        this.meta = meta;
        this.data = data;
    }

    public GoRestResponsePojo() {
    }

    //3) Getters ve Setters'lar olusturulur
    public Object getMeta() {
        return meta;
    }

    public void setMeta(Object meta) {
        this.meta = meta;
    }

    public GoRestDataPojo getData() {
        return data;
    }

    public void setData(GoRestDataPojo data) {
        this.data = data;
    }

    //4) toString methodu olusturulur
    @Override
    public String toString() {
        return "GoRestResponsePojo{" +
                "meta=" + meta +
                ", data=" + data +
                '}';
    }
}
