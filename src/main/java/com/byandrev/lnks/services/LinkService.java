package com.byandrev.lnks.services;

import com.byandrev.lnks.entities.FolderEntity;
import com.byandrev.lnks.entities.LinkEntity;
import com.byandrev.lnks.entities.UserEntity;

import java.util.List;
import java.util.Optional;

public interface LinkService {

    void create(LinkEntity link);

    Optional<LinkEntity> getById(Long id);

    List<LinkEntity> getAllByFolder(FolderEntity folderEntity);

    void delete(Long id);

}
