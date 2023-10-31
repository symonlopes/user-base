package br.com.symon.userbase.model.api;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class User {
    private String id;
    private String email;
    private String passwordHash;
}
