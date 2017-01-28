package com.kolimpri.ag.domain;

/**
 * Enumeration of supported Regions.
 * @author jaime
 *
 */

public enum Region {
	
	BCS("Baja California Sur"), AGS("Aguascalientes"), SIN("Sinaloa");
	
	private String label;
	
	private Region(String label) {
		this.label = label;
	}
	
	public static Region findByLabel(String byLabel) {
		for(Region r : Region.values()) {
			if (r.label.equalsIgnoreCase(byLabel)) {
				return r;
			}
		}
		return null;
	}
}
