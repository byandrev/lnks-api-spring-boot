package com.byandrev.lnks.repositories;

import com.byandrev.lnks.entities.LinkEntity;
import com.byandrev.lnks.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkRepository extends CrudRepository<LinkEntity, Long> {

    Iterable<LinkEntity> findAllByUser(UserEntity user);

}