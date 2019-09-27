package com.thinkenterprise.domain.route.graphql.resolver.query;

import java.util.List;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.thinkenterprise.domain.route.jpa.model.Flight;
import com.thinkenterprise.domain.route.jpa.model.Route;
import com.thinkenterprise.domain.route.jpa.repository.FlightRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**  
* GraphQL Spring Boot Samples 
* Design and Development by msg Applied Technology Research
* Copyright (c) 2018 msg systems ag (http://www.msg-systems.com/)
* All Rights Reserved.
* 
* @author Michael Schäfer
*/

@Component(RouteQueryResolver.ROOT_QUERY_RESOLVER)
public class RouteQueryResolver implements GraphQLResolver<Route> {

	static final String ROOT_QUERY_RESOLVER = "com.thinkenterprise.domain.route.graphql.resolver.query.RouteQueryResolver";
    private FlightRepository flightRepository;

    public RouteQueryResolver() {
		super();
	}

	@Autowired
    public RouteQueryResolver(FlightRepository flightRepository) {
        this.flightRepository=flightRepository;
    }
       
   	
    public List<Flight> flights(Route route) {
       	return flightRepository.findByRoute(route);	
    }
     
}