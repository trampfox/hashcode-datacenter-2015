package com.reply.hashcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

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
    String folder = "C:\\Users\\m.omodei\\Documents\\HashCode\\input\\";
    for(int f = 1; f <= 9; f++) {
	    String inputFilePath = folder + f +".in"; //args[0];
	    String outputFilePath = folder + f + ".out";
	    List<String> header = fileHelper.readFileHeader(inputFilePath);
	    Map<String, Integer> parameters = MatrixHelper.readMatrixParameters(header);
	    List<String> lines = fileHelper.readFileContentByLine(inputFilePath);
	    List<UnavailableSlot> unavailableSlots = MatrixHelper.readUnavailableSlots(parameters, lines);
	    List<Server> servers = MatrixHelper.readServers(parameters, lines);
	    datacenterMatrix = MatrixHelper.buildDatacenterMatrix(parameters, unavailableSlots);
	    rows = parameters.get("rows");
	    slots = parameters.get("slots");
	    poolsNum = parameters.get("pools");
	    serversNum = parameters.get("servers");
	    List<Server> sortedServers = new ArrayList<Server>(servers);
	    Collections.sort(sortedServers);
	    List<Pool> pools = new ArrayList<Pool>();
	    PriorityQueue<Pool> sortedPools = new PriorityQueue<Pool>();
	    for(int i = 0; i < poolsNum; i++) {
	    	Pool tmp = new Pool(i, rows);
	    	pools.add(tmp);
	    	sortedPools.add(tmp);
	    }
	    Collections.sort(pools);
	    //for(Pool tmp : pools)
	    //	//System.out.println("");
	    //System.out.println("Start");
	    int serverCount = 0;
	    for(Server s : sortedServers) {
	    	serverCount++;
	    	if(serverCount % 1000 == 0)
	    		System.out.println("Server " + serverCount + "/" + serversNum);
	    	boolean inserted = false;
	    	List<Integer> removed = new ArrayList<Integer>();
	    	int poolTry = 0, rowTry = 0;
	    	while(!inserted && poolTry < poolsNum) {
	    		poolTry++;
		    	Pool p = sortedPools.poll();
		    	//System.out.println("Pool " + p.getId());
	    		removed.add(p.getId());
		    	Set<Integer> tryedRows = new HashSet<Integer>();
		    	while(!inserted && rowTry < rows) {
		    		Integer min = Integer.MAX_VALUE, minIdx = -1;
		    		for(int i = 0; i < rows; i++) {
		    			if(tryedRows.contains(i))
		    				continue;
		    			else if(p.getRowCapacities().get(i) < min) {
			    			min = p.getRowCapacities().get(i);
			    			minIdx = i;
			    		}		
			    	}
		    		rowTry++;
		    		//System.out.println("Row " + minIdx);
		    		tryedRows.add(minIdx);
			    	inserted = insert(s, p, minIdx);
		    	}
		    
	    	}
	    	for(Integer idx : removed) {
	    		sortedPools.add(pools.get(idx));
	    	}
	    }
	    fileHelper.writeOutputFile(servers, outputFilePath);
    }
    
  }

  private static boolean insert(Server s, Pool p, Integer rowIdx) {
	  int start = -1, size = 0;
	  for(Integer i = 0; i < slots; i++) {
		  if(datacenterMatrix[rowIdx][i] == -2) {
			  if(start == -1) {
				  start = i;
				  size = 1;
			  }
			  else {
				  size++;
			  }
		  }
		  else {
			  start = -1;
			  size = 0;
		  }
		  if(size == s.getSize()) {
			  for(Integer j = 0; j < s.getSize(); j++)
				  datacenterMatrix[rowIdx][j + start] = s.getId();
			  s.setRow(rowIdx);
			  s.setSlot(start);
			  s.setPoolId(p.getId());
			  p.addServer(s, rowIdx);
			  return true;
		  }
	  }
	  return false;
  }

}
