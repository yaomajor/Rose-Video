package com.javacv.video.vo;

/**
 * @ClassName Success
 * @Description TODO
 * @Author 86133
 * @Date 2020/6/16 10:14
 * @Version 1.0
 **/
public class Success {
    private Boolean success;
    private String message;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Success{" +
                "success=" + success +
                ", message='" + message + '\'' +
                '}';
    }
}
