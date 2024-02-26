package online.shop.online_shop.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class CommonUtils {

    public static Pageable getPageable(int page, int size) throws Exception {
        if (page < 0) {
            throw new Exception("Page 0 dan kichik bo'lmasin");
        }
        if (size > Integer.parseInt(AppConstant.MAX_SIZE)) {
            throw new Exception("Size 100 dan kichik bo'lsin");
        }
        return PageRequest.of(page, size);
    }
}
