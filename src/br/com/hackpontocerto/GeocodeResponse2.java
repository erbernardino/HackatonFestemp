package br.com.hackpontocerto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

	
	public class GeocodeResponse2 {
	    private String status;
	    private List<Geocode> results = new ArrayList<Geocode>();

	    public String getStatus() {
	        return status;
	    }

	    public void setStatus(String status) {
	        this.status = status;
	    }

	    public void setResults(List<Geocode> results) {
	        this.results = results;
	    }

	    public List<Geocode> getResults() {
	        return results;
	    }
	


	public class Geocode {
	    private Collection<String> types = new ArrayList<String>();
	    private String formatted_address;
	    private Collection<AddressComponent> address_components = new ArrayList<AddressComponent>();
	    private Geometry geometry;
	    private boolean partialMatch;

	    public Collection<String> getTypes() {
	        return types;
	    }

	    public void setTypes(Collection<String> types) {
	        this.types = types;
	    }

	    public void setFormatted_address(String formatted_address) {
	        this.formatted_address = formatted_address;
	    }

	    public String getFormatted_address() {
	        return formatted_address;
	    }

	    public void setAddress_components(Collection<AddressComponent> address_components) {
	        this.address_components = address_components;
	    }

	    public Collection<AddressComponent> getAddress_components() {
	        return address_components;
	    }

	    public Geometry getGeometry() {
	        return geometry;
	    }

	    public void setGeometry(Geometry geometry) {
	        this.geometry = geometry;
	    }

	    public boolean isPartialMatch() {
	        return partialMatch;
	    }

	    public void setPartialMatch(boolean partialMatch) {
	        this.partialMatch = partialMatch;
	    }
	}

	public class AddressComponent {
	    private String longName;
	    private String shortName;
	    private Collection<String> types = new ArrayList<String>();

	    public String getLongName() {
	        return longName;
	    }

	    public void setLongName(String longName) {
	        this.longName = longName;
	    }

	    public String getShortName() {
	        return shortName;
	    }

	    public void setShortName(String shortName) {
	        this.shortName = shortName;
	    }

	    public Collection<String> getTypes() {
	        return types;
	    }

	    public void setTypes(Collection<String> types) {
	        this.types = types;
	    }
	}

	public class Geometry {
	    private Location location;
	    private String locationType;
	    private Area viewport;
	    private Area bounds;

	    public Location getLocation() {
	        return location;
	    }

	    public void setLocation(Location location) {
	        this.location = location;
	    }

	    public String getLocationType() {
	        return locationType;
	    }

	    public void setLocationType(String locationType) {
	        this.locationType = locationType;
	    }

	    public Area getViewport() {
	        return viewport;
	    }

	    public void setViewport(Area viewport) {
	        this.viewport = viewport;
	    }

	    public Area getBounds() {
	        return bounds;
	    }

	    public void setBounds(Area bounds) {
	        this.bounds = bounds;
	    }
	}


	public class Location {
	    private double lat;
	    private double lng;

	    public void setLat(double lat) {
	        this.lat = lat;
	    }

	    public double getLat() {
	        return lat;
	    }

	    public void setLng(double lng) {
	        this.lng = lng;
	    }

	    public double getLng() {
	        return lng;
	    }
	}


	public class Area {
	    private Location southWest;
	    private Location northEast;

	    public Location getSouthWest() {
	        return southWest;
	    }

	    public void setSouthWest(Location southWest) {
	        this.southWest = southWest;
	    }

	    public Location getNorthEast() {
	        return northEast;
	    }

	    public void setNorthEast(Location northEast) {
	        this.northEast = northEast;
	    }
	}
	
}
