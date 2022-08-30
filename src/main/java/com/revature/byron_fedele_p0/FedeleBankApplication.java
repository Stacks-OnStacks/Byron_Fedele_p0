package com.revature.byron_fedele_p0;

/* Fedele Bank is an application that simulates a simple baking application where you can sign up, log in,
and make basic withdrawal and deposit transactions
*/

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class FedeleBankApplication {

    public static void main(String[] args) {
        SpringApplication.run(FedeleBankApplication.class, args);
    }

}



