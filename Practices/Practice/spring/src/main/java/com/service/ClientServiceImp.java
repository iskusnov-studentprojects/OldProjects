package com.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.entity.Client;
import com.repository.ClientRepository;
import com.service.ClientService;
import javax.annotation.Resource;

@Service
public class ClientServiceImp implements ClientService {
	@Resource
	ClientRepository cr;

	public void add(Client client) {
		cr.save(client);
	}

	public List<Client> getAll() {
		return (List<Client>) cr.findAll();
	}
        
        public List<Client> getRange(Iterable<Integer> idClients){
            return (List<Client>) cr.findAll(idClients);
        }
        
        public List<Client> getRange(String name){
            return (List<Client>) cr.findByName(name);
        }

	public Client getOne(int id) {
            return cr.findOne(id);
	}

	public void delete(int id) {
		cr.delete(id);
	}
        
        public void deleteRange(Iterable<Client> clients){
            cr.delete(clients);
        }

	public void edit(Client client) {
		cr.save(client);
	}

	public void clear() {
		cr.deleteAll();
	}
}