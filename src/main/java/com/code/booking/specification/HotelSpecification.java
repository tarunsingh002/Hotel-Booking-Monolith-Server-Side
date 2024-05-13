package com.code.booking.specification;

import com.code.booking.entity.Hotel;
import jakarta.persistence.criteria.Expression;
import org.springframework.data.jpa.domain.Specification;

public class HotelSpecification {
    public static Specification<Hotel> nameContains(String name) {
        return (root, query, builder) -> {
            Expression<String> nameLowerCase = builder.lower(root.get("name"));
            return builder.like(nameLowerCase, "%" + name.toLowerCase() + "%");
        };
    }

    public static Specification<Hotel> locationContains(String location) {
        return (root, query, builder) -> {
            Expression<String> locationLowerCase = builder.lower(root.get("location"));
            return builder.like(locationLowerCase, "%" + location.toLowerCase() + "%");
        };
    }

    public static Specification<Hotel> amenitiesContains(String amenity) {
        return (root, query, builder) -> {
            Expression<String> amenitiesLowerCase = builder.lower(root.get("amenities"));
            return builder.like(amenitiesLowerCase, "%" + amenity.toLowerCase() + "%");
        };
    }
    public static Specification<Hotel> priceLessThanOrEqualTo(float price) {
        return (root, query, builder) -> {
            Expression<Float> price1 = root.get("price");
            return builder.lessThanOrEqualTo(price1, price);
        };
    }
    public static Specification<Hotel> priceMoreThanOrEqualTo(float price) {
        return (root, query, builder) -> {
            Expression<Float> price1 = root.get("price");
            return builder.greaterThanOrEqualTo(price1, price);
        };
    }
}
