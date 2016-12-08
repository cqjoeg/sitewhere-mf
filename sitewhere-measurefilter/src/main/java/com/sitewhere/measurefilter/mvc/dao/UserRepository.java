package com.sitewhere.measurefilter.mvc.dao;

import com.sitewhere.measurefilter.mvc.domain.AppUser;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by CQ on 2016/11/18.
 *
 * @author Joeg
 */

@Repository
//@RepositoryRestResource
public interface UserRepository extends PagingAndSortingRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}
