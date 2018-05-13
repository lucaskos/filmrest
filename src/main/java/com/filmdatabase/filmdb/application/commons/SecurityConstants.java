package com.filmdatabase.filmdb.application.commons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SecurityConstants {
    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users/sign-up";
    public static final String ROLES = "PersonRolesKeys";


    public static final String ALLOW_CREDENTIALS = "Access-Control-Allow-Credentials";
    public static final String ALLOW_CREDENTIALS_VALUE = "true";
    public static final String MAX_AGE = "Access-Control-Max-Age";
    public static final String MAX_AGE_TIME = "3600";
    public static final String METHODS_ALLOWED = "Access-Control-Allow-Methods";
    public static final String HEADERS_ALLOWED = "Access-Control-Allow-Headers";
    public static final String EXPOSED_HEADERS = "Access-Control-Expose-Headers";

    public static final String AUTHORIZATION = "Authorization";
    public static final String ORIGIN = "Origin";

    private static List<String> headersList;

    public static String getGeneratedHeaders() {

        if(headersList == null || headersList.isEmpty()) {
            headersList = generateHeadersList(AUTHORIZATION, ORIGIN);
        }

        StringBuilder sb = new StringBuilder();
        for (String str : headersList) {
            if (headersList.get(headersList.size()-1) != str) {
                sb.append(str + ", ");
            } else {
                sb.append(str);
            }
        }
        return sb.toString();
    }

    private static List<String> generateHeadersList(String... string) {
        if (string != null) {
            return Arrays.stream(string).collect(Collectors.toList());
        }
        return null;
    }
}