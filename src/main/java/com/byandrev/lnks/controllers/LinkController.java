package com.byandrev.lnks.controllers;

import com.byandrev.lnks.dto.LinkDTO;
import com.byandrev.lnks.entities.LinkEntity;
import com.byandrev.lnks.entities.Response;
import com.byandrev.lnks.entities.UserEntity;
import com.byandrev.lnks.services.LinkService;
import com.byandrev.lnks.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController()
@RequestMapping("/links")
public class LinkController {

    @Autowired
    private LinkService linkService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Response> getAll(Principal principal) {
        UserEntity userEntity = userService.getUserByEmail(principal.getName());
        List<LinkEntity> links = linkService.getAll(userEntity);

        Response httpResponse = Response.builder()
                .status(HttpStatus.OK.value())
                .msg("all links")
                .body(links)
                .build();

        return new ResponseEntity<>(httpResponse, HttpStatusCode.valueOf(httpResponse.getStatus()));
    }

    @PostMapping
    public ResponseEntity<Response> create(Principal principal, @RequestBody LinkDTO linkDTO) {
        try {
            UserEntity userEntity = userService.getUserByEmail(principal.getName());

            LinkEntity link = LinkEntity.builder()
                    .name(linkDTO.getName())
                    .url(linkDTO.getUrl())
                    .description(linkDTO.getDescription())
                    .user(userEntity)
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
