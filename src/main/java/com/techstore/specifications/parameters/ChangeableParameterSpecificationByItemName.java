package com.techstore.specifications.parameters;

import com.techstore.specifications.SqlSpecification;

public class ChangeableParameterSpecificationByItemName implements SqlSpecification {
    private String itemName;

    public ChangeableParameterSpecificationByItemName(final String itemName) {
        this.itemName = itemName;
    }

    @Override
    public String toSql() {
        return String.format("SELECT cpiv.*, cp.* " +
                "FROM items i " +
                "JOIN category_parameters_item_values cpiv on i.id = cpiv.item_id " +
                "JOIN category_parameters cp on cpiv.category_parameter_id = cp.id " +
                "WHERE cp.changeable=true AND i.name='%s'", itemName);
    }
}
