package edu.famu.alertallergy.util;

public record ApiResponse(boolean success, String message, Object data, Object error) {}
