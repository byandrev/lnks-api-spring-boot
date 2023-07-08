package com.byandrev.lnks.services;

import com.byandrev.lnks.entities.FolderEntity;
import com.byandrev.lnks.entities.LinkEntity;
import com.byandrev.lnks.repositories.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LinkServiceImpl implements LinkService {

    @Autowired
    private LinkRepository linkRepository;

    @Override
    public void create(LinkEntity link) {
        linkRepository.save(link);
    }

    @Override
    public Optional<LinkEntity> getById(Long id) {
        return linkRepository.findById(id);
    }

    @Override
    public List<LinkEntity> getAllByFolder(FolderEntity folder) {
        return (List<LinkEntity>) linkRepository.findAllByFolder(folder);
    }

    @Override
    public void delete(Long id) {
        linkRepository.deleteById(id);
    }

}
