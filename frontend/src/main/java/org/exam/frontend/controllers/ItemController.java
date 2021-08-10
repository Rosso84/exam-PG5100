package org.exam.frontend.controllers;

import org.exam.backend.entities.Item;
import org.exam.backend.entities.Rank;
import org.exam.backend.services.ItemService;
import org.exam.backend.services.RankService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ApplicationScoped
public class ItemController implements Serializable {

    @Autowired
    private ItemService itemService;

    @Autowired
    private RankService rankService;

    private Item selectedItem;

    private String comment;

    private boolean ranked;

    private List<Item> allItemsList;


    public List<Item> getAllItemsList() {
       allItemsList = itemService.getALLItemOrderByCategory(true);
        return allItemsList;
    }


    public Item getSelectedItem() {
        return selectedItem;
    }


    public void setSelectedItem(Item selectedItem) {
        this.selectedItem = selectedItem;
    }


    public String getComment() {
        return comment;
    }


    public void setComment(String comment) {
        this.comment = comment;
    }


    public void setToRanked( boolean isRanked) {
        this.ranked = isRanked;
    }


    public boolean isItemRanked() {
        return this.ranked;
    }


    public void checkItemIsRanked(String userID){
        System.out.println("usersEmail: " +  userID);
        boolean isRanked = rankService.userHasRankedItem( userID , getSelectedItem().getId() );

        setToRanked( isRanked );
    }


    public String toDetailPageAuthenticated(Item item, String userId) {
        setSelectedItem(item);
        checkItemIsRanked(userId);

        return "/itemDetails.jsf&faces-redirect=true";
    }

    public String toDetailPageAnonymous(Item item) {
        setSelectedItem(item);
        return "/itemDetails.jsf&faces-redirect=true";
    }


    public double getAvgVotes(Item item) {
        double avg = itemService.getItemsAverageRank(item.getId());
        avg = Math.round(avg * 100) / 100.0d; //to remove decimals

        return avg;
    }


    public int getNumberOfUsersVoted(Item item) {
        return item.getRankings().size();
    }


    public String giveItemScore(String userId, Long itemId, Integer score) {
        Long rankId = rankService.rankItem( userId, itemId, score, null);

        setSelectedItem( itemService.getItem( itemId, true ));

        setToRanked( true );
        return "/itemDetails.jsf&faces-redirect=true";
    }

    public void giveItemComment(){

    }






}