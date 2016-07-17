/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ninjav.sample.soap;

import java.util.List;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import org.ninjav.sample.data.MemberRepository;
import org.ninjav.sample.model.Member;
import org.ninjav.sample.service.MemberRegistration;

/**
 *
 * @author ninjav
 */
@WebService(serviceName = "MemberWS")
public class MemberWS {

    @Inject
    MemberRegistration registration;
    
    @Inject
    private MemberRepository repository;

    @WebMethod
    public void register(@WebParam Member member) throws Exception {
        registration.register(member);
    }
    
    @WebMethod
    @WebResult(name = "members")
    public List<Member> listAllMembers() {
        return repository.findAllOrderedByName();
    }

}
