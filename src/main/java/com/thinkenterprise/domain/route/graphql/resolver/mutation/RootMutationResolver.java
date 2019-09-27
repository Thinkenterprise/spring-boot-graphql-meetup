package com.thinkenterprise.domain.route.graphql.resolver.mutation;

import java.util.Optional;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.thinkenterprise.domain.route.RouteException;
import com.thinkenterprise.domain.route.graphql.publisher.RouteCreatePublisher;
import com.thinkenterprise.domain.route.jpa.model.Route;
import com.thinkenterprise.domain.route.jpa.repository.RouteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**  
* GraphQL Spring Boot Samples 
* Design and Development by msg Applied Technology Research
* Copyright (c) 2018 msg systems ag (http://www.msg-systems.com/)
* All Rights Reserved.
* 
* @author Michael Schäfer
*/

@Service
public class RootMutationResolver implements GraphQLMutationResolver {

    RouteRepository routeRepository;
    RouteCreatePublisher routeUpdatePublisher;

    @Autowired
    public RootMutationResolver(RouteRepository routeRepository,
                                RouteCreatePublisher routeUpdatePublisher) {
        this.routeRepository=routeRepository;	
        this.routeUpdatePublisher=routeUpdatePublisher;
    }

    public Route createRoute(String flightNumber) {  
        Route route = new Route();
        route.setFlightNumber(flightNumber);
        routeRepository.save(route);
        routeUpdatePublisher.emit(route);
        return route;
    }
    
    public Route updateRoute(Long id, Boolean disabled) {
    	Optional<Route> route = routeRepository.findById(id);
    	if(route.isPresent()) {
    		route.get().setDisabled(disabled);
    		routeUpdatePublisher.emit(route.get());
    		return routeRepository.save(route.get());
    	}
    	throw new RouteException("route not found");
    }
    
    public Boolean deleteRoute(Long id) {
    	Optional<Route> route = routeRepository.findById(id);
    	if(route.isPresent()) {
    		routeRepository.delete(route.get());
    		return Boolean.TRUE;
    	}
    	return Boolean.FALSE;
    }



}