package com.techstore.specifications.parameters;

import com.techstore.specifications.SqlSpecification;

public class ParameterSpecificationByItemID implements SqlSpecification {
    private String itemId;

    public ParameterSpecificationByItemID(final String itemId) {
        this.itemId = itemId;
    }

    @Override
    public String toSql() {
        return String.format("SELECT cdid.item_id, cdid.category_parameter_ID, cdid.id, cdid.value, cd.searchable, cd.changeable, cd.parameter_name, cd.parameter_symbol " +
                "FROM items i " +
                "JOIN category_parameters_item_values cdid ON i.id=cdid.item_id " +
                "JOIN category_parameters cd ON cdid.category_parameter_id=cd.id " +
                "WHERE cdid.item_id=%s AND cd.changeable=false " +
                "ORDER BY cdid.id", itemId);
    }
}
