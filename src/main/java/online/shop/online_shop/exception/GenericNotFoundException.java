package online.shop.online_shop.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GenericNotFoundException extends RuntimeException {
    private String message;
    private Integer statusCode;
}
