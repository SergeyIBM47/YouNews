package com.template.web.rest;

import com.template.domain.model.Tags;
import com.template.service.TagService;
import com.template.web.form.TagsForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.CompletableFuture.supplyAsync;

/**
 * Created by: Sergey Volokh
 * Date: 6/3/2016
 * Time: 8:24 PM
 * Project: springmvcs
 */
@RestController
@RequestMapping(value = "/rest/tags", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestTagController {

    @Autowired
    private TagService tagService;

    @RequestMapping(method = RequestMethod.GET)
    public CompletableFuture<ResponseEntity<List<Tags>>> getAllTags(){
        return supplyAsync(() -> new ResponseEntity<>(tagService.findAll(new Sort(Sort.Direction.ASC, "name")), HttpStatus.OK));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public CompletableFuture<ResponseEntity<Tags>> updateTag(@RequestBody @Valid TagsForm form){
        return supplyAsync(() -> new ResponseEntity<>(tagService.update(form.getEntity()), HttpStatus.OK));
    }

    @RequestMapping(method = RequestMethod.POST)
    public CompletableFuture<ResponseEntity<Tags>> addTag(@RequestBody @Valid TagsForm form){
        return supplyAsync(()-> new ResponseEntity<>(tagService.insert(form.getEntity()), HttpStatus.OK));
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public CompletableFuture<ResponseEntity<String>> deleteTag(@RequestBody @Valid TagsForm form){
        return supplyAsync(() -> {
            boolean tagsDeleted = tagService.delete(form.getEntity().getId());
            if(tagsDeleted){
                return new ResponseEntity<>("Tag " + form + " deleted successfully.", HttpStatus.OK);
            }else {
                return new ResponseEntity<>("Tag " + form + " deleted error.", HttpStatus.OK);
            }
        });
    }

}
