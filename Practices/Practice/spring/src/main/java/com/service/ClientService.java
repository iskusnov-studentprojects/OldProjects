package com.service;

import java.util.List;
import com.entity.Client;

public interface ClientService {
	void add(Client client);

	List<Client> getAll();
        
        public List<Client> getRange(Iterable<Integer> idClients);
        
        public List<Client> getRange(String name);

	Client getOne(int id);

	void delete(int id);
        
        public void deleteRange(Iterable<Client> clients);

	void clear();

	void edit(Client client);
}
