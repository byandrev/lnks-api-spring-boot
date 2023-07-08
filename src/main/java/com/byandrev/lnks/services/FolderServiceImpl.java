package com.byandrev.lnks.services;

import com.byandrev.lnks.entities.FolderEntity;
import com.byandrev.lnks.entities.UserEntity;
import com.byandrev.lnks.repositories.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FolderServiceImpl implements FolderService {

    @Autowired
    private FolderRepository folderRepository;

    @Override
    public void create(FolderEntity folder) {
        folderRepository.save(folder);
    }

    @Override
    public Optional<FolderEntity> getById(Long id, UserEntity user) {
        return folderRepository.findByIdAndUser(id, user);
    }

    @Override
    public List<FolderEntity> getAll(UserEntity userEntity) {
        return (List<FolderEntity>) folderRepository.findAllByUser(userEntity);
    }

    @Override
    public void delete(Long id) {
        folderRepository.deleteById(id);
    }

}
