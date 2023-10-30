package br.com.symon.userbase.model.api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiError {
    private int code;
    private String message;
    private String details;
}
