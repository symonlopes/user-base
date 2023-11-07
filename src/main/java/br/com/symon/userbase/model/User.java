package br.com.symon.userbase.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

public record User (String id, String email,String passwordHash){

}
