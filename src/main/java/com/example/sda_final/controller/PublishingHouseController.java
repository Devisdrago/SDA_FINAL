package com.example.sda_final.controller;

import com.example.sda_final.entity.PublishingHouseEntity;
import com.example.sda_final.repository.PublishingHouseRepository;
import com.example.sda_final.serivce.PublishingHouseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class PublishingHouseController {

    private final PublishingHouseService publishingHouseService;
    private final PublishingHouseRepository publishingHouseRepository;

    public PublishingHouseController(PublishingHouseService publishingHouseService, PublishingHouseRepository publishingHouseRepository) {
        this.publishingHouseService = publishingHouseService;

        this.publishingHouseRepository = publishingHouseRepository;
    }

    @PostMapping("/addPublishedHouse")
    public String addPublishedHouse(@ModelAttribute PublishingHouseEntity publishingHouseEntity){
        System.out.println(" published request : " + publishingHouseEntity);
        PublishingHouseEntity addHouse = publishingHouseService.addPublishedHouse(publishingHouseEntity.getName() , publishingHouseEntity.getDescription());
        return addHouse != null ? "addPublishingHouse" : "redirect:error_page" ;
    }

    @RequestMapping("/houses/list")
    public String getAllPublishedHouses(Model model) {
        List<PublishingHouseEntity> publishingHouseEntityList = publishingHouseService.getAllPublishedHouses();
        model.addAttribute("publishedRequest", publishingHouseEntityList);
        return publishingHouseEntityList != null ? "publishingHouseList" : "redirect:error_page";
    }

    @GetMapping("/houses/addHouse")
    public String addHousePage(Model model){
        model.addAttribute("houseRequest", new PublishingHouseEntity());
        return "addPublishingHouse";
    }


    @GetMapping("/houses/list/delete/{id}")
    public String deleteHouse(@PathVariable Long id,Model model){
        publishingHouseRepository.deleteById(id);
        return getAllPublishedHouses(model);
    }

    @PostMapping("/houses/list/update/{id}")
    public String updateHouse (@PathVariable  Long id , PublishingHouseEntity publishingHouseEntity , Model model){
        model.addAttribute("updateHouseRequest" , new PublishingHouseEntity());
        publishingHouseService.updateHouse(id,publishingHouseEntity.getName() , publishingHouseEntity.getDescription());
        return getAllPublishedHouses(model);
    }


}
