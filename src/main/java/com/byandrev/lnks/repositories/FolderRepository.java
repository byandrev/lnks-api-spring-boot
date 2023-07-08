package com.byandrev.lnks.repositories;

import com.byandrev.lnks.entities.FolderEntity;
import com.byandrev.lnks.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FolderRepository extends CrudRepository<FolderEntity, Long> {

    Optional<FolderEntity> findByIdAndUser(Long id, UserEntity user);

    Iterable<FolderEntity> findAllByUser(UserEntity user);

}
