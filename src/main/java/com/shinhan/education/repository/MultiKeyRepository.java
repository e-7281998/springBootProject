package com.shinhan.education.repository;

import org.springframework.data.repository.CrudRepository;

import com.shinhan.education.vo2.MultiKeyA;
import com.shinhan.education.vo2.MultiKeyAUsing;

public interface MultiKeyRepository extends CrudRepository<MultiKeyAUsing, MultiKeyA>{

}
