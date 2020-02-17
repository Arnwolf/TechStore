package com.techstore.specifications.items;

import com.techstore.specifications.SqlSpecification;

public class ItemSpecificationByParamValue extends ItemSpecification implements SqlSpecification {
    private String ParamID;
    private String ParamValue;

    public ItemSpecificationByParamValue(final String ParamID, final String ParamValue) {
        this.ParamID = ParamID;
        this.ParamValue = ParamValue;
    }

    @Override
    public String toSql() {
        return String.format("SELECT cdid.category_parameter_id, i.id, i.manufacturer, i.new, i.availability, i.name, i.price, i.main_photo, c.name AS Category, i.discount, i.category_id " +
                "FROM items i " +
                "JOIN categories c ON i.category_id=c.id " +
                "JOIN item_details id ON i.id=id.item_id " +
                "JOIN category_parameters_item_values cdid ON i.id=cdid.item_id " +
                "WHERE cdid.category_parameter_id=%s AND cdid.value='%s'", ParamID, ParamValue);
    }
}
