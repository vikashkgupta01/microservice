package com.demo.book.service;

import com.demo.book.common.ProcessContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
public class TxnRefGenerationSerive {

    @Autowired
    ProcessContent processContent;

    public void execute(){
        //String sequence=String.format("%05d",sequenceRepo.getNextValue());
        String dateString= LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyMMddHHmmss"));
        String transRef= dateString+Math.random();
        processContent.getFlowParams().put("transRef",transRef);

        for(Map.Entry<String, Object> entry:processContent.getFlowParams().entrySet()){
            System.out.println(entry.getKey()+"::"+entry.getValue());
        }
    }

}
