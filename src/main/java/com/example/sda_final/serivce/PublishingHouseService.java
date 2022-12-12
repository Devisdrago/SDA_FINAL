package com.example.sda_final.serivce;

import com.example.sda_final.entity.PublishingHouseEntity;
import com.example.sda_final.repository.PublishingHouseRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class PublishingHouseService {

    private final PublishingHouseRepository publishingHouseRepository;

    public PublishingHouseService(PublishingHouseRepository publishingHouseRepository) {
        this.publishingHouseRepository = publishingHouseRepository;
    }

    public PublishingHouseEntity addPublishedHouse(String name, String description) {

        if (name == null) {
            return null;
        } else if (publishingHouseRepository.findByName(name).isPresent()) {
            System.out.println("Published house already exist");
            return null;
        }
        PublishingHouseEntity publishingHouseEntity = new PublishingHouseEntity();
        publishingHouseEntity.setName(name);
        publishingHouseEntity.setDescription(description);

        return publishingHouseRepository.save(publishingHouseEntity);
    }

    public List<PublishingHouseEntity> getAllPublishedHouses() {

        return new ArrayList<>(publishingHouseRepository.findAll());
    }

    public void updateHouse(Long id, String name, String description) {

        if (publishingHouseRepository.findById(id).isPresent()) {
            publishingHouseRepository.findById(id).get().setName(name);
            publishingHouseRepository.findById(id).get().setDescription(description);
        }
    }


}
