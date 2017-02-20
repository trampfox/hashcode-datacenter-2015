package com.reply.hashcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import com.reply.hashcode.helpers.FileHelper;
import com.reply.hashcode.helpers.MatrixHelper;

/**
 * @author Davide Monfrecola
 */
public class Application {

	static Integer[][] datacenterMatrix;
	static Integer rows, slots, poolsNum, serversNum;
	
  public static void main(String[] args) {
    FileHelper fileHelper = new FileHelper();
    String inputFilePath = args[0];

    List<String> header = fileHelper.readFileHeader(inputFilePath);
    Map<String, Integer> parameters = MatrixHelper.readMatrixParameters(header);

    List<String> lines = fileHelper.readFileContentByLine(inputFilePath);
    List<UnavailableSlot> unavailableSlots = MatrixHelper.readUnavailableSlots(parameters, lines);
    Map<Integer, Server> servers = MatrixHelper.readServers(parameters, lines);
    List<Server> sortedServers = new ArrayList<Server>();
    for(Integer key : servers.keySet())
    	sortedServers.add(servers.get(key));
    Collections.sort(sortedServers);
    List<Pool> pools = new ArrayList<Pool>();
    PriorityQueue<Pool> sortedPools = new PriorityQueue<Pool>();
    for(int i = 0; i < poolsNum; i++) {
    	Pool tmp = new Pool(i);
    	pools.add(tmp);
    	sortedPools.add(tmp);
    }
    Collections.sort(pools);
    datacenterMatrix = MatrixHelper.buildDatacenterMatrix(parameters, unavailableSlots);
    rows = parameters.get("rows");
    slots = parameters.get("slots");
    poolsNum = parameters.get("pools");
    serversNum = parameters.get("servers");
    for(Server s : sortedServers) {
    	boolean inserted = false;
    	List<Integer> removed = new ArrayList<Integer>();
    	int poolTry = 0;
    	while(!inserted && poolTry < poolsNum) {
    		poolTry++;
	    	Pool p = sortedPools.poll();
	    	removed.add(p.getId());
	    	Integer min = Integer.MAX_VALUE, minIdx = -1;
	    	for(int i = 0; i < p.getRowCapacities().size(); i++) {
	    		if(p.getRowCapacities().get(i) < min) {
	    			min = p.getRowCapacities().get(i);
	    			minIdx = i;
	    		}		
	    	}
	    	inserted = insert(s, p, minIdx);
    	}
    	for(Integer idx : removed) {
    		sortedPools.add(pools.get(idx));
    	}
    }
    
    
  }
  
  private static boolean insert(Server s, Pool p, Integer rowIdx) {
	  int start = -1, size = 0;
	  for(Integer i = 0; i < slots; i++) {
		  if(datacenterMatrix[rowIdx][i] == -2) {
			  if(start != -1) {
				  start = i;
				  size = 1;
			  }
			  else
				  size++;
		  }
		  if(size == s.getSize()) {
			  for(Integer j = 0; j < s.getSize(); j++)
				  datacenterMatrix[rowIdx][j + start] = s.getId();
			  p.addServer(s, rowIdx);
			  return true;
		  }
	  }
	  return false;
  }

}
