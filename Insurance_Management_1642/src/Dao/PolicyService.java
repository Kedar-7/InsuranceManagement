package Dao;


import java.util.Collection;

import Entity.Policy;

	public interface PolicyService {
	    boolean createPolicy(Policy policy);
	    Policy getPolicy(int policyId);
	    Collection<Policy> getAllPolicies();
	    boolean updatePolicy(Policy policy);
	    boolean deletePolicy(int policyId);
	}