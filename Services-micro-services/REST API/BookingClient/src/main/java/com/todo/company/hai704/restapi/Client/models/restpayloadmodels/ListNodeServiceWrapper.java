package com.todo.company.hai704.restapi.Client.models.restpayloadmodels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.todo.company.hai704.restapi.Client.entity.NodeService;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListNodeServiceWrapper {
    private List<NodeService> nodeServiceList;

    public ListNodeServiceWrapper() {
    }

    public List<NodeService> getNodeServiceList() {
        return nodeServiceList;
    }

    public void setNodeServiceList(List<NodeService> nodeServiceList) {
        this.nodeServiceList = nodeServiceList;
    }
}
