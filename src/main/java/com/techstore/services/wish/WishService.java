package com.techstore.services.wish;

import com.techstore.dto.CreateWishDto;
import com.techstore.dto.WishDto;
import java.util.List;


public interface WishService {
    void create(final CreateWishDto dto);
    void delete(final int wishId);

    List<WishDto> userWishes(final int userId);
}
