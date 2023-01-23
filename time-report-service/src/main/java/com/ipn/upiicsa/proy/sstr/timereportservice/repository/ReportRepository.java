package com.ipn.upiicsa.proy.sstr.timereportservice.repository;

import com.ipn.upiicsa.proy.sstr.timereportservice.entity.Report;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReportRepository extends MongoRepository<Report, ObjectId> {
    List<Report> findAllByOwner(String owner);
}
