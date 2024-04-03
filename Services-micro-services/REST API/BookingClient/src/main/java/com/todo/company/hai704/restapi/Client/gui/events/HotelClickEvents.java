package com.todo.company.hai704.restapi.Client.gui.events;


import com.todo.company.hai704.restapi.Client.models.guidatamodels.BrowsingInfo;
import com.todo.company.hai704.restapi.Client.models.guidatamodels.RetrievedHotel;

public interface HotelClickEvents {
    void onSelected(RetrievedHotel hotel, BrowsingInfo browsingInfo);

}
