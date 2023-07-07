package com.byandrev.lnks.services;

import com.byandrev.lnks.entities.LinkEntity;
import com.byandrev.lnks.entities.UserEntity;

import java.util.List;
import java.util.Optional;

public interface LinkService {

    void create(LinkEntity link);

    Optional<LinkEntity> getById(Long id);

    List<LinkEntity> getAll(UserEntity userEntity);

    void delete(Long id);

}
