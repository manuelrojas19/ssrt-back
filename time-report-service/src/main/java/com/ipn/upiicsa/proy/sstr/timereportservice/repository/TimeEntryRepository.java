package com.ipn.upiicsa.proy.sstr.timereportservice.repository;

import com.ipn.upiicsa.proy.sstr.timereportservice.entity.TimeEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TimeEntryRepository extends MongoRepository<TimeEntry, ObjectId> {
}
