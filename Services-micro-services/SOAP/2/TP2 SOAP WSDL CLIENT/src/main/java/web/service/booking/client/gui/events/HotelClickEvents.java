package web.service.booking.client.gui.events;

import web.service.booking.client.models.BrowsingInfo;
import web.service.booking.client.models.RetrievedHotel;

public interface HotelClickEvents {
    void onSelected(RetrievedHotel hotel, BrowsingInfo browsingInfo);

}
