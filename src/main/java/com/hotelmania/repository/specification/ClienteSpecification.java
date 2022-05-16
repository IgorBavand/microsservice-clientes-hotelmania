package com.hotelmania.repository.specification;

import com.hotelmania.model.Cliente;
import com.hotelmania.service.filter.ClienteFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.hotelmania.model.*;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ClienteSpecification implements Specification<Cliente> {

    private static final long serialVersionUID = 6970841708640167374L;
    private final ClienteFilter filter;

    public static ClienteSpecification of(ClienteFilter filter) {
        if (filter == null) {
            return null;
        }
        return new ClienteSpecification(filter);
    }

    @Override
    public Predicate toPredicate(Root<Cliente> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if(filter.getNomeCliente() != null){
            predicates.add(criteriaBuilder.like(root.get(Cliente_.nomeCliente), "%" + filter.getNomeCliente() + "%"));

        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));

    }
}
























