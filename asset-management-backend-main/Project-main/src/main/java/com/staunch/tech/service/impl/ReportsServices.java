package com.staunch.tech.service.impl;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.staunch.tech.dto.Reports3Dto;
import com.staunch.tech.dto.ReportsDto;
import com.staunch.tech.entity.Reports3D;
import com.staunch.tech.repository.Reports2DRepository;
import com.staunch.tech.repository.Reports3DRepository;
import com.staunch.tech.repository.TicketRepository;
import com.staunch.tech.service.IReports;
import com.staunch.tech.utils.ConversionUtils;

@Service
public class ReportsServices implements IReports{


	@Autowired
	private Reports2DRepository reportsRepo;
	
	@Autowired
	private Reports3DRepository reports3Repo;
	
	@Override
	public List<ReportsDto> calculateAmountSpent() {
		List<ReportsDto> reports = new ArrayList<ReportsDto>();
		var a = reportsRepo.findAllByType("colour");
		Set<String> labels= new HashSet<>();
		for(var i : a)
		{
			labels.add(i.getLabels());
		}
		int pos = 0;
		for(var i:labels)
		{
			long temp=0;
			List<String> listUuid = new ArrayList<String>();
			int aa = 0;
			for(var j:a)
			{
				if(i.contains(j.getLabels()))
				{
					temp=temp+j.getValues();
					listUuid.add(aa++,j.getTicket_id().getUuid());
				} 
			}
			reports.add(pos++, new ReportsDto(i, temp, listUuid));
		}
		return reports;  
	}

	@Override
	public List<ReportsDto> calculatWeeklyReports() {
		
		List<ReportsDto> reports = new ArrayList<ReportsDto>();
		var a = reportsRepo.findAllByType("weekly");
		Set<String> labels= new HashSet<>();
		for(var i : a)
		{
			labels.add(i.getLabels());
		}
		int pos = 0;
		for(var i:labels)
		{
			long temp=0;
			List<String> listUuid = new ArrayList<String>();
			int aa = 0;
			for(var j:a)
			{
				if(i.contains(j.getLabels()))
				{
					temp=temp+j.getValues();
					listUuid.add(aa++,j.getTicket_id().getUuid());
				} 
			}
			reports.add(pos++, new ReportsDto(i, temp, listUuid));
		}
		return reports;  

	}
	
	@Override
	public List<Reports3Dto> calculateRadarReport() {
		var a = reports3Repo.findAllByType("radar");		
		List<Reports3Dto> reports = new ArrayList<Reports3Dto>();
		int pos = 0;
//		Map<String,Map<String, Long>> map1= new HashMap<String,Map<String, Long>>();
//		for(var i:a)
//		{
//			Map<String,Long> map=new HashMap<String,Long>();
//			List<String> listUuid = new ArrayList<String>();
//			int aa=0;
//			if(map1.containsKey(i.getLabels()))
//			{
//				continue;
//			}
//			else {
//				for(var j : a)
//				{
//					if(j.getLabels() == i.getLabels()) {
//						if(map.containsKey(j.getLabel()))
//						{
//							map.put(j.getLabel(),map.get(j.getLabel())+j.getValues());
//							listUuid.add(aa++,j.getTicket_id().getUuid());
//						}
//						else
//						{
//							map.put(j.getLabel(), j.getValues());
//							listUuid.add(aa++,j.getTicket_id().getUuid());
//						}
//						}
//					else {
//						break;
//					}
//				}
//				map1.put(i.getLabels(), map);
//			}
//			reports.add(pos++, new Reports3Dto(i.getLabels(), new ArrayList<String>(map1.get(i.getLabels()).keySet()), new ArrayList<Long>(map1.get(i.getLabels()).values()), listUuid));
// 		}
		Set<String> labels= new HashSet<>();
		for(var i : a)
		{
			labels.add(i.getLabels());
		}
		List<String> label= new ArrayList<String>();
		label.addAll(Arrays.asList("A safety hazard", "Minor Repair", "Major Repair", "Replacement Required"));
		for(var i:labels)
		{
			List<String> listUuid = new ArrayList<String>();
			List<String> month = new ArrayList<String>();
			List<Long> value = new ArrayList<Long>();
			int aa = 0;
			int bb=0;
			for(var j:label)
			{
				long temp=0;
				for(var k:a)
				{
					if(i.contains(k.getLabels()) && j.contains(k.getLabel()))
					{
						temp=temp+k.getValues();
						listUuid.add(aa++,k.getTicket_id().getUuid());
					}
				}
				month.add(bb,j);
				value.add(bb++,temp);
			}
			reports.add(pos++,new Reports3Dto(i, month, value, listUuid));
		}
		return reports;
	}  
}

