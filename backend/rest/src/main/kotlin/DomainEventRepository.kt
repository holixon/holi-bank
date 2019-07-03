package de.holisticon.holibank.backend.rest

import org.axonframework.eventsourcing.eventstore.jpa.DomainEventEntry
import org.springframework.data.repository.PagingAndSortingRepository

interface DomainEventRepository : PagingAndSortingRepository<DomainEventEntry, Long>
