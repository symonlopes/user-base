package br.com.symon.userbase.model.api;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class ApiResponse {
    @Builder.Default
    private List<ApiError> errors = new ArrayList<>();
}
