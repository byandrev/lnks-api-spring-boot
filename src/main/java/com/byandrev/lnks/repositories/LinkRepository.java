package com.byandrev.lnks.repositories;

import com.byandrev.lnks.entities.FolderEntity;
import com.byandrev.lnks.entities.LinkEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkRepository extends CrudRepository<LinkEntity, Long> {

    Iterable<LinkEntity> findAllByFolder(FolderEntity folder);

}
