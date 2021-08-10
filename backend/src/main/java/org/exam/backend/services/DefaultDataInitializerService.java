package org.exam.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.function.Supplier;

@Service
public class DefaultDataInitializerService {

    @Autowired
    private RankService rankService;

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;


    @PostConstruct
    public void initialize() {

        String userId1 = "foo@bar.com";
        String userId2 = "foo2@bar2.com";
        String userId3 = "foo3@bar3.com";

        String password = "123456";


        attempt(() -> userService.createUser(userId1, "Jimmy", "Bar", "Miller",
                "OclahomaStreet 22nd", "2020", password));

        attempt(() -> userService.createUser(userId2, "Sherman", "Bar2", "Miller2",
                "OclahomaStreet 22nd", "2020", password));

        attempt(() -> userService.createUser(userId3, "Waldo", "Bar3", "Miller3",
                "OclahomaStreet 22nd", "2020", password));


        String ctgTour = "tour";

        String titleTour1 = "Rafting Tour In Alanya";
        String descrTour1 = "Marvel at the natural beauty of Köprülü Canyon\n" +
                "Sail through the wild water and white foam of the river\n" +
                "have tasty Turkish meal outdoors amongst the evergreen trees";

        String titleTour2 = "Archaeology Tours in Antalya";
        String descrTour2 = "Traveling by hire car to Pamukkale’s calcium pools and ancient Hierapolis from Antalya means a long drive." +
                " Skip the effort and go with a guide for ease and added insight on this small-group tour." +
                "Explore and learn about the ruins of the Greco-Roman spa town of Hierapolis.";


        String titleTour3 = "The Blue Lagoon tour";
        String descrTour3 = "The Blue Lagoon is one of the most visited and unique attractions in Iceland. The lagoon, " +
                                "which gets it’s name from the blue colour of the water, is located in the middle of a black lava field";

        String titleTour4 = "West Iceland – PRIVATE TOUR";
        String descrTour4 = "Often overlooked but never disappointing West Iceland offers everything you’d like to see on an Icelandic day trip: glaciers, waterfalls and geysers." +
                " West Iceland is also often dubbed the saga land because it’s the scene of many of Iceland’s most beloved sagas";

        String titleTour5 = "PENINSULA PRIVATE TOUR";
        String descrTour5 = "The Snæfellsnes is a peninsula situated to the west of Borgarfjörður, in western Iceland. " +
                "It has been named Iceland in Miniature, because many national sights can be found in the area.";

        String titleTour6 = "The Black beach tour";
        String descrTour6 = "From Reykjavík we drive over the plateau of Hellisheiði, past the greenhouse village of Hveragerði," +
                " to the village of Hvolsvöllur Along the way we visit two magnificent waterfalls: Seljalandsfoss a tall thin beauty of 73 meters";

        String titleTour7 = "Day Trip to Chambord & Chenonceau";
        String descrTour7 = "Discover the most important and picturesque castles in the Loire Valley with a registered guide, and get the chance to taste some delightful local wines.";

        //Create Items Tours
        Long itemTour1 = attempt(() -> createItem(ctgTour, titleTour1, descrTour1));
        Long itemTour2 = attempt(() -> createItem(ctgTour, titleTour2, descrTour2));
        Long itemTour3 = attempt(() -> createItem(ctgTour, titleTour3, descrTour3));
        Long itemTour4 = attempt(() -> createItem(ctgTour, titleTour4, descrTour4));
        Long itemTour5 = attempt(() -> createItem(ctgTour, titleTour5, descrTour5));
        Long itemTour6 = attempt(() -> createItem(ctgTour, titleTour6, descrTour6));
        Long itemTour7 = attempt(() -> createItem(ctgTour, titleTour7, descrTour7));

        //Rank Tours
        Long rankId1 = attempt(() -> rankService.rankItem(userId1, itemTour1, 4, "Very fun! Most recommended!"));
        Long rankId2 = attempt(() -> rankService.rankItem(userId2, itemTour1, 5, "I very Much agree! This was best"));
        Long rankId3 = attempt(() -> rankService.rankItem(userId3, itemTour1, 3, "I have had better times.. but it wasn´t bad."));

        Long rankId4 = attempt(() -> rankService.rankItem(userId1, itemTour2, 4, "Very fun! Most recommended!"));
        Long rankId5 = attempt(() -> rankService.rankItem(userId2, itemTour2, 2, "I fell asleep. The tourguide was talking like he was high on something"));
        Long rankId6 = attempt(() -> rankService.rankItem(userId3, itemTour2, 1, "I have had better times.."));

        Long rankId7 = attempt(() -> rankService.rankItem(userId1, itemTour3, 4, "Very fun! Most recommended!"));
        Long rankId8 = attempt(() -> rankService.rankItem(userId2, itemTour3, 5, "I very Much agree! This was best"));
        Long rankId9 = attempt(() -> rankService.rankItem(userId3, itemTour3, 3, "I have had better times.. but it wasn´t bad."));

        Long rankId10 = attempt(() -> rankService.rankItem(userId1, itemTour4, 4, "Very fun! Most recommended!"));
        Long rankId11 = attempt(() -> rankService.rankItem(userId2, itemTour4, 2, "I fell asleep. The tourguide was talking like he was high on something"));
        Long rankId12 = attempt(() -> rankService.rankItem(userId3, itemTour4, 1, "I have had better times.."));

        Long rankId13 = attempt(() -> rankService.rankItem(userId1, itemTour5, 4, "Very fun! Most recommended!"));
        Long rankId14 = attempt(() -> rankService.rankItem(userId2, itemTour5, 5, "I very Much agree! This was best"));
        Long rankId15 = attempt(() -> rankService.rankItem(userId3, itemTour5, 3, "I have had better times.. but it wasn´t bad."));

        Long rankId16 = attempt(() -> rankService.rankItem(userId1, itemTour6, 4, "Very fun! Most recommended!"));
        Long rankId17 = attempt(() -> rankService.rankItem(userId2, itemTour6, 2, "I fell asleep. The tourguide was talking like he was high on something"));
        Long rankId18 = attempt(() -> rankService.rankItem(userId3, itemTour6, 1, "I have had better times.."));

        Long rankId19 = attempt(() -> rankService.rankItem(userId1, itemTour7, 4, "Very fun! Most recommended!"));
        Long rankId20 = attempt(() -> rankService.rankItem(userId2, itemTour7, 5, "I very Much agree! This was best"));
        Long rankId21 = attempt(() -> rankService.rankItem(userId3, itemTour7, 3, "I have had better times.. but it wasn´t bad."));



        String ctgWaterSport = "watersport";

        String titleWater1 = "The top water activities to try in Qatar";
        String descrWater1 = "As a peninsula surrounded by the Arabian sea and blessed with year-round sunny weather, " +
                "Qatar is an ideal location for water sports. ";

        String titleWater2 = "Diving in the Arabian Gulf";
        String descrWater2 = "From above ground to under water, there is plenty of exploring to be done." +
                " Dive sites are easily accessible, and include wreck, deep water, and reef dives.";

        String titleWater3 = "Kitesurfing in Qatar";
        String descrWater3 = "Kitesurfing is a thrilling activity that combines surfing, wakeboarding," +
                            " and windsurfing, affording thrills and views over the desert landscape.";

        String titleWater4 = "Surfing in Hawaii";
        String descrWater4 = "Surfing is a must in Hawaii! Activity that combines surfing," +
                             " wakeboarding, and windsurfing, affording thrills and views over the wonderful clear ocean.";

        String titleWater5 = "Swimming in the dead sea";
        String descrWater5 = "The ocean is known for its healing powers. Here you can swim in a sharkfree area with no disturbings and all that..";

        String titleWater6 = "Diving at the oceans in Hawaii for sharks";
        String descrWater6 = "Look for exotic creatures in a crystal clear water with thousands of different species!";

        String titleWater7 = "Swimming with Cleopatra";
        String descrWater7 = "Swim in Cleopatra’s thermal Roman pool, and admire Pamukkale’s bright-white terraces!";

        String titleWater8 = "Boat-ride in Hua Hinh ";
        String descrWater8 = "Hua Hinh has a proud history of seafaring, and today it is possible to experience that aboard a traditional wooden dhow boat.";

        //items
        Long itemWater1 = attempt(() -> createItem(ctgWaterSport, titleWater1, descrWater1));
        Long itemWater2 = attempt(() -> createItem(ctgWaterSport, titleWater2, descrWater2));
        Long itemWater3 = attempt(() -> createItem(ctgWaterSport, titleWater3, descrWater3));
        Long itemWater4 = attempt(() -> createItem(ctgWaterSport, titleWater4, descrWater4));
        Long itemWater5 = attempt(() -> createItem(ctgWaterSport, titleWater5, descrWater5));
        Long itemWater6 = attempt(() -> createItem(ctgWaterSport, titleWater6, descrWater6));
        Long itemWater7 = attempt(() -> createItem(ctgWaterSport, titleWater7, descrWater7));
        Long itemWater8 = attempt(() -> createItem(ctgWaterSport, titleWater8, descrWater8));

        //Rank
        Long rank1 = attempt(() -> rankService.rankItem(userId1, itemWater1, 4, "Very fun! Most recommended!"));
        Long rank2 = attempt(() -> rankService.rankItem(userId2, itemWater2, 4, "I very Much agree! This was best"));
        Long rank3 = attempt(() -> rankService.rankItem(userId3, itemWater3, 5, "I have had better times.. but it wasn´t bad."));

        Long rank4 = attempt(() -> rankService.rankItem(userId1, itemWater4, 5, "What a beautifull place. Very fun! Most recommended!"));
        Long rank5 = attempt(() -> rankService.rankItem(userId2, itemWater5, 1, "I fell asleep. The tourguide was talking like he was high on something"));
        Long rank6 = attempt(() -> rankService.rankItem(userId3, itemWater6, 2, "Wate of money! just bla bla bla all day. I wanted to enjoy the trip!"));

        Long rank7 = attempt(() -> rankService.rankItem(userId2, itemWater7, 4, "Wow, just wow...I very Much liked it!"));
        Long rank8 = attempt(() -> rankService.rankItem(userId3, itemWater8, 2, "I have had better times.. but it wasn´t bad."));


    }

    private Long createItem(String category, String title, String descr) {

        return itemService.createItem(category, title, descr);

    }


    private <T> T attempt(Supplier<T> lambda) {
        try {
            return lambda.get();
        } catch (Exception e) {
            return null;
        }
    }


}
