package web.service.booking.client.gui.events;

import web.service.booking.client.models.RetrievedRoom;

public interface RoomClickEvents {
    void onSelected(RetrievedRoom room);
    void onUnselected(RetrievedRoom room);
}
