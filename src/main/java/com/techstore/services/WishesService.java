package com.techstore.services;

import com.techstore.entities.User;
import com.techstore.entities.Wish;
import com.techstore.repositories.WishesRepository;
import java.util.List;


public class WishesService {
    private WishesRepository wishesRepository = new WishesRepository();

    private static WishesService instance = new WishesService();
    public static WishesService getInstance() { return instance; }

    private WishesService() { }

    public void addWish(final Wish wish) {
        if (wishesRepository.findByItemId(wish.getProduct().getId()).isEmpty())
            wishesRepository.add(wish);
    }

    public void removeWish(final Wish wish) { wishesRepository.remove(wish); }

    public List<Wish> getUserWishes(final String userHashedId) {
        User user = UsersService.getInstance().loadUserByHashedId(userHashedId);
        return wishesRepository.findByUserId(user.getID());
    }
}
