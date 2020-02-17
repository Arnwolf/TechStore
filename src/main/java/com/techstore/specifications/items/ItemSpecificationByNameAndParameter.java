package com.techstore.specifications.items;

import com.techstore.specifications.SqlSpecification;

public class ItemSpecificationByNameAndParameter implements SqlSpecification {
    private String itemName;
    private String changeableParamValue;

    public ItemSpecificationByNameAndParameter(final String itemName, final String changeableParamValue) {
        this.changeableParamValue = changeableParamValue;
        this.itemName = itemName;
    }

    @Override
    public String toSql() {
        return String.format("SELECT i.*, cpiv.*, c.name AS Category " +
                "FROM items i " +
                "JOIN categories c on i.category_id = c.id " +
                "JOIN category_parameters_item_values cpiv on i.id = cpiv.item_id " +
                "WHERE i.name='%s' AND cpiv.value='%s'", itemName, changeableParamValue);
    }
}
