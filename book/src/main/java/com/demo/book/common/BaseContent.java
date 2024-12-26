package com.demo.book.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseContent {

    Map<String, Object> flowParams=new HashMap<>();

    public Map<String, Object> getFlowParams(){
        return flowParams;
    }

    public void setFlowParams(Map<String, Object> flowParams){
        this.flowParams=flowParams;
    }
}
