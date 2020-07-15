/*
 * Copyright 2016 Red Hat, Inc.
 * <p>
 * Red Hat licenses this file to you under the Apache License, version
 * 2.0 (the "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 *
 */
package com.redhat;
	
import java.util.logging.Logger;

import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

/**
 * A sample transform
 */
@Component(value = "myTransformer")
public class MyTransformer {

	Logger log= Logger.getLogger(this.getClass().getName());
    public String transform() {
        // let's return a random string
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < 3; i++) {
            int number = (int) (Math.round(Math.random() * 1000) % 10);
            char letter = (char) ('0' + number);
            buffer.append(letter);
        }
        return buffer.toString();
    }

    //inject exchange
    public void updateUser(Exchange ex) {
        log.info("processor: "+ex.getIn().getBody());
        User user=(User)ex.getIn().getBody();
        log.info(""+user.getName());
        log.info("processor: "+ex.getIn().getHeader("userId"));
        log.info("processor: "+ex.getIn().getHeader("name"));
    	//log.info("processor: "+user);
    }
    //inject Body type directly
    public void updateUser1(User user) {
        log.info(""+user.getName());
    }
    

    //another way is to inject both Exchange and type of Body, sequence does not matter

    public void updateUser2(Exchange ex, User user) {
        log.info(""+user.getName());
        log.info(""+ex.getIn().getBody());
    }
    
    public void test(Exchange ex) {
        log.info("processor: "+ex.getIn().getBody());
        ex.getOut().setBody(ex.getIn().getBody());
    }
}
