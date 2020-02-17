package com.techstore.services;

import com.techstore.entities.DetailedItem;
import com.techstore.entities.Review;
import com.techstore.entities.User;
import com.techstore.repositories.*;
import com.techstore.specifications.SqlSpecification;
import com.techstore.specifications.items.ItemSpecificationByID;
import com.techstore.specifications.items.ItemSpecificationByNameAndParameter;

import javax.validation.constraints.NotNull;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DetailedItemsService {
    private Repository<DetailedItem> detailedItemRepository;
    private Repository<Review> reviewRepository;
    private final static Logger LOG = Logger.getLogger(DetailedItemsService.class.getName());

    private static DetailedItemsService instance;

    public static void init() {
        instance = new DetailedItemsService(new DetailedItemRepository(), new ItemReviewRepository());
    }

    public static DetailedItemsService getInstance() {
        return instance;
    }

    private DetailedItemsService(final Repository<DetailedItem> detailedItemRepository,
                                final Repository<Review> reviewRepository) {
        this.detailedItemRepository = detailedItemRepository;
        this.reviewRepository = reviewRepository;
    }

    private void addReview(final Review review) {
        try {
            reviewRepository.add(review);
        } catch (final SQLException exc) {
            exc.printStackTrace();
            LOG.log(Level.ALL, exc.getMessage());
            throw new RuntimeException("SQL Problems");
        }
    }

    public void addReview(final String itemId, final String userHashedId,
                           final int score, final String comment, final LocalDateTime date) {
        Review newReview = new Review();
        newReview.setItemID(itemId);
        newReview.setRating(score);
        newReview.setDatetime(date);
        newReview.setDescription(comment);

        User user = UsersService.getInstance().loadUserByHashedId(userHashedId);

        newReview.setUserID(user.getID());
        newReview.setUserName(user.getName());
        addReview(newReview);
    }

    private List<DetailedItem> getDetailedItem(final SqlSpecification spec) {
        try {
            return detailedItemRepository.query(spec);
        } catch (final SQLException exc) {
            exc.printStackTrace();
            LOG.log(Level.ALL, exc.getMessage());
            throw new RuntimeException("SQL Problems");
        }
    }

    public DetailedItem getSearchedDetailedItem(@NotNull final Map<String, String> paramMap) {
        final String itemId = paramMap.get("ItemID");
        final String itemName = paramMap.get("name");
        final String itemChangeableParameter = paramMap.get("parameter");

        if (!itemId.isEmpty())
            return getDetailedItem(new ItemSpecificationByID(itemId)).get(0);
        else if (!itemName.isEmpty() && !itemChangeableParameter.isEmpty())
            return getDetailedItem(new ItemSpecificationByNameAndParameter(itemName, itemChangeableParameter)).get(0);
        else
            return new DetailedItem();
    }
}
