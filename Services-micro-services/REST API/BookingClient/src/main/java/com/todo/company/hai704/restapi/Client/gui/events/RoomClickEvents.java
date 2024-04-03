package com.todo.company.hai704.restapi.Client.gui.events;


import com.todo.company.hai704.restapi.Client.models.guidatamodels.RetrievedRoom;

public interface RoomClickEvents {
    void onSelected(RetrievedRoom room);
    void onUnselected(RetrievedRoom room);
}
