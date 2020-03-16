package com.alonginfo.pageConfig;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;

/**
 * Author:hwn
 * Date:2020-03-13 10:37
 * Description:<描述>
 */
@EnableBinding(Source.class)
public class Producer {

    private Source mySource;

    public Producer(Source mySource) {
        super();
        this.mySource = mySource;
    }

    public Source getMysource() {
        return mySource;
    }

    public void setMysource(Source mysource) {
        mySource = mySource;
    }
}
