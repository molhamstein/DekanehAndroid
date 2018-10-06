package brain_socket.com.dekaneh.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;



public class Error implements Serializable {

    @SerializedName("error")
    @Expose
    private
    ErrorDetails errorDetails;

    public ErrorDetails getErrorDetails() {
        return errorDetails;
    }

    public String getMessage() {
        return errorDetails.getMessage();
    }

    class ErrorDetails implements Serializable {

        @SerializedName("statusCode")
        @Expose
        int statusCode;
        @SerializedName("name")
        @Expose
        String name;
        @SerializedName("message")
        @Expose
        String message;
        @SerializedName("code")
        @Expose
        String code;

        public String getMessage() {
            return message;
        }
    }

}
