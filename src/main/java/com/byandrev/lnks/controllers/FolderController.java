package com.byandrev.lnks.controllers;

import com.byandrev.lnks.dto.FolderDTO;
import com.byandrev.lnks.entities.FolderEntity;
import com.byandrev.lnks.entities.Response;
import com.byandrev.lnks.entities.UserEntity;
import com.byandrev.lnks.security.AuthenticationFacade;
import com.byandrev.lnks.services.FolderService;
import com.byandrev.lnks.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/folders")
public class FolderController {

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @Autowired
    private FolderService folderService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Response> getAll() {
        Authentication authentication = this.authenticationFacade.getAuthentication();
        UserEntity userEntity = userService.getUserByEmail(authentication.getName());
        List<FolderEntity> folders = folderService.getAll(userEntity);

        Response httpResponse = Response.builder()
                .status(HttpStatus.OK.value())
                .msg("all folders")
                .body(folders)
                .build();

        return new ResponseEntity<>(httpResponse, HttpStatusCode.valueOf(httpResponse.getStatus()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getById(@PathVariable Long id) {
        Authentication authentication = this.authenticationFacade.getAuthentication();
        UserEntity user = userService.getUserByEmail(authentication.getName());
        FolderEntity folder = folderService.getById(id, user).orElse(null);

        Response httpResponse = Response.builder()
                .status(HttpStatus.OK.value())
                .msg("folder #"+id)
                .body(folder)
                .build();

        return new ResponseEntity<>(httpResponse, HttpStatusCode.valueOf(httpResponse.getStatus()));
    }

    @PostMapping
    public ResponseEntity<Response> create(@RequestBody FolderDTO folderDTO) {
        Authentication authentication = this.authenticationFacade.getAuthentication();

        try {
            UserEntity userEntity = userService.getUserByEmail(authentication.getName());
            FolderEntity folder = FolderEntity.builder()
                    .name(folderDTO.getName())
                    .description(folderDTO.getDescription())
                    .user(userEntity)
                    .build();

            folderService.create(folder);

            Response httpResponse = Response.builder()
                    .msg("folder created")
                    .status(HttpStatus.CREATED.value())
                    .body(folder)
                    .build();

            return new ResponseEntity<>(httpResponse, HttpStatusCode.valueOf(httpResponse.getStatus()));
        } catch (Exception e) {
            Response httpResponse = Response.builder()
                    .msg("error to create folder")
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .build();

            return new ResponseEntity<>(httpResponse, HttpStatusCode.valueOf(httpResponse.getStatus()));
        }
    }

}
