package com.techstore.services;

import com.techstore.entities.Item;
import com.techstore.repositories.*;
import com.techstore.specifications.items.*;
import com.techstore.specifications.SqlSpecification;

import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ItemsService {
    private Repository<Item> itemsRepository;

    private final static Logger LOG = Logger.getLogger(ItemsService.class.getName());
    private static ItemsService instance = new ItemsService(new ItemRepository());
    public static ItemsService getInstance() {
        return instance;
    }

    private ItemsService(final Repository<Item> itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    private List<Item> getItems(SqlSpecification spec) {
        try {
            return itemsRepository.query(spec);
        } catch (final SQLException exc) {
            exc.printStackTrace();
            LOG.log(Level.ALL, exc.getMessage());
            throw new RuntimeException("SQL Problems");
        }
    }

    public List<Item> getMainPageItems() {
        return getItems(new ItemSpecificationByPopular());
    }

    public List<Item> getCategoryItems(final String categoryID) {
        return getItems(new ItemSpecificationByCategoryID(categoryID));
    }

    public Item getItem(final String itemId) {
        List<Item> items = getItems(Arrays.asList(itemId));
        return items.isEmpty() ? null : items.get(0);
    }

    public List<Item> getItems(final Collection<String> itemsIds) {
        return getItems(new ItemSpecificationByID(itemsIds));
    }

    public List<Item> getSearchedItems(final Map<String, String> paramMap) {
        final String categoryID = paramMap.get("categoryID");
        final String paramID = paramMap.get("ParamID");
        final String paramValue = paramMap.get("ParamValue");
        final String searchValue = paramMap.get("search");

        if (!categoryID.isEmpty())
            return getItems(new ItemSpecificationByCategoryID(categoryID));
        else if (!paramID.isEmpty() && !paramValue.isEmpty())
            return getItems(new ItemSpecificationByParamValue(paramID, paramValue));
        else if (!searchValue.isEmpty())
            return getItems(new ItemSpecificationBySearchName(searchValue));
        else
            return new ArrayList<>();
    }
}
