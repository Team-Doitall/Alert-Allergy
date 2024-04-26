package edu.famu.alertallergy.util;

public record ApiResponseFormat<T extends Object>(boolean success, String message, Object data, Object error) {

}
