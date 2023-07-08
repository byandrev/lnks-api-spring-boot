package com.byandrev.lnks.services;

import com.byandrev.lnks.entities.FolderEntity;
import com.byandrev.lnks.entities.UserEntity;

import java.util.List;
import java.util.Optional;

public interface FolderService {

    void create(FolderEntity folder);

    Optional<FolderEntity> getById(Long id, UserEntity user);

    List<FolderEntity> getAll(UserEntity userEntity);

    void delete(Long id);

}
