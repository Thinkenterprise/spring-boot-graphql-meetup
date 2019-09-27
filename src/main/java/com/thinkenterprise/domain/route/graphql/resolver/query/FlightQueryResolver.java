package com.thinkenterprise.domain.route.graphql.resolver.query;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.thinkenterprise.domain.route.jpa.model.Flight;
import com.thinkenterprise.domain.route.jpa.model.Route;
import com.thinkenterprise.domain.route.jpa.repository.RouteRepository;

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

@Component(value=FlightQueryResolver.ROOT_QUERY_RESOLVER)
public class FlightQueryResolver implements GraphQLResolver<Flight> {
	
	static final String ROOT_QUERY_RESOLVER = "com.thinkenterprise.domain.route.graphql.resolver.query.FlightQueryResolver";
	private RouteRepository routetRepository;
	
	@Autowired
	public FlightQueryResolver(RouteRepository routetRepository) {
		super();
		this.routetRepository = routetRepository;
	}

	public FlightQueryResolver() {
		super();
	}
	
	public Route route(Flight flight) {	
		return routetRepository.findById(flight.getRoute().getId()).get();
	}
}
