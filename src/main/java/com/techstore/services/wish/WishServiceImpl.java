package com.techstore.services.wish;

import com.techstore.components.converter.Converter;
import com.techstore.components.converter.WishConverter;
import com.techstore.dto.CreateWishDto;
import com.techstore.dto.WishDto;
import com.techstore.entities.Product;
import com.techstore.entities.User;
import com.techstore.entities.Wish;
import com.techstore.services.product.ProductServiceImpl;
import com.techstore.services.user.UserRepository;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class WishServiceImpl implements WishService {
    private final WishesRepository wishesRepository = new WishesRepository();
    private final UserRepository userRepository = new UserRepository();

    private final Converter<WishDto, Wish> converter = new WishConverter();

    private final static WishService instance = new WishServiceImpl();
    public static WishService getInstance() { return instance; }

    private WishServiceImpl() { }


    @Override
    public void create(final CreateWishDto dto) {
        Optional<Wish> duplicateWish = wishesRepository.findByProductId(dto.productId);

        if (duplicateWish.isEmpty()) {
            final Product product = ProductServiceImpl.getInstance().findProduct(dto.productId);

            final User user = userRepository.findById(dto.userId)
                    .orElseThrow(() -> new EntityNotFoundException(String.valueOf(dto.userId)));

            Wish newWish = new Wish(user, product);

            try {
                wishesRepository.add(newWish);
            } catch (final RuntimeException exc) {
                exc.printStackTrace();
            }
        }
    }

    @Override
    public void delete(final int wishId) {
        Wish wish = wishesRepository.findById(wishId)
                .orElseThrow(() -> new EntityNotFoundException(String.valueOf(wishId)));
        try {
            wishesRepository.remove(wish);
        } catch (final RuntimeException exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public List<WishDto> userWishes(final int userId) {
        return wishesRepository.findByUserId(userId).stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }
}
