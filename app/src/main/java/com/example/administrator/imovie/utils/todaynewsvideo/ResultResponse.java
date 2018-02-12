package com.example.administrator.imovie.utils.todaynewsvideo;

/**
 * Created by huangweiliang on 2018/02/12.
 */

public class ResultResponse<T> {

    public String has_more;
    public String message;
    public T data;

    public ResultResponse(String more, String _message, T result) {
        has_more = more;
        message = _message;
        data = result;
    }
}
