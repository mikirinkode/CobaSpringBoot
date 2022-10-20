package com.mikirinkode.architecture.repository;

import com.mikirinkode.architecture.model.LoggingModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoggingRepository extends MongoRepository<LoggingModel, String> {

}
