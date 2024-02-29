package edu.famu.alertallergy.util;

public record ApiResponse<T>(boolean success, String message, Object data, Object error) {}
