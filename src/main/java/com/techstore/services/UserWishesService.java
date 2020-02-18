package com.techstore.services;

import com.techstore.entities.WishedItem;
import com.techstore.repositories.Repository;
import com.techstore.repositories.WishesRepository;
import com.techstore.specifications.SqlSpecification;
import com.techstore.specifications.wishes.WishesSpecificationByUserHashedID;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserWishesService {
    private Repository<WishedItem> wishesRepository;
    private final static Logger LOG = Logger.getLogger(UserWishesService.class.getName());

    private static UserWishesService instance = new UserWishesService(new WishesRepository());

    public static UserWishesService getInstance() {
        return instance;
    }

    private UserWishesService(final Repository<WishedItem> wishesRepository) {
        this.wishesRepository = wishesRepository;
    }

    private void addWish(final String userId, final String itemId) {
        WishedItem wishedItem = new WishedItem();
        wishedItem.setUserId(userId);
        wishedItem.setId(itemId);
        try {
            wishesRepository.add(wishedItem);
        } catch (final SQLException exc) {
            exc.printStackTrace();
            LOG.log(Level.ALL, exc.getMessage());
            throw new RuntimeException("SQL Problems");
        }
    }

    private void removeWish(final String hashedUserId, final String wishedItemId) {
        WishedItem wishedItem = new WishedItem();
        wishedItem.setId(wishedItemId);
        wishedItem.setUserId(UsersService.getInstance().loadUserByHashedId(hashedUserId).getID());
        try {
            wishesRepository.remove(wishedItem);
        } catch (final SQLException exc) {
            exc.printStackTrace();
            LOG.log(Level.ALL, exc.getMessage());
            throw new RuntimeException("SQL Problems");
        }
    }

    private List<WishedItem> wishesList(final SqlSpecification spec) {
        try {
            return wishesRepository.query(spec);
        } catch (final SQLException exc) {
            exc.printStackTrace();
            LOG.log(Level.ALL, exc.getMessage());
            throw new RuntimeException("SQL Problems");
        }
    }

    public List<WishedItem> getUserWishes(final String userHashedId) {
        return wishesList(new WishesSpecificationByUserHashedID(userHashedId));
    }

    public void addUserWish(final String hashedUserId, final String itemId) throws RuntimeException {
        if (getUserWishes(hashedUserId).stream().noneMatch((wish) -> wish.getId().equals(itemId)))
            addWish(UsersService.getInstance().loadUserByHashedId(hashedUserId).getID(), itemId);
    }

    public void removeUserWish(final String hashedUserId, final String itemId) {
        removeWish(hashedUserId, itemId);
    }
}
