package com.byandrev.lnks.controllers;

import com.byandrev.lnks.dto.LinkDTO;
import com.byandrev.lnks.entities.FolderEntity;
import com.byandrev.lnks.entities.LinkEntity;
import com.byandrev.lnks.entities.Response;
import com.byandrev.lnks.entities.UserEntity;
import com.byandrev.lnks.services.FolderService;
import com.byandrev.lnks.services.LinkService;
import com.byandrev.lnks.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/links")
public class LinkController {

    @Autowired
    private LinkService linkService;

    @Autowired
    private UserService userService;

    @Autowired
    private FolderService folderService;

    @GetMapping
    public ResponseEntity<Response> getAll(Principal principal, @RequestParam(name = "folder_id") Long folderId) {
        UserEntity user = userService.getUserByEmail(principal.getName());
        Optional<FolderEntity> folder = folderService.getById(folderId, user);

        if (folder.isPresent()) {
            List<LinkEntity> links = linkService.getAllByFolder(folder.get());

            Response httpResponse = Response.builder()
                    .status(HttpStatus.OK.value())
                    .msg("all links of folder #"+folder.get().getId())
                    .body(links)
                    .build();

            return new ResponseEntity<>(httpResponse, HttpStatusCode.valueOf(httpResponse.getStatus()));
        }

        Response httpResponse = Response.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .msg("folder no exist")
                .build();

        return new ResponseEntity<>(httpResponse, HttpStatusCode.valueOf(httpResponse.getStatus()));
    }

    @PostMapping
    public ResponseEntity<Response> create(Principal principal, @RequestBody LinkDTO linkDTO) {
        try {
            UserEntity user = userService.getUserByEmail(principal.getName());
            FolderEntity folder = folderService.getById(linkDTO.getFolderId(), user).orElse(null);

            LinkEntity link = LinkEntity.builder()
                    .name(linkDTO.getName())
                    .url(linkDTO.getUrl())
                    .description(linkDTO.getDescription())
                    .folder(folder)
                    .build();

            linkService.create(link);

            Response httpResponse = Response.builder()
                    .msg("link created")
                    .status(HttpStatus.CREATED.value())
                    .body(link)
                    .build();

            return new ResponseEntity<>(httpResponse, HttpStatusCode.valueOf(httpResponse.getStatus()));
        } catch (Exception e) {
            Response httpResponse = Response.builder()
                    .msg("error to create link")
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .build();

            return new ResponseEntity<>(httpResponse, HttpStatusCode.valueOf(httpResponse.getStatus()));
        }
    }


}
