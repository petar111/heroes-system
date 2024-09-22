package com.springpj.heroesauthorizationserver.constants;

public interface SecurityConstants {
    String JWT_SECRET_KEY = "7817586165658315776978987486471-391568161597189390820941=02592498195";
    long EXPIRATION_TIME = 432_000_000;
    String JWT_ISSUER = "SIUX";
    String JWT_VERIFIER_ERROR = "Token can not be verified";
    String JWT_AUTHORITIES = "authorities";
    String JWT_AUDIENCE = "Audience FIXME";
    String JWT_SUBJECT_DELIMITER = ":";

}
