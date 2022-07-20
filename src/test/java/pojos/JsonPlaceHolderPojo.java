package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
// Farkli key-value ikililerin uyusmazligi @JsonIgnoreProperties(ignoreUnknown = true)
// anotation'ini Pojo Class'in basina yazarak cozebiliriz.
public class JsonPlaceHolderPojo {

    /*
    1) Tum keyler icin private veriable'lar olusturulr
    2) Tum parametrelerle ve parametresiz constructor'lar olusturulur
    3) Getters ve Setters'lar olusturulur
    4) toString methodu olusturulur
     */

    //1) Tum keyler icin private veriable'lar olusturulr
    private Integer userId;
    private String title;
    private Boolean completed;

    //2) Tum parametrelerle ve parametresiz constructor'lar olusturulur
    public JsonPlaceHolderPojo(Integer userId, String title, Boolean completed) {
        this.userId = userId;
        this.title = title;
        this.completed = completed;
        //this.= class seviyesindeki variable'i kullanir
    }

    public JsonPlaceHolderPojo() {
    }

    //3) Getters ve Setters'lar olusturulur
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    //4) toString methodu olusturulur

    @Override
    public String toString() {
        return "JsonPlaceHolderPojo{" +
                "userId=" + userId +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }


}
