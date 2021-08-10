package org.exam.frontend.controllers;

import org.exam.backend.services.RankService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class RankController {


    @Autowired
    private RankService rankService;

    @Autowired
    private ItemController itemController;


    public boolean rankItem(String userId, Long itemId){

        String comment = itemController.getComment();

        Long rankId = rankService.rankItem(userId, itemId, null, comment);

        return rankId != null;
    }
}
