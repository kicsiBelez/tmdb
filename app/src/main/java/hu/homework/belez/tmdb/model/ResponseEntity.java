package hu.homework.belez.tmdb.model;

/**
 * Created by Belez on 2017. 07. 02..
 */

public class ResponseEntity {

    Integer status_code;

    String status_message;

    public ResponseEntity() {
        super();
    }

    public ResponseEntity(Integer status_code, String status_message) {
        this.status_code = status_code;
        this.status_message = status_message;
    }

    public Integer getStatus_code() {
        return status_code;
    }

    public void setStatus_code(Integer status_code) {
        this.status_code = status_code;
    }

    public String getStatus_message() {
        return status_message;
    }

    public void setStatus_message(String status_message) {
        this.status_message = status_message;
    }

}
