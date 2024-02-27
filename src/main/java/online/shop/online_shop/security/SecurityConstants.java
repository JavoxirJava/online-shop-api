package online.shop.online_shop.security;

public class SecurityConstants {
    public static final String[] WHITE_LIST = {
            "/login",
            "/register",
            "/category",
            "/category/**",
            "/product",
            "/api-docs/**",
            "/swagger-ui/**",
            "/v2/api-docs",
            "/swagger-ui.html",
            "/swagger-ui/index.html",
            "/v3/api-docs",
            "/v3/api-docs/swagger-config",
            "/swagger-resources/**",
            "/webjars/**",
            "/**"
    };
}
