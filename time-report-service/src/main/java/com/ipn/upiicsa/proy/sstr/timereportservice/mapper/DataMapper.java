package com.ipn.upiicsa.proy.sstr.timereportservice.mapper;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class DataMapper {

    public String asString(ObjectId id) {
        if (Objects.nonNull(id))
            return id.toString();
        else
            return null;
    }

    public ObjectId asObjectId(String id) {
        if (Objects.nonNull(id))
            return new ObjectId(id);
        else
            return null;
    }
}
