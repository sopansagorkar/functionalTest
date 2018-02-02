package com.nexant.common.contextdata;

/**
 * Class to hold the data which is to be passed across test cases for verification like
 * group name created etc.
 *
 * @author Suresh Nagar
 * @author $LastChangedBy: snagar $
 * @version $Revision: 0 $, 1/16/2015 4:07 PM
 */
public class SchedulingContextData {


	private String groupName;
	private String aptTypeName;


	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getAptTypeName() {
		return aptTypeName;
	}

	public void setAptTypeName(String aptTypeName) {
		this.aptTypeName = aptTypeName;
	}


	private static class LazyHolder {
		private static final SchedulingContextData INSTANCE = new SchedulingContextData();
	}

	public static SchedulingContextData getInstance() {
		return LazyHolder.INSTANCE;
	}

	private SchedulingContextData() {

	}
}
